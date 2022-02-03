package pages;

import libs.ConfigProperties;
import org.aeonbits.owner.ConfigFactory;
import org.apache.log4j.Logger;
import org.hamcrest.core.StringContains;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.containsString;

abstract public class ParentPage {
    Logger logger = Logger.getLogger(getClass());
    WebDriver driver;
    WebDriverWait webDriverWait10, webDriverWait15;
    public static ConfigProperties configProperties = ConfigFactory.create(ConfigProperties.class);
    protected String baseUrl = configProperties.base_url();

    public ParentPage(WebDriver driver) {
        this.driver = driver;
        //inicializiruem elementi na stranicah (izuchity PageFactory)
        PageFactory.initElements(driver, this);
        webDriverWait10 = new WebDriverWait(driver, configProperties.TIME_FOR_DFFAULT_WAIT());
        webDriverWait15 = new WebDriverWait(driver, configProperties.TIME_FOR_EXPLICIT_WAIT_LOW());
    }

    abstract String getRelativeUrl();

    protected void checkUrl(){
        Assert.assertEquals("Invalid page is open", baseUrl + getRelativeUrl(), driver.getCurrentUrl());
    }

    protected void checkUrlWithPattern(){
        Assert.assertThat("Invalid page is open", driver.getCurrentUrl(), containsString(baseUrl + getRelativeUrl()));
    }

    protected void enterTextIntoElement(WebElement webElement, String text){
        try {
            webDriverWait15.until(ExpectedConditions.visibilityOf(webElement));
            webElement.clear();
            webElement.sendKeys(text);
            logger.info(text + " was entered");
        }catch (Exception e){
            printErrorAndStopTest(e);
        }
    }

    public void usersPressesKeyEnterTime(int numberOfTimes) {
        Actions actions = new Actions(driver);
        for (int i = 0; i < numberOfTimes; i++) {
            actions.sendKeys(Keys.ENTER).build().perform();
        }
    }
    public void usersPressesKeyTabTime(int numberOfTimes) {
        Actions actions = new Actions(driver);
        for (int i = 0; i < numberOfTimes; i++) {
            actions.sendKeys(Keys.TAB).build().perform();
        }

    }

    public void userOpensNewTab() {
        ((JavascriptExecutor)driver).executeScript("window.open()");
        ArrayList<String> tabs = new ArrayList<> (driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
    }

    void printErrorAndStopTest(Exception e) {
        logger.error("Can't work with element" + e);
        Assert.fail("Can't work with element" + e);
    }


   protected void clickOnElement(WebElement webElement){
        try{
            webDriverWait10.until(ExpectedConditions.elementToBeClickable(webElement));
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

    protected boolean isElementDisplayed(WebElement element){
        try {
            boolean state = element.isDisplayed();
            if(state){
                logger.info("Element is displayed");
            }else {
                logger.info("Element isn't displayed");
            }
            return state;
        }catch (Exception e){
            logger.info("Element is not displayed " + e);
            return false;
        }
    }

    protected void selectTextInDropDown(WebElement dropDown, String text){
        try {
            Select select = new Select(dropDown);
            select.selectByVisibleText(text);
            logger.info(text + " was selected in dropdown");
        }catch (Exception e){
            printErrorAndStopTest(e);
        }
    }

    protected void selectValueInDropdown(WebElement dropDown, String value){
        try {
            Select select = new Select(dropDown);
            select.selectByValue(value);
            logger.info(value + " was selected in dropdown");
        }catch (Exception e){
            printErrorAndStopTest(e);
        }
    }

    protected void waitChatToBeHide(){
        webDriverWait10.withMessage("Chat isn't closed")
                .until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(".//*[@id='chat-wrapper']")));
    }

    protected void checkBoxActions(String state, WebElement element) {
        try {
            if (state.equalsIgnoreCase("check") && element.isSelected()){
                logger.info("Checkbox is checked");
            }else if(state.equalsIgnoreCase("check") && !element.isSelected()){
                element.click();
                logger.info("Checkbox was unchecked but now it's checked");
            }else if (state.equalsIgnoreCase("uncheck") && element.isSelected()){
                element.click();
                logger.info("Checkbox was checked but now it's unchecked");
            }else if(state.equalsIgnoreCase("uncheck") && !element.isSelected()){
                logger.info("Checkbox is unchecked");
            }else {
                Assert.fail("Entered value is incorrect. Please enter correct value (check or uncheck) and repeat test");
            }

        }catch (Exception e){
            printErrorAndStopTest(e);
        }

    }

    protected void addTextToElement(WebElement element, String text){
        webDriverWait10.until(ExpectedConditions.visibilityOf(element));
        logger.info(element.getAttribute("value") + " (title before update)");
        element.sendKeys(text);
        logger.info(element.getAttribute("value") + " (title after update)");
    }

}
