package pages;
//Все действия для всех страниц

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class ParentPage {
    Logger logger = Logger.getLogger(getClass());// Logger для всех страниц
    WebDriver webDriver;

    public ParentPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver,this);
    }

    protected void enterTextInToElement(WebElement webElement,String text){  //метод,который будет вводить текст
        try {
            webElement.clear();
            webElement.sendKeys(text);
            logger.info(text +" was input");
        } catch (Exception e){
            printErrorandStopTest(e);
        }
    }
    protected void clickOnElement(WebElement webElement) { // метод, который кликает на элемент
        try {
            webElement.click();
            logger.info("Element was clicked");

        } catch (Exception e){
            printErrorandStopTest(e);
        }
    }

    private void printErrorandStopTest(Exception e) {
        logger.error("Can not work with element" + e);
        Assert.fail("Can not work with element"+ e);

    }
}
