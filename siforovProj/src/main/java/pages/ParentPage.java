package pages;

import libs.TestData;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Locale;


public class ParentPage {
    Logger logger = Logger.getLogger(getClass());
    WebDriver webDriver;
    WebDriverWait webDriverWait10, webDriverWait15;


    public ParentPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
        webDriverWait10 = new WebDriverWait(webDriver,10);
        webDriverWait15 = new WebDriverWait(webDriver,15);
    }

    protected boolean elementIsVisible(WebElement webElement){
        try{
            if(webElement.isDisplayed()){
                logger.info("The element is displayed");
                return true;
            }
            logger.info("Element is not displayed");
            return false;
        }catch (Exception e){
            logger.info("Element isn't displayed");
            return false;
        }
    }

    protected void enterTextIntoElement(WebElement webElement, String text) {
        try {
            webDriverWait15.until(ExpectedConditions.visibilityOf(webElement));
            webElement.clear();
            webElement.sendKeys(text);
            logger.info(text+" was inputted ");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    protected void clickOnElement(WebElement webElement){
        try {
            webDriverWait10.until(ExpectedConditions.elementToBeClickable(webElement));
            webElement.click();
            logger.info("Element was clicked");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    protected boolean compareElementTextWithExpectedText(WebElement webElement){
        try {
            if (webElement.getText().equals(TestData.VALID_USERNAME.toLowerCase(Locale.ROOT))) {
                return true;
            }
        }catch (Exception e){
            printErrorAndStopTest(e);
            return false;
        }
        return false;
    }

    private void printErrorAndStopTest(Exception e) {
        logger.error("Can not work with element" + e);
        Assert.fail("Can not work with element" + e);
    }

    protected void selectTextInDD(WebElement dropDown, String text){
        try{
            Select select = new Select(dropDown);
            select.selectByVisibleText(text);
            logger.info(text+" was selected in DD");
        }catch(Exception e){
            printErrorAndStopTest(e);
        }
    }

    protected void selectTextInDropDownByUI(List<WebElement> selectOptionsList, String textOfOption){
        try{
            for (int i = 0; i < selectOptionsList.size(); i++) {
                if (selectOptionsList.get(i).getText().equals(textOfOption)) {
                    selectOptionsList.get(i).click();
                    logger.info("The " + textOfOption + "has been clicked");
                    break;
                }else{
                    if(i == selectOptionsList.size()-1){
                        logger.info("There is no option which matches "+textOfOption);
                    }
                }
            }
        }catch (Exception e){
            printErrorAndStopTest(e);
        }
    }

    protected void selectValueInDropDown(WebElement dropDown, String value){
        try{
            Select select = new Select(dropDown);
            select.selectByValue(value);
            logger.info(value+" was selected in DD");
        }catch(Exception e){
            printErrorAndStopTest(e);
        }
    }
}
