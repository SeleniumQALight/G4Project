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
import ru.yandex.qatools.htmlelements.element.TypifiedElement;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementLocatorFactory;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.containsString;


abstract public class ParentPage {
    Logger logger = Logger.getLogger(getClass());
    WebDriver webDriver;
    WebDriverWait webDriverWait10, webDriverWait15;
    public static ConfigProperties configProperties = ConfigFactory.create(ConfigProperties.class);
    protected String baseUrl = configProperties.base_url();

    private String selectOptionLocator = ".//*[text()='%s']";
    private String findCheckbox = ".//*[@type='checkbox'and@id='%s']";

    public ParentPage(WebDriver webDriver) {
        this.webDriver = webDriver;
       // PageFactory.initElements(webDriver, this);
        PageFactory.initElements(
                new HtmlElementDecorator(
                        new HtmlElementLocatorFactory(webDriver))
                ,this);
        webDriverWait10 = new WebDriverWait(webDriver, configProperties.TIME_FOR_DFFAULT_WAIT());
        webDriverWait15 = new WebDriverWait(webDriver, configProperties.TIME_FOR_EXPLICIT_WAIT_LOW());
        baseUrl = configProperties.base_url().replace("[env]", System.getProperty("env", "qa"));
    }

    abstract String getRelativeUrl();

    protected void checkUrl(){
        Assert.assertEquals("Invalid page", baseUrl + getRelativeUrl(), webDriver.getCurrentUrl());
    }

    protected void checkUrlWithPattern(){
      Assert.assertThat("Invalid page", webDriver.getCurrentUrl(), containsString(baseUrl + getRelativeUrl()));
    }


    protected void enterTextIntoElement(WebElement webElement, String text) {
        try {
            webDriverWait15.until(ExpectedConditions.visibilityOf(webElement));
            webElement.clear();
            webElement.sendKeys(text);
            logger.info(text + " was inputted " + getElementName(webElement));
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    private String getElementName(WebElement webElement) {
        String elementName = "";
        if (webElement instanceof TypifiedElement) {
            elementName = " '" + ((TypifiedElement) webElement).getName()  + "' ";
        }

        return elementName;
    }


    protected void clickOnElement(WebElement webElement) {
        try {
            webDriverWait10.until(ExpectedConditions.elementToBeClickable(webElement));
            webElement.click();
            logger.info(getElementName(webElement) + " Element was clicked");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    protected void clickOnElement (String xpathLocator) {

        try {WebElement webElement = webDriver.findElement(By.xpath(xpathLocator));
            clickOnElement(webElement);
        } catch (Exception e) {
            logger.info("Element was not found");
        }
    }

    protected boolean isElementDisplayed(WebElement webElement) {
        try {
            boolean state = webElement.isDisplayed();
            if (state) {
                logger.info("Element is displayed");
            } else {
                logger.info("Element is not displayed");
            }
            return state;
        } catch (Exception e) {
            logger.info("Element is not displayed");
            return false;

        }
    }

    protected void selectTextInDropDown(WebElement dropDown, String text){
        try{
            Select select = new Select(dropDown);
            select.deselectByVisibleText(text);
            logger.info(text + " was selected in DropDown");
        }catch (Exception e){
            printErrorAndStopTest(e);
        }
    }

    protected void selectValueInDropDown(WebElement dropDown, String value){
        try{
            Select select = new Select(dropDown);
            select.selectByValue(value);
            logger.info(value + " was selected in DropDown");
        }catch (Exception e){
            printErrorAndStopTest(e);
        }
    }


    protected void selectTextInDropDownByUI(WebElement webElement, String text) {
        try {
            webDriverWait10.until(ExpectedConditions.elementToBeClickable(webElement));
            webElement.click();
            logger.info(webElement + "Element was clicked");
            WebElement optionForSelect = webDriver.findElement(By.xpath(String.format(selectOptionLocator, text)));
            optionForSelect.click();
            logger.info(text + " was chosen");
        }catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

protected void setValueInCheckbox(String necessaryCheckbox, String statusForCheckbox) {
    try { WebElement checkbox = webDriver.findElement(By.xpath(String.format(findCheckbox, necessaryCheckbox)));
        if (statusForCheckbox.equalsIgnoreCase("check") && !checkbox.isSelected()){
            checkbox.click();
            logger.info("Checkbox was checked");
        } else if (statusForCheckbox.equalsIgnoreCase("check") && checkbox.isSelected()){
            logger.info("Checkbox is already checked");
        } else if (statusForCheckbox.equalsIgnoreCase("uncheck") && checkbox.isSelected()){
            checkbox.click();
            logger.info("Checkbox was unchecked");
        } else if (statusForCheckbox.equalsIgnoreCase("uncheck") && !checkbox.isSelected()) {
            logger.info("Checkbox is already unchecked");
        }
        } catch(Exception e){
            printErrorAndStopTest(e);
        }
    }

    protected void waitChatToBeHidden(){
      webDriverWait10.withMessage("Chat is not closed").until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(".//*[@id='chat-wrapper']")));
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


    private void printErrorAndStopTest(Exception e) {
        logger.error("Can not work with element " + e);
        Assert.fail("Can not work with element " + e);
    }

    protected boolean isMessageForFieldDisplayed(WebElement webElement){
        try{
            return webElement.isDisplayed();
        }catch (Exception e){
            return false;
        }
    }
}