package pages;

import libs.TestData;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.Locale;


public class ParentPage {
    Logger logger = Logger.getLogger(getClass());
    WebDriver webDriver;


    public ParentPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    protected boolean elementIsVisible(WebElement webElement){
        try{
            if(webElement.isDisplayed()){
                logger.info("The element is displayed");
                return true;
            }
            logger.info("Element is not displayed");
            return false;
        }catch (Exception e){
            logger.info("Element isn't displayed");
            return false;
        }
    }

    protected void enterTextIntoElement(WebElement webElement, String text) {
        try {
            webElement.clear();
            webElement.sendKeys(text);
            logger.info(text+" was inputted ");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    protected void clickOnElement(WebElement webElement){
        try {
            webElement.click();
            logger.info("Element was clicked");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    protected boolean compareElementTextWithExpectedText(WebElement webElement){
        try {
            if (webElement.getText().equals(TestData.VALID_USERNAME.toLowerCase(Locale.ROOT))) {
                return true;
            }
        }catch (Exception e){
            printErrorAndStopTest(e);
            return false;
        }
        return false;
    }

    private void printErrorAndStopTest(Exception e) {
        logger.error("Can not work with element" + e);
        Assert.fail("Can not work with element" + e);
    }

    protected void selectTextInDD(WebElement dropDown, String text){
        try{
            Select select = new Select(dropDown);
            select.selectByVisibleText(text);
            logger.info(text+" was selected in DD");
        }catch(Exception e){
            printErrorAndStopTest(e);
        }
    }

    protected void selectValueInDropDown(WebElement dropDown, String value){
        try{
            Select select = new Select(dropDown);
            select.selectByValue(value);
            logger.info(value+" was selected in DD");
        }catch(Exception e){
            printErrorAndStopTest(e);
        }
    }
}
