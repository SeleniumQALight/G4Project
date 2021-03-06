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
    WebDriverWait webDriverWait10, WebDriverWait15;
    public static ConfigProperties configProperties = ConfigFactory.create(ConfigProperties.class);
    protected String baseUrl;

    public ParentPage(WebDriver webDriver) {
        this.webDriver = webDriver;
       // PageFactory.initElements(webDriver, this);
        PageFactory.initElements(
                new HtmlElementDecorator(
                        new HtmlElementLocatorFactory(webDriver))
                ,this);
        webDriverWait10 = new WebDriverWait(webDriver,configProperties.TIME_FOR_DFFAULT_WAIT());
        WebDriverWait15 = new WebDriverWait(webDriver, configProperties.TIME_FOR_EXPLICIT_WAIT_LOW());
        baseUrl = configProperties.base_url().replace("[env]", System.getProperty("env", "qa"));
    }

    abstract String getRalativeUrl();

    protected void checkUrl(){
        Assert.assertEquals("Invalid page"
                ,baseUrl + getRalativeUrl()
                , webDriver.getCurrentUrl());
    }

    protected void checkUrlWithPattern(){
        Assert.assertThat("Invalid page"
                , webDriver.getCurrentUrl()
                , containsString(baseUrl + getRalativeUrl()));
    }

    protected void enterTextIntoElement(WebElement webElement, String text) {
        try {
            WebDriverWait15.until(ExpectedConditions.visibilityOf(webElement));
            webElement.clear();
            webElement.sendKeys(text);
            logger.info(text + " was inputed " + getElementName(webElement));
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    private String getElementName(WebElement webElement) {
        String elementName = "";
        if (webElement instanceof TypifiedElement) {
            elementName = " '" + ((TypifiedElement) webElement).getName() + "' ";
        }
        return elementName;
    }

    protected void clickOnElement(WebElement webElement) {
        try {
            webDriverWait10.until(ExpectedConditions.elementToBeClickable(webElement));
            webElement.click();
            logger.info("Element was clicked");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    protected boolean isElementDisplayed(WebElement webElement) {
        try {
            boolean state = webElement.isDisplayed();
            if(state) {
                logger.info("Element is displayed");
            } else {
                logger.info("Element is not displayed");
            }
            return state;
        } catch (Exception e) {
            logger.info("Element is notdisplayed");
            return false;
        }
    }

    protected void selectTextInDropDown(WebElement dropDown, String text){
        try {
            Select select = new Select(dropDown);
            select.selectByVisibleText(text);
            logger.info(text + " was selected in DD");
        }catch (Exception e){
            printErrorAndStopTest(e);
        }
    }

    protected void sectValueInDropdown(WebElement dropDown, String value){
        try {
            Select select = new Select(dropDown);
            select.selectByValue(value);
            logger.info(value + " was selected in DD");
        }catch (Exception e){
            printErrorAndStopTest(e);
        }
    }

    protected void waitChatTobeHide(){
        webDriverWait10
                .withMessage("Chat is not closed")
                .until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(".//*[@id='chat-wrapper']")));
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

    protected void checkIsElementDisplayed(String message, WebElement webElement){
        Assert.assertTrue(message, isElementDisplayed(webElement));
    }

    protected void checkBoxCondition (WebElement  checkBox ,String  checkBoxCondition ){
        boolean checkBoxState = checkBox.isSelected();
        switch (checkBoxCondition){
            case("check"):
                if (checkBoxState) {
                    logger.info("checkBox is selekted");
                }else {
                    clickOnElement(checkBox);
                    logger.info("checkBox is clicked and have status selekted");}
                break;

            case ("uncheck"):
                if (!checkBoxState) {
                    logger.info("checkBox is unselekted");
                }else {
                    clickOnElement(checkBox);
                    logger.info("checkBox is clicked and have status unselekted");}
                break;
            default:clickOnElement(checkBox);
                logger.info("checkBox is clicked");
        }
    }

    protected void checkTextInElem(String text, WebElement element) {
        try {
            Assert.assertEquals(text, element.getText());
            logger.info("Text '" + text + "' right");
        } catch (Exception e) {
            logger.error("Text '" + text + "' not right" + e);
            Assert.fail("Text '" + text + "' not right" + e);
        }
    }
}
