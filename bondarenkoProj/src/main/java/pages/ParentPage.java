package pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class ParentPage {
    Logger logger = Logger.getLogger(getClass());
    WebDriver webDriver;

    public ParentPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    protected void enterTextIntoElement(WebElement webElement, String text) {
        try {
            webElement.clear();
            webElement.sendKeys(text);
            logger.info(text + " was inputted ");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    protected  void clickOnElement(WebElement webElement){
        try{
            webElement.click();
            logger.info(webElement + "Element was clicked");
    }catch (Exception e){
            printErrorAndStopTest(e);
        }
    }
    private void printErrorAndStopTest(Exception e) {
        logger.error("Can not work with element " + e);
        Assert.fail("Can not work with element " + e);
    }

   protected void isMessageForFieldDisplayed(WebElement webElement){
        try{
            webElement.isDisplayed();
            logger.info(webElement + " is displayed");
        }catch (Exception e){
            printErrorAndStopTest(e);
        }
   }
}