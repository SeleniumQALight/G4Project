package pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.hamcrest.CoreMatchers.containsString;

abstract public class ParentPage {
    Logger logger = Logger.getLogger(getClass());
    WebDriver webDriver;//получили webDriver
    WebDriverWait webDriverWait10, webDriverWait15;
    protected String baseUrl = "https://qa-complex-app-for-testing.herokuapp.com";


    public ParentPage(WebDriver webDriver) {
        this.webDriver = webDriver;// сюда его засетили
        PageFactory.initElements(webDriver, this);// с той пейджи с которой пришли елементы то и про инециализируй , если Логин пейдж то его если Хом то хом
        webDriverWait10 = new WebDriverWait(webDriver, 10);
        webDriverWait15 = new WebDriverWait(webDriver, 15);
    }
    abstract String getRelativeUrl();

    protected  void checkUrl(){
        Assert.assertEquals(" Invalid page "
                ,baseUrl + getRelativeUrl()
                ,webDriver.getCurrentUrl() );
    }
    protected  void  checkUrlWithPattern(){
        Assert.assertThat("Invalid page"
                ,webDriver.getCurrentUrl()
                ,containsString(baseUrl+getRelativeUrl()));
    }


    protected void enterTextInToElement(WebElement webElement, String text) {
        try {
            webDriverWait15.until(ExpectedConditions.visibilityOf(webElement));
            webElement.clear();
            webElement.sendKeys(text);
            logger.info(text + " Was inputted ");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    protected void clickOnElement(WebElement webElement) {
        try {
            webDriverWait10.until(ExpectedConditions.elementToBeClickable(webElement));
            webElement.click();
            logger.info(" Element was clicked ");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }

    }

    protected boolean isElementDisplayed(WebElement webElement) {
        try {
            boolean state = webElement.isDisplayed();
            if (state) {
                logger.info(" Element is displayed ");
            } else {
                logger.info(" Element is not displayed ");
            }
            return state;//если веб елемент есть покажы тру ! еслит нет фолс , если ненашел то   Exception
        } catch (Exception e) {
            logger.info(" Element is not displayed ");
            return false;
        }

    }

    protected void selectTextInDropDown(WebElement dropDown, String text) {
        try {
            Select select = new Select(dropDown);
            select.selectByVisibleText(text);
            logger.info(text + " was selected in DD ");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    protected void selectValueDropDown(WebElement dropDown, String value) {
        try {
            Select select = new Select(dropDown);
            select.selectByValue(value);
            logger.info(value + " was selected in DD ");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }

    }

    protected void waitChatToBeHide() {
        webDriverWait10.withMessage(" Chat is no closed ")
                .until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(".//*[@id='chat-wrapper']")));
    }


    private void printErrorAndStopTest(Exception e) {
        logger.error(" Can not work with element " + e);
        Assert.fail(" Can not work with element " + e);
    }
}
