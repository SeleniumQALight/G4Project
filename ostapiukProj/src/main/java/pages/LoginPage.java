package pages;

import libs.TestData;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends ParentPage{
    @FindBy(xpath = ".//input[@name='username' and @placeholder='Username']")
    private WebElement inputLoginSignIn;
    @FindBy(xpath = ".//input[@placeholder='Password']")
    private WebElement inputPassWordSignIn;
    @FindBy(xpath = ".//button[text()='Sign In']")
    private WebElement buttonSignIn;
    @FindBy(xpath = ".//input[@id='username-register']")
    private WebElement inputLoginSignUp;
    @FindBy(xpath = ".//input[@id='email-register']")
    private WebElement inputEmailSignUp;
    @FindBy(xpath = ".//input[@id='password-register']")
    private WebElement inputPassWordSignUp;
    @FindBy(xpath = ".//button[text()='Sign up for OurApp']")
    private WebElement buttonSignUpForOurApp;



    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void openLoginPage(){
        try{
            webDriver.get("https://qa-complex-app-for-testing.herokuapp.com/");
            logger.info("Login page was opened");
        }catch (Exception e){
            logger.error("cann't open Login Page" + e);
            Assert.fail("cann't open Login Page" + e);
        }
    }

    public void enterLoginIntoInputLogin(String login) {
//        try{
//            inputLoginSignIn.clear();
//            inputLoginSignIn.sendKeys(login);
//            logger.info(login + " was inputted into Input Login");
//        }catch (Exception e){
//            printErrorAndStopTest(e);
//        }
        enterTextIntoElement(inputLoginSignIn, login);
    }

    public void enterPassWordIntoPassWord(String passWord) {
        enterTextIntoElement(inputPassWordSignIn, passWord);
    }

    public void clickOnButtonSignIn(){
        clickOnElement(buttonSignIn);
    }

    public void enterLoginIntoInputLoginSignUp(String login){
        enterTextIntoElement(inputLoginSignUp, login);
    }

    public void enterEmailIntoInputEmailSignUp(String email){
        enterTextIntoElement(inputEmailSignUp, email);
    }

    public void enterPassWordIntoPassSignUp(String passWord){
        enterTextIntoElement(inputPassWordSignUp, passWord);
    }

    public void clickOnButtonSignUp(){
        clickOnElement(buttonSignUpForOurApp);
    }

    public HomePage loginWithValidCred(){
        openLoginPage();
        enterLoginIntoInputLogin(TestData.VALID_LOGIN);
        enterPassWordIntoPassWord(TestData.VALID_PASS);
        clickOnButtonSignIn();
        return new HomePage(webDriver);
    }

    public HomePage loginWithUnValidCred(){
        openLoginPage();
        enterLoginIntoInputLoginSignUp(TestData.UNVALID_LOGIN);
        enterEmailIntoInputEmailSignUp(TestData.UNVALID_EMAIL);
        enterPassWordIntoPassSignUp(TestData.UNVALID_PASS);
        clickOnButtonSignUp();
        return new HomePage(webDriver);
    }
}
