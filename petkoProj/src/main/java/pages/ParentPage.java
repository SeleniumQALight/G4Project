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

    WebDriverWait webDriverWait5, webDriverWait15;

    public static ConfigProperties configProperties = ConfigFactory.create(ConfigProperties.class);
    protected String baseUrl = configProperties.base_url();

    public ParentPage(WebDriver webDriver) {
        this.webDriver = webDriver;
       // PageFactory.initElements(webDriver, this); -- if we are working with WebElements
        PageFactory.initElements(
                new HtmlElementDecorator(new HtmlElementLocatorFactory(webDriver)),this); //can work with html elements by yandex

        webDriverWait5 = new WebDriverWait(webDriver, configProperties.TIME_FOR_DEFAULT_WAIT());
        webDriverWait15 = new WebDriverWait(webDriver, configProperties.TIME_FOR_EXPLICIT_WAIT_LOW());
    }

    abstract String getRelativeUrl();

    protected void  checkUrl(){
        Assert.assertEquals("Invalid page" //message if failed
                , baseUrl + getRelativeUrl()  //expected result
                ,webDriver.getCurrentUrl()); // actual result
    }

    protected void checkUrlWithPattern(){
        Assert.assertThat("Invalid page" //message
                ,webDriver.getCurrentUrl() //act result
                ,containsString(baseUrl + getRelativeUrl())); //exp result with condition
    }

    protected void enterTextInToElement(WebElement webElement, String text) {
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
        if(webElement instanceof TypifiedElement){
            elementName = " '" + ((TypifiedElement) webElement).getName() + "' ";
        }
        return elementName;
    }

    protected void clickOnElement(WebElement webElement) {
        try {
            webDriverWait5.until(ExpectedConditions.elementToBeClickable(webElement));
            webElement.click();
            logger.info(getElementName(webElement) + " Element was clicked");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    protected boolean isElementDisplayed(WebElement webElement) {
        try {
            if (webElement.isDisplayed()) {
                logger.info("Element is displayed");
                return true;
            } else {
                logger.info("Element is not displayed");
                return false;
            }
        } catch (Exception e) {
            logger.info("The element was not found");
            return false;
        }
    }

    protected void selectTextInDropDown(WebElement dropDown, String text) {
        try {
            Select select = new Select(dropDown);
            select.selectByVisibleText(text);
            logger.info(text + " was selected in DD");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    protected void selectValueInDropDown(WebElement dropDown, String value) {
        try {
            Select select = new Select(dropDown);
            select.selectByValue(value);
            logger.info(value + " was selected in DD");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    protected void checkBoxActions(String state, WebElement webElement) {
        try {
            if (state.equalsIgnoreCase("check") && webElement.isSelected()) {
                logger.info("Checkbox has already checked");
            } else if (state.equalsIgnoreCase("check") && !webElement.isSelected()) {
                webElement.click();
                logger.info("The state was changed from uncheck to check");
            } else if (state.equalsIgnoreCase("uncheck") && webElement.isSelected()) {
                webElement.click();
                logger.info("The state was changed from check to uncheck");
            } else if (state.equalsIgnoreCase("uncheck") && !webElement.isSelected()) {
                logger.info("Checkbox has already unchecked");
            } else {
                Assert.fail("You are inputted incorrect state. Please use 'check' or 'uncheck'");
            }
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    protected void waitChatToBeHide() {
        webDriverWait5
                .withMessage("Chat is not closed")
                .until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(".//*[@id='chat-wrapper']")));
    }

    //Pressing on keyboard
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
// Opening new tab and transition between them
    public void userOpensNewTab() {
        ((JavascriptExecutor)webDriver).executeScript("window.open()");
        ArrayList<String> tabs = new ArrayList<> (webDriver.getWindowHandles());
        webDriver.switchTo().window(tabs.get(1));
    }

    private void printErrorAndStopTest(Exception e) {
        logger.error("Can not work with element " + e);
        Assert.fail("Can not work with element " + e);
    }
}
