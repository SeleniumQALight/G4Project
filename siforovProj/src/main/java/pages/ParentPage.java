package pages;

import libs.ConfigProperties;
import libs.TestData;
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
import java.util.List;
import java.util.Locale;

import static org.hamcrest.CoreMatchers.containsString;


abstract public class ParentPage {
    Logger logger = Logger.getLogger(getClass());
    WebDriver webDriver;
    WebDriverWait webDriverWait10, webDriverWait15;
    public static ConfigProperties configProperties = ConfigFactory.create(ConfigProperties.class);
    protected String baseUrl = configProperties.base_url();


    public ParentPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        //PageFactory.initElements(webDriver, this);
        PageFactory.initElements(new HtmlElementDecorator(new HtmlElementLocatorFactory(webDriver)),this);
        webDriverWait10 = new WebDriverWait(webDriver, configProperties.TIME_FOR_DFFAULT_WAIT());
        webDriverWait15 = new WebDriverWait(webDriver, configProperties.TIME_FOR_EXPLICIT_WAIT_LOW());
    }

    protected void checkUrl() {
        Assert.assertEquals("Invalid page"
                , baseUrl + getRelativeUrl()
                , webDriver.getCurrentUrl());
    }

    protected void checkUrlWithPattern() {
        Assert.assertThat("Invalid Page"
                , webDriver.getCurrentUrl()
                , containsString(baseUrl + getRelativeUrl()));
    }

    abstract String getRelativeUrl();

    protected boolean elementIsVisible(WebElement webElement) {
        try {
            if (webElement.isDisplayed()) {
                logger.info("The element is displayed");
                return true;
            }
            logger.info("Element is not displayed");
            return false;
        } catch (Exception e) {
            logger.info("Element isn't displayed");
            return false;
        }
    }

    protected void enterTextIntoElement(WebElement webElement, String text) {
        try {
            webDriverWait15.until(ExpectedConditions.visibilityOf(webElement));
            webElement.clear();
            webElement.sendKeys(text);
            logger.info(text + " was inputted "+ getElementName(webElement));
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    private String getElementName(WebElement webElement) {
        String elementName = "";
        if(webElement instanceof TypifiedElement){
            elementName = " '" +((TypifiedElement)webElement).getName() + "' ";
        }
        return elementName;
    }

    protected void clickOnElement(WebElement webElement) {
        try {
            webDriverWait10.until(ExpectedConditions.elementToBeClickable(webElement));
            webElement.click();
            logger.info("Element was clicked" + getElementName(webElement));
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }
    // added extra method for a safe click on element
    protected void clickOnElement(String formattedLocator){
        try{
            WebElement element = webDriver.findElement(By.xpath(formattedLocator));
            clickOnElement(element);
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    protected boolean compareElementTextWithExpectedText(WebElement webElement) {
        try {
            if (webElement.getText().equals(TestData.VALID_USERNAME.toLowerCase(Locale.ROOT))) {
                return true;
            }
        } catch (Exception e) {
            printErrorAndStopTest(e);
            return false;
        }
        return false;
    }

    protected void selectTextInDD(WebElement dropDown, String text) {
        try {
            Select select = new Select(dropDown);
            select.selectByVisibleText(text);
            logger.info(text + " was selected in DD");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    //ClassWork
    protected void findElementByTextWithinSpaceOfSimilar(List<WebElement> webElements, String postTitle) {
        try {
            for (int i = 0; i < webElements.size(); i++) {
                if (webElements.get(i).getText().equals(postTitle)) {
                    logger.info("The " + postTitle + "has been clicked");
                    break;
                } else {
                    if (i == webElements.size() - 1) {
                        logger.info("There is no option which matches " + postTitle);
                    }
                }
            }
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    protected void selectTextInDropDownByUI(List<WebElement> selectOptionsList, String textOfOption) {
        try {
            for (int i = 0; i < selectOptionsList.size(); i++) {
                if (selectOptionsList.get(i).getText().equals(textOfOption)) {
                    selectOptionsList.get(i).click();
                    logger.info("The " + textOfOption + "has been clicked");
                    break;
                } else {
                    if (i == selectOptionsList.size() - 1) {
                        logger.info("There is no option which matches " + textOfOption);
                    }
                }
            }
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    protected void waitChatToBeHide() {
        webDriverWait10
                .withMessage("Chat is not closed")
                .until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(".//*[@id='chat-wrapper']")));
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
        logger.error("Can not work with element" + e);
        Assert.fail("Can not work with element" + e);
    }
}
