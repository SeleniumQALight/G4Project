package pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ParentPage {
    Logger logger = Logger.getLogger(getClass());
    WebDriver driver;
    WebDriverWait webDriverWait10, webDriverWait15;
    public ParentPage(WebDriver driver) {
        this.driver = driver;
        //inicializiruem elementi na stranicah (izuchity PageFactory)
        PageFactory.initElements(driver, this);
        webDriverWait10 = new WebDriverWait(driver, 10);
        webDriverWait15 = new WebDriverWait(driver, 15);
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
        //TODO wait chat
        try {
            Thread.sleep(1000);
        }catch (Exception e){
           e.printStackTrace();
        }
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

}
