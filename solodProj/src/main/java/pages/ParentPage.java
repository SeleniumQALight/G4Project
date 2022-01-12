package pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class ParentPage {
    Logger logger = Logger.getLogger(getClass());
    WebDriver webDriver;//получили webDriver

    public ParentPage(WebDriver webDriver) {
        this.webDriver = webDriver;// сюда его засетили
        PageFactory.initElements(webDriver, this);// с той пейджи с которой пришли елементы то и про инециализируй , если Логин пейдж то его если Хом то хом
    }

    protected void enterTextInToElement(WebElement webElement, String text) {
        try {
            webElement.clear();
            webElement.sendKeys(text);
            logger.info(text + " Was inputted ");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    protected void clickElement(WebElement webElement) {
        try {
            webElement.click();
            logger.info(" Element was clicked ");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }

    }

    protected boolean isElementDisplayed(WebElement webElement) {
        try {
            boolean state = webElement.isDisplayed();
            if (state) {
                logger.info(" Element is displayed ");
            } else {
                logger.info(" Element is not displayed ");
            }
            return state;//если веб елемент есть покажы тру ! еслит нет фолс , если ненашел то   Exception
        } catch (Exception e) {
            logger.info(" Element is not displayed ");
            return false;
        }

    }
    protected void selectTextInDropDown(WebElement dropDown,String text){
       try {
           Select select=new Select(dropDown);
           select.selectByVisibleText(text);
           logger.info(text+" was selected in DD ");
       }catch (Exception e){
           printErrorAndStopTest(e);
       }
    }
protected void selectValueDropDown(WebElement dropDown,String value){
    try {
        Select select=new Select(dropDown);
        select.selectByValue(value);
        logger.info(value +" was selected in DD ");
    }catch (Exception e){
        printErrorAndStopTest(e);
    }

}
protected void waitChatToBeHide(){
        //TODO wait chat
    try {
        Thread.sleep(1000);
    }catch (InterruptedException e){
        e.printStackTrace();
    }
}


    private void printErrorAndStopTest(Exception e) {
        logger.error(" Can not work with element " + e);
        Assert.fail(" Can not work with element " + e);
    }
}
