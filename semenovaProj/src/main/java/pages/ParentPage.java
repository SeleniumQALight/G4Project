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
    WebDriverWait webDriverWait10, webDriverWait15;
    protected String baseUrl = "https://qa-complex-app-for-testing.herokuapp.com";

    public ParentPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
        webDriverWait10 = new WebDriverWait(webDriver, 10);
        webDriverWait15 = new WebDriverWait(webDriver, 15);
    }
    abstract String getRelativeUrl();

    protected void checkUrl(){
        Assert.assertEquals("InValid Page"
                , baseUrl + getRelativeUrl()
                ,webDriver.getCurrentUrl() );
    }
    protected void checkUrlWithPattern(){
        Assert.assertThat("InValid page"
                ,webDriver.getCurrentUrl()
                ,containsString(baseUrl + getRelativeUrl()));
    }

    protected void enterTextIntoElement(WebElement webElement, String text) {
        try {
            webDriverWait15.until(ExpectedConditions.visibilityOf(webElement));
            webElement.clear();
            webElement.sendKeys(text);
            logger.info(text + " was inputted ");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    protected void clickOnEltment(WebElement webElement) {
        try {
            webDriverWait10.until(ExpectedConditions.elementToBeClickable(webElement));
            webElement.click();
            logger.info("Element was clicked");

        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    protected boolean isMessageDisplayed(WebElement webElement) {
        try {
            return webElement.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    protected boolean isElementDisplayed(WebElement webElement) {
        try {
            boolean state = webElement.isDisplayed();
            if (state) {
                logger.info("Element is displayed");
            } else {
                logger.info("Element is not displayed");
            }
            return state;
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



    protected void isCheckBoxCondition(WebElement checkBox, String condition) {
        try {
            if (condition.equalsIgnoreCase("check") & checkBox.isSelected()) {
                logger.info("CheckBox is check ");
            } else if (condition.equalsIgnoreCase("check") & !checkBox.isSelected()) {
                clickOnEltment(checkBox);
                logger.info("CheckBox was checked");
            } else if (condition.equalsIgnoreCase("uncheck") & checkBox.isSelected()) {
                clickOnEltment(checkBox);
                logger.info("CheckBox must be uncheck");
            } else if (condition.equalsIgnoreCase("uncheck") & !checkBox.isSelected()) {
                logger.info("CheckBox uncheck");
            } else {
                logger.info("State is not selected");
                Assert.assertTrue("State is not selected", checkBox.isSelected());
            }

        } catch (Exception e) {
            printErrorAndStopTest(e);
        }

    }

    protected void waitChatTobeHide() {
        //TODO wait chat
        webDriverWait10
                .withMessage("Chat is no closed")
                .until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(".//*[@id='chat-wrapper']")));
    }

    private void printErrorAndStopTest(Exception e) {
        logger.error("Can not work with element" + e);
        Assert.fail("Can not work with element" + e);
    }
}
