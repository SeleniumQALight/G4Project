package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static libs.TestData.*;
import static org.junit.Assert.assertEquals;

public class SignUpPage extends ParentPage {

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

    public SignUpPage(WebDriver webDriver) {
        super(webDriver);
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

    public SignUpPage singUpWithInvalidData() {
        enterLoginIntoInputLoginSignUp(INVALID_LOGIN);
        enterPassWordIntoInputPassWordSignUp(INVALID_PASS);
        enterEmailIntoInputEmailSignUp(INVALID_EMAIL);
        clickOnButtonSignUp();
        return this;
    }
}
