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

    public ParentPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    //    PageFactory.initElements(webDriver, this);
        PageFactory.initElements( new HtmlElementDecorator(                     // for yandex
                        new HtmlElementLocatorFactory(webDriver)),this);

        webDriverWait10 = new WebDriverWait(webDriver, configProperties.TIME_FOR_DFFAULT_WAIT());
        webDriverWait15 = new WebDriverWait(webDriver, configProperties.TIME_FOR_EXPLICIT_WAIT_LOW());
    }

    abstract String getRelativeUrl();

    protected void checkUrl() {
        Assert.assertEquals("Invalid page",
                            baseUrl + getRelativeUrl(),
                                    webDriver.getCurrentUrl());
    }

    protected void checkUrlWithPattern() {
        Assert.assertThat("Invalid page",
                                webDriver.getCurrentUrl(),
                                containsString(baseUrl+getRelativeUrl()));
    }

    protected void enterTextToElement(WebElement element, String text) {
        try {
            webDriverWait15.until(ExpectedConditions.visibilityOf(element));
            element.clear();
            element.sendKeys(text);
            logger.info(text + " was inputted " + getElementName(element));
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    private String getElementName(WebElement element) {
        String elementName = "";
        if (element instanceof TypifiedElement){
            elementName = " '" + ((TypifiedElement) element).getName() + "' ";
        }
        return elementName;
    }

    protected void clickOnElement(WebElement element) {
        try {
            webDriverWait10.until(ExpectedConditions.elementToBeClickable(element));
            element.click();
            logger.info(getElementName(element) + " element was clicked");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    private void printErrorAndStopTest(Exception e) {
        logger.error("Can not work with element " + e);
        Assert.fail("Can not work with element " + e);
    }

    protected void checkTextFromElement(String text, WebElement element) {
        try {
            Assert.assertEquals(text, element.getText());
            logger.info("Text '" + text + "' is correct");
        } catch (Exception e) {
            logger.error("Text '" + text + "' is not correct" + e);
            Assert.fail("Text '" + text + "' is not correct" + e);
        }
    }

    protected boolean isElementDisplayed(WebElement element) {
        try {
            boolean state = element.isDisplayed();
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

    protected void selectTextInDropDown(WebElement dropDown, String text) {
        try {
            Select select = new Select(dropDown);
            select.selectByVisibleText(text);
            logger.info(text + " was selected in DD");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    protected void selectValueInProdDown(WebElement dropDown, String value) {
        try {
            Select select = new Select(dropDown);
            select.selectByValue(value);
            logger.info(value + " was selected in DD");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    protected void selectTextInDropDownByUI(WebElement dropDown, String option, String text) {
        try {
            clickOnElement(dropDown);
            WebElement onePersonalOption = webDriver.findElement(By.xpath(String.format(option, text)));
            onePersonalOption.click();
            logger.info(text + " was selected at DropDown menu");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    protected void setCheckboxCondition(WebElement element, String checkboxCondition) {
        boolean currentElementCondition = element.isSelected();
        if (checkboxCondition == "check" || checkboxCondition == "uncheck") {
            switch (checkboxCondition) {
                case ("check"):
                    if (currentElementCondition) {
                        logger.info("Checkbox condition is correct");
                    } else {
                        clickOnElement(element);
                        logger.info("Checkbox condition was changed on condition - " + checkboxCondition);
                    }
                    break;
                case ("uncheck"):
                    if (!currentElementCondition) {
                        logger.info("Checkbox condition is correct");
                    } else {
                        clickOnElement(element);
                        logger.info("Checkbox condition was changed on condition - " + checkboxCondition);
                    }
                    break;
            }
        } else {
            logger.error("Wrong 'checkboxCondition' - " + checkboxCondition + ". Should be: 'check' or 'uncheck'");
            Assert.fail("Wrong 'checkboxCondition' - " + checkboxCondition + ". Should be: 'check' or 'uncheck'");
        }
    }

    protected void waitChatToBeHide() {
        webDriverWait10
                .withMessage("Chat is displayed")
                .until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='chat-wrapper']")));
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
        ArrayList<String> tabs = new ArrayList<>(webDriver.getWindowHandles());
        webDriver.switchTo().window(tabs.get(1));
    }
}
