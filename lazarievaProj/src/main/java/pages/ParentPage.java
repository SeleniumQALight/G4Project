package pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class ParentPage {
    Logger logger = Logger.getLogger(getClass());
    WebDriver webDriver;

    public ParentPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
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
            webElement.click();
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
        //TODO wait chat
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
