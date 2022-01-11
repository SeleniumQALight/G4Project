package pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.PageFactoryFinder;


public class ParentPage {
    Logger logger = Logger.getLogger(getClass());
    WebDriver webDriver;
//alt + insert и создать конструктор
    public ParentPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this); //проиницилизровать елементы описанне через FindBy
    }

    protected void enterTextInToElement(WebElement webElement, String text) { //метод для чистки и ввода текста в елементы
        try {
            webElement.clear();
            webElement.sendKeys(text);
            logger.info(text + " was inputted ");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

   protected boolean isElementDispleid(WebElement webElement) {
       try{
           return webElement.isDisplayed();
       }catch (Exception e){
           return false;
       }
  }


    protected void clickOnElement(WebElement webElement){
        try {
            webElement.click();
            logger.info("Element was clicked");
        }catch (Exception e){
            printErrorAndStopTest(e);
        }
    }

    private void printErrorAndStopTest(Exception e) {
        logger.error("Can not work with element" + e);// в консоль
        Assert.fail("Can not work with element" + e);// в отчет
    }

}
