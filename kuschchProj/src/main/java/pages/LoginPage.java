package pages;

import libs.TestData;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends ParentPage{
    @FindBy(xpath = ".//input[@name='username' and @placeholder='Username']")
    private WebElement inputLoginSingIn;
    @FindBy(xpath = ".//input[@placeholder='Password']")
    private WebElement inputPassWordSingIn;
    @FindBy(xpath = ".//button[text()='Sign In']")
    private WebElement buttonSingIn;

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void openLoginPage(){
        try{
            webDriver.get("https://qa-complex-app-for-testing.herokuapp.com/");
            logger.info("Login page was opened");
        }catch (Exception e){
            logger.error("Can not open Login Page" + e);
            Assert.fail("Can not open Login Page" + e);
        }

    }

    public void enterLoginIntoInputLogin(String login) {
//        try{
//            inputLoginSingIn.clear();
//            inputLoginSingIn.sendKeys(login);
//            logger.info(login + " was inputed into Lolin");
//        }catch (Exception e){
//            printErrorAndStopTest(e);
//        }
        enterTextIntoElement(inputLoginSingIn, login);
    }

    public void enterPassWordIntoInputPassWord(String passWord){
//        try {
//            inputPassWordSingIn.clear();
//            inputPassWordSingIn.sendKeys(passWord);
//            logger.info(passWord + "was inputed");
//        }catch (Exception e){
//            printErrorAndStopTest(e);
//        }
        enterTextIntoElement(inputPassWordSingIn, passWord);
    }

    public void clickOnButtonSingIn(){
//        try{
//            buttonSingIn.click();
//            logger.info("Button Sign In was clicked");
//        }catch (Exception e){
//            printErrorAndStopTest(e);
//        }
        clickOnElement(buttonSingIn);
    }

    private void printErrorAndStopTest(Exception e) {
        logger.error("Can not work with element" +e);
        Assert.fail("Can not work with element" + e);
    }

    public HomePage loginWithValidCred(){
        openLoginPage();
        enterLoginIntoInputLogin(TestData.VALID_LOGIN);
        enterPassWordIntoInputPassWord(TestData.VALID_PASS);
        clickOnButtonSingIn();
        return new HomePage(webDriver);
    }

    public boolean errorMessageMainPage() {
        try {
            return webDriver.findElement(By.xpath(".//div[@Class = 'alert alert-danger text-center']")).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
