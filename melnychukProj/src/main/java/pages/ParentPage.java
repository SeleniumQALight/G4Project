package pages;

import libs.ConfigProperties;
import org.aeonbits.owner.ConfigFactory;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementLocatorFactory;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.containsString;

abstract public class ParentPage {
    Logger logger = Logger.getLogger(getClass());
WebDriver webDriver;
WebDriverWait webDriverWait10, webDriverWait15;
public static ConfigProperties configProperties =
        ConfigFactory.create(ConfigProperties.class);

    protected String baseUrl=configProperties.base_url();

//protected String baseUrl="https://qa-complex-app-for-testing.herokuapp.com";

    public ParentPage(WebDriver webDriver) {
        this.webDriver = webDriver;
       PageFactory.initElements(webDriver, this);
     /*
       PageFactory.initElements(
                new HtmlElementDecorator(
                        new HtmlElementLocatorFactory(webDriver))
                ,this);
        */
        //webDriverWait10= new WebDriverWait(webDriver, 10);
       // webDriverWait15=  new WebDriverWait(webDriver, 15);
        webDriverWait10= new WebDriverWait(webDriver, configProperties.TIME_FOR_DFFAULT_WAIT());
        webDriverWait15=  new WebDriverWait(webDriver, configProperties.TIME_FOR_EXPLICIT_WAIT_LOW());
    }
    abstract String getRelativeUrl();

    protected void checkUrl(){
        Assert.assertEquals("Invalid page"
                ,baseUrl+getRelativeUrl()
                ,webDriver.getCurrentUrl());
    }
    protected void checkUrlWithPattern(){
        Assert.assertThat("Invalid page",webDriver.getCurrentUrl()
                ,containsString(baseUrl+getRelativeUrl()));
    }

    protected void enterTextInToElement(WebElement webElement, String text){
        try{
            webElement.clear();
            webElement.sendKeys(text);
            logger.info( text + " was inputed");

        } catch (Exception e){
            printErrorAndStopTest(e);
        }
    }

    protected void printErrorAndStopTest(Exception e) {
        logger.error("Can not work with element " +  e);
        Assert.fail("Can not work with element " + e);
    }
    protected  void clickOnElement(WebElement webElement){
        try{
            webDriverWait10.until(ExpectedConditions.elementToBeClickable(webElement));
            webElement.click();
            logger.info("Element was clicked");

        }catch  (Exception e){
            printErrorAndStopTest(e);
        }
    }

    protected  boolean isElementDisplayed(WebElement webElement){
       try{
           boolean state = webElement.isDisplayed();
           if(state){
               logger.info(" Element is displayed");
           } else{
               logger.info("Element is not displayed");
           }
           return state;
       }catch (Exception e){
           logger.info("Element is not displayed");
           return false;
       }
    }
    protected  void selectTextInDropDown(WebElement dropDown, String text){
        try{
            Select select= new Select(dropDown);
            select.selectByVisibleText(text);
            logger.info(text + " was selected in dropDown");
        }catch (Exception e){
            printErrorAndStopTest(e);
        }
    }
    protected void selectValueInDropDown(WebElement dropDown, String value){
        try{
            Select select= new Select(dropDown);
            select.selectByValue (value);
            logger.info(value + " was selected in dropDown");
        }catch (Exception e){
            printErrorAndStopTest(e);
        }
    }
    // заглушка
    protected void waitChatToBeHide() {
        webDriverWait10
                .withMessage("Chat is no close")
                .until(ExpectedConditions.
                        invisibilityOfElementLocated(By.xpath(".//*[@id='chat-wrapper']")));
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

    }
    public void usersPressesKeyEnterTime(int numberOfTimes) {
        Actions actions = new Actions(webDriver);
        for (int i = 0; i < numberOfTimes; i++) {
            actions.sendKeys(Keys.ENTER).build().perform();
        }
    }
    public void usersPressesKeyTabTime(int numberOfTimes) {
        Actions actions = new Actions(webDriver);
        for (int i = 0; i < numberOfTimes; i++) {
            actions.sendKeys(Keys.TAB).build().perform();
        }

    }

    public void userOpensNewTab() {
        ((JavascriptExecutor)webDriver).executeScript("window.open()");
        ArrayList<String> tabs = new ArrayList<> (webDriver.getWindowHandles());
        webDriver.switchTo().window(tabs.get(1));
    }
}
