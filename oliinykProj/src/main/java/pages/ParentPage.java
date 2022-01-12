package pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class ParentPage {
    Logger logger = Logger.getLogger(getClass());
    WebDriver driver;

    public ParentPage(WebDriver driver) {
        this.driver = driver;
        //inicializiruem elementi na stranicah (izuchity PageFactory)
        PageFactory.initElements(driver, this);
    }

    protected void enterTextIntoElement(WebElement webElement, String text){
        try {
            webElement.clear();
            webElement.sendKeys(text);
            logger.info(text + " was entered");
        }catch (Exception e){
            printErrorAndStopTest(e);
        }
    }

    void printErrorAndStopTest(Exception e) {
        logger.error("Can't work with element" + e);
        Assert.fail("Can't work with element" + e);
    }

    protected void clickOnElement(WebElement webElement){
        try{
            webElement.click();
            logger.info("Element was clicked");
        }catch (Exception e){
            printErrorAndStopTest(e);
        }
    }

    protected boolean checkErrorText(WebElement element){
        try {
            return element.isDisplayed();
        }catch (Exception e){
            printErrorAndStopTest(e);
            return false;
        }
    }
}
