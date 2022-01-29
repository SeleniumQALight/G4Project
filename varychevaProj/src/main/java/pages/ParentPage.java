package pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

abstract public class ParentPage {
    Logger logger = Logger.getLogger(getClass());
    WebDriver webDriver;
    WebDriverWait webDriverWait10, webDriverWait15;
    protected String baseUrl = "https://qa-complex-app-for-testing.herokuapp.com";

    public ParentPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
        webDriverWait10 = new WebDriverWait(webDriver, 10);
        webDriverWait15 = new WebDriverWait(webDriver, 15);
    }

    abstract String getRelativeUrl();

    protected void checkUrl() {
        assertEquals("Invalide page", baseUrl + getRelativeUrl(), webDriver.getCurrentUrl());

    }

    protected void checkUrlWithPattern() {
        assertThat("Invalide page", webDriver.getCurrentUrl(), containsString(baseUrl + getRelativeUrl()));
    }

    protected void enterTextIntoElement(WebElement webElement, String text) {
        try {
            webDriverWait15.until(ExpectedConditions.visibilityOf(webElement));
            webElement.clear();
            webElement.sendKeys(text);
            logger.info(text + " was inputted");
        } catch (Exception ex) {
            printErrorAndStopTest(ex);
        }
    }

    protected void clickOnElement(WebElement webElement) {
        try {
            webDriverWait10.until(ExpectedConditions.elementToBeClickable(webElement));
            webElement.click();
            logger.info("Element was clicked");
        } catch (Exception ex) {
            printErrorAndStopTest(ex);
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

    protected void waitChatTobeHide() {
        webDriverWait10.withMessage("Chat is no closed")
                .until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(".//*[@id='chat-wrapper']")));
    }

    protected void selectTextInDropDownByUI(WebElement dropdown, String text) {
        try {
            clickOnElement(dropdown);
            WebElement optionElement = webDriver.findElement(By.xpath(String.format(".//option[text()='%s']", text)));
            clickOnElement(optionElement);
            logger.info(String.format("Option with text: '%s' was selected", text));
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    protected void setCheckbox(WebElement checkbox, String value) {
        try {
            boolean state = checkbox.isSelected();
            if (value.equals("check") && !state) {
                clickOnElement(checkbox);
                logger.info("Checkbox was checked");
            } else if (value.equals("uncheck") && state) {
                clickOnElement(checkbox);
                logger.info("Checkbox was uncheck");
            } else {
                logger.info(String.format("State of checkbox is already '%s'", value));
            }
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    private void printErrorAndStopTest(Exception e) {
        logger.error("Can not work with element" + e);
        fail("Can not work with element" + e);
    }
}
