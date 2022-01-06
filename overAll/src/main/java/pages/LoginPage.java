package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import libs.TestData;

public class LoginPage extends ParentPage {
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
//            logger.info(login + " was inputted into Input Login");
//        }catch (Exception e){
//            printErrorAndStopTest(e);
//        }

        enterTextInToElement(inputLoginSingIn, login);
    }

    public void enterPassWordIntoInputPassWord(String passWord){
        enterTextInToElement(inputPassWordSingIn, passWord);
    }

    public void clickOnButtonSingIn(){
        clickOnElement(buttonSingIn);
    }

    public HomePage loginWithValidCred(){
        openLoginPage();
        enterLoginIntoInputLogin(TestData.VALID_LOGIN);
        enterPassWordIntoInputPassWord(TestData.VALID_PASS);
        clickOnButtonSingIn();
        return new HomePage(webDriver);
    }


}
