package pages;

import libs.TestData;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends ParentPage {

    @FindBy(xpath = ".//input[@name='username' and @placeholder='Username']")
    private WebElement inputLoginSignIn;

    @FindBy(xpath = ".//input[@placeholder='Password']")
    private WebElement inputPasswordSignIn;

    @FindBy(xpath = ".//button[text()='Sign In']")
    private WebElement buttonSignIn;

    @FindBy(xpath = ".//input[@id = 'username-register']")
    private WebElement inputLoginSignUp;

    @FindBy(xpath = ".//input[@id = 'password-register']")
    private WebElement inputPasswordSignUp;

    @FindBy(xpath = ".//input[@id = 'email-register']")
    private WebElement inputEmailSignUp;

    @FindBy(xpath = ".//button[@type = 'submit']")
    private WebElement buttonSignUp;

    @FindBy(xpath = ".//div[text() = 'Username must be at least 3 characters.']")
    private WebElement errorSignUpLoginValidation;

    @FindBy(xpath = ".//div[text() = 'You must provide a valid email address.']")
    private WebElement errorSignUpEmailValidation;

    @FindBy(xpath = ".//div[text() = 'Password must be at least 12 characters.']")
    private WebElement errorSignUpPasswordValidation;



    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void openLoginPage(){
        try {
            webDriver.get("https://qa-complex-app-for-testing.herokuapp.com/");
            logger.info("Login page was opened");
        }catch (Exception e){
            logger.error("Can not open Login Page" + e);
            Assert.fail("Can not open Login Page" + e);
        }
    }

    public void enterLoginIntoInputLogin(String login) {
//        try{
//            inputLoginSignIn.clear();
//            inputLoginSignIn.sendKeys(login);
//            logger.info(login + "was inputted into Input Login");
//        }catch (Exception e) {
//            printErrorAndStopTest(e);
//        }
        enterTextInToElement(inputLoginSignIn, login);
    }

    public void enterPassWordIntoInputPassWord(String password){
        enterTextInToElement(inputPasswordSignIn, password);
    }

    public void enterSignUpLogin(String login){
        enterTextInToElement(inputLoginSignUp, login);
    }

    public void enterSignUpEmail(String email){
        enterTextInToElement(inputEmailSignUp, email);
    }

    public void enterSignUpPassword(String password){
        enterTextInToElement(inputPasswordSignUp, password);
    }

    public void clickOnButtonSignIn(){
        clickOnElement(buttonSignIn);
    }

    public void clickOnButtonSignUp(){
        clickOnElement(buttonSignUp);
    }

    public HomePage loginWithValidCred(){
        openLoginPage();
        enterLoginIntoInputLogin(TestData.VALID_LOGIN);
        enterPassWordIntoInputPassWord(TestData.VALID_PASS);
        clickOnButtonSignIn();
        return new HomePage(webDriver);
    }

    public boolean isButtonSignInDisplayed(){
        return isElementDisplayed(buttonSignIn);
    }

    public boolean isErrorLoginValidationForSignUpDisplayed(){
       return isElementDisplayed(errorSignUpLoginValidation);
    }

    public boolean isErrorEmailValidationForSignUpDisplayed(){
       return isElementDisplayed(errorSignUpEmailValidation);
    }

    public boolean isErrorPasswordValidationForSignUpDisplayed(){
        return isElementDisplayed(errorSignUpPasswordValidation);
    }

}
