package pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.PageFactoryFinder;
import org.openqa.selenium.support.ui.Select;


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
           boolean state = webElement.isDisplayed(); // нет елемента
           if (state){
               logger.info("Element is displayed"); // есть елемент
           } else {
               logger.info("Element is not displayed");// нет елемента
           }
           return webElement.isDisplayed();
       }catch (Exception e){
           logger.info("Element is not displayed");
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

    protected void selectTextInDropDown(WebElement dropDown, String text){
        try {
            Select select = new Select(dropDown);//передать закрытый дробдаун
            select.selectByVisibleText(text);//выбрать нужный текст
            logger.info(text + "was selected in DD");
        }catch (Exception e){
            printErrorAndStopTest(e);
        }
    }

    protected void selectValueInDropDown (WebElement dropDown, String value){
        try {
            Select select = new Select(dropDown);//передать закрытый дробдаун
            select.selectByValue(value);//выбрать нужный текст
            logger.info(value + "was selected in DD");
        }catch (Exception e){
            printErrorAndStopTest(e);
        }

    }

    protected void waitChatTobeHide(){
        //ToDO wait chat
        try {
            Thread.sleep(1000); //свернуть все и подождать в милисекундах alt + entr
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void printErrorAndStopTest(Exception e) {
        logger.error("Can not work with element" + e);// в консоль
        Assert.fail("Can not work with element" + e);// в отчет
    }

}
