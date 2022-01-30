package pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.hamcrest.CoreMatchers.containsString;

abstract public class ParentPage {
    //public class ParentPage {
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
    abstract  String getRelativeUrl();

    protected  void  checkUrl(){
        // message- expectedResult-ActualResult
        Assert.assertEquals("Invalid page"
                , baseUrl + getRelativeUrl()
                ,webDriver.getCurrentUrl());
    }

    protected  void checkUrlWithPattern(){
        Assert.assertThat("Invalid page"
                ,webDriver.getCurrentUrl()
                , containsString(baseUrl+getRelativeUrl()));
    }

    protected void enterTextInToElement(WebElement webElement, String text) {
        try {
            webElement.clear();
            webElement.sendKeys(text);
            logger.info((text + "  was inputted"));

        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    protected void clickOnElement(WebElement webElement) {
        try {
            webDriverWait10.until(ExpectedConditions.elementToBeClickable(webElement)).click();
            logger.info(("WebElement clicked"));
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }


    private void printErrorAndStopTest(Exception e) {
        logger.error("Can not work with element  " + e);
        Assert.fail("Can not work with element  " + e);
    }

    protected boolean isElementDisplayed(WebElement webElement) {
        try {
            boolean state = webElement.isDisplayed();
            if (state) {
                logger.error("Element is displayed");
            } else {
                logger.error("Element is NOT  displayed");
            }
            return state;
        } catch (Exception e) {
            logger.error("Element is NOT  displayed");
            return false;
        }
    }

    protected void selectTextInDropDown(WebElement dropDown, String text) {
        try { //get all elements in Dropdown
            Select select = new Select(dropDown);
            // choose by visible text
            select.selectByVisibleText(text);
            logger.info(text + " was selected in Dropdown");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }

    }

    // the method is faster than select by text
    protected void selectValueInDropDown(WebElement dropDown, String value) {
        try {
            Select select = new Select(dropDown);
            select.selectByValue(value);
            logger.info(value + " was selected in Dropdown");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    protected  void waitChatTobeHide(){
        webDriverWait10
                .withMessage("Chat is no closed")
                .until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(".//*[@id='chat-wrapper']")));
        //TODO wait chat
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

}
