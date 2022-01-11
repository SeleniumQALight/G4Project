package pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class ParentPage {
    Logger logger=Logger.getLogger(getClass());
    WebDriver webDriver;//получили webDriver

    public ParentPage(WebDriver webDriver) {
        this.webDriver = webDriver;// сюда его засетили
        PageFactory.initElements(webDriver,this);// с той пейджи с которой пришли елементы то и про инециализируй , если Логин пейдж то его если Хом то хом
    }

    protected void enterTextInToElement(WebElement webElement,String text){
    try {
        webElement.clear();
        webElement.sendKeys(text);
        logger.info(text + " Was inputted ");
    }catch (Exception e){
        printErrorAndStopTest(e);
    }
    }
    protected  void clickElement(WebElement webElement){
        try {
            webElement.click();
            logger.info(" Element was clicked ");
        }catch (Exception e){
            printErrorAndStopTest(e);
        }

    }



    private void printErrorAndStopTest(Exception e) {
        logger.error(" Can not work with element " + e);
        Assert.fail(" Can not work with element " + e);
    }

}
