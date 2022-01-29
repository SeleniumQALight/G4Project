package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static libs.TestData.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class LoginPage extends ParentPage {

    @FindBy(xpath = ".//input[@name='username' and @placeholder='Username']")
    private WebElement inputLoginSignIn;

    @FindBy(xpath = ".//input[@placeholder='Password']")
    private WebElement inputPasswordSignIn;

    @FindBy(xpath = ".//button[text()='Sign In']")
    private WebElement buttonSignIn;

    @FindBy(xpath = ".//input[@id='username-register']")
    private WebElement inputLoginSignUp;

    @FindBy(xpath = ".//input[@id='password-register']")
    private WebElement inputPassWordSignUp;

    @FindBy(xpath = "//input[@id='password-register']")
    private WebElement inputPass;

    @FindBy(xpath = ".//input[@id='email-register']")
    private WebElement inputEmailSignUp;

    @FindBy(xpath = ".//button[@type='submit']")
    private WebElement buttonSignUp;

    @FindBy(xpath = "//label[@for='username-register']/..//div")
    private WebElement alertInvalidLogin;

    @FindBy(xpath = "//label[@for='email-register']/..//div")
    private WebElement alertInvalidEmail;

    @FindBy(xpath = "//label[@for='password-register']/..//div")
    private WebElement alertInvalidPassWord;

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void openLoginPage() {
        try {
            webDriver.get("https://qa-complex-app-for-testing.herokuapp.com/");
            logger.info("Login page was opened");
        } catch (Exception e) {
            logger.error("Can not open Login Page" + e);
            fail("Can not open Login Page" + e);
        }
    }

    public void enterLoginIntoInputLogin(String login) {
        enterTextIntoElement(inputLoginSignIn, login);
    }

    public void enterPassWordIntoInputPassWord(String passWord) {
        enterTextIntoElement(inputPasswordSignIn, passWord);
    }

    public void clickOnButtonSingIn() {
        clickOnElement(buttonSignIn);
    }

    public boolean isButtonSignInDisplayed() {
        try {
            return buttonSignIn.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void enterLoginIntoInputLoginSignUp(String login) {
        enterTextIntoElement(inputLoginSignUp, login);
    }

    public void enterPassWordIntoInputPassWordSignUp(String passWord) {
        enterTextIntoElement(inputPassWordSignUp, passWord);
    }

    public void enterEmailIntoInputEmailSignUp(String email) {
        enterTextIntoElement(inputEmailSignUp, email);
    }

    public void clickOnButtonSignUp() {
        clickOnElement(buttonSignUp);
    }

    public void checkErrorMessages() {
        assertEquals(SIGNUP_ERROR_EMAIL, getTextFromElement(alertInvalidEmail));
        assertEquals(SIGNUP_ERROR_LOGIN, getTextFromElement(alertInvalidLogin));
        assertEquals(SIGNUP_ERROR_PASSWORD, getTextFromElement(alertInvalidPassWord));
    }

    public LoginPage singUpWithInvalidData(String login, String password, String email) {
        enterLoginIntoInputLoginSignUp(login);
        enterPassWordIntoInputPassWordSignUp(password);
        enterEmailIntoInputEmailSignUp(email);
        clickOnButtonSignUp();
        return this;
    }

    public HomePage loginWithValidCred() {
        openLoginPage();
        enterLoginIntoInputLogin(VALID_LOGIN);
        enterPassWordIntoInputPassWord(VALID_PASS);
        clickOnButtonSingIn();
        return new HomePage(webDriver);
    }
}
