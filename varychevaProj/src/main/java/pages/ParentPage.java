package pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import static org.junit.Assert.*;

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
            logger.info(text + " was inputted");
        } catch (Exception ex) {
            printErrorAndStopTest(ex);
        }
    }

    protected void clickOnElement(WebElement webElement) {
        try {
            webElement.click();
            logger.info("Element was clicked");
        } catch (Exception ex) {
            printErrorAndStopTest(ex);
        }
    }

    protected String getTextFromElement(WebElement webElement) {
        try {
            String text = webElement.getText();
            logger.info("Text from Element: " + text);
            return text;
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
        return null;
    }

    private void printErrorAndStopTest(Exception e) {
        logger.error("Can not work with element" + e);
        fail("Can not work with element" + e);
    }

}
