package pages;

import org.apache.log4j.Logger;
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

    protected void EnterTextInToEveryElement(WebElement webElement, String text){
        try {
            webElement.clear();
            webElement.sendKeys();
            logger.info(text + " was inputted" );
        }catch  (Exception e){
            printElementAndStopTest(e);
        }
    }
    private void printElementAndStopTest(Exception e) {
    }
    protected void clickOnElement (WebElement webElement){
        try {
            webElement.click();
            logger.info(webElement + " was clicked" );
        }catch  (Exception e){
            printElementAndStopTest(e);
        }
    }
    }




