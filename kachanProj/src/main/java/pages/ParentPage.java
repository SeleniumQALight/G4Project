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
    WebDriver webDriver;
    WebDriverWait webDriverWaite10, webDriverWaite15;
    protected String baseUrl = "https://qa-complex-app-for-testing.herokuapp.com";

    public ParentPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
        webDriverWaite10 = new WebDriverWait(webDriver, 10);
        webDriverWaite15 = new WebDriverWait(webDriver, 15);
    }

    abstract String getRalativeUrl ();

    protected void checkUrl() {
        Assert.assertEquals("Invalide page"
                , baseUrl + getRalativeUrl()
                , webDriver.getCurrentUrl());

    }

    protected void checkUrlWithPattern(){
        Assert.assertThat("Invalide page"
                , webDriver.getCurrentUrl()
                , containsString(baseUrl+getRalativeUrl()));
    }

    protected void enterTextInToElement(WebElement webElement, String text) {
        try {
            webDriverWaite15.until(ExpectedConditions.visibilityOf(webElement));
            webElement.clear();
            webElement.sendKeys(text);
            logger.info(text + "was inputted");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    protected void clickOnElement(WebElement webElement) {
        try {
            webDriverWaite10.until(ExpectedConditions.elementToBeClickable(webElement));
            webElement.click();
            logger.info("Element was cliked");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    //Нужно будет сделать в ДЗ
    protected boolean isElementDisplayed(WebElement webElement) {
        try {
            boolean state = webElement.isDisplayed();
            if (state) {
                logger.info("Element is displayed");
            } else {
                logger.info("Element is not displayed");
            }
            return webElement.isDisplayed();
        } catch (Exception e) {
            logger.info("Element is not displayed");
            return false;
        }
    }

    protected void selectTextInDropDown(WebElement dropDown, String text) {
        try {
            Select select = new Select(dropDown);
            select.selectByVisibleText(text);
            logger.info(text + "was selected in DD");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    protected void selectValueInDropDown(WebElement dropDown, String value) {
        try {
            Select select = new Select(dropDown);
            select.selectByValue(value);
            logger.info(value + "was selected in DD");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    protected void waitChatTobeHide() {
        webDriverWaite10
                .withMessage("Chat is no closed")
                .until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(".//*[@id='chat-wrapper']")));

    }

    private void printErrorAndStopTest(Exception e) {
        logger.error("Can not work with " + e);
        Assert.fail("Can not with clicked");
    }

}
