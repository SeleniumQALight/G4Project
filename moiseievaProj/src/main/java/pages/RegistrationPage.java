package pages;

import libs.TestData;
import libs.Util;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegistrationPage extends ParentPage {
    LoginPage loginPage = new LoginPage(webDriver);

    @FindBy(xpath = "//input[@id='username-register']")
    private WebElement inputUserName;
    @FindBy(xpath = "//input[@id='email-register']")
    private WebElement inputEmailField;
    @FindBy(xpath = "//input[@id='password-register']")
    private WebElement inputPass;
    @FindBy(xpath = "//button[@type='submit']")
    private WebElement buttonSubmit;
    @FindBy(xpath = "//label[@for='username-register']/..//div")
    private WebElement alertNameError;
    @FindBy(xpath = "//label[@for='email-register']/..//div")
    private WebElement alertEmailError;
    @FindBy(xpath = "//label[@for='password-register']/..//div")
    private WebElement alertPassError;

    public RegistrationPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/";
    }

    public void enterNameIntoInputRegistration(String name) {
        enterTextToElement(inputUserName, name);
    }

    public void enterPasswordIntoRegistration(String password) {

        enterTextToElement(inputPass, password);
    }

    public void enterEmailIntoInputRegistration(String email) {
        enterTextToElement(inputEmailField, email);
    }

    public void clickOnSignUpButton() {
        clickOnElement(buttonSubmit);
    }

    public void userInvalidRegistration() {
        loginPage.openLoginPage();
        enterNameIntoInputRegistration(TestData.INVALID_USER_NAME);
        enterPasswordIntoRegistration(TestData.INVALID_PASS);
        enterEmailIntoInputRegistration(TestData.INVALID_EMAIL);
        clickOnSignUpButton();
    }

    public void isRegistrationErrorsDisplayed() {
        checkTextFromElement(TestData.ERROR_NAME_REGISTER, alertNameError);
        checkTextFromElement(TestData.ERROR_PASS_REGISTER, alertPassError);
        checkTextFromElement(TestData.ERROR_EMAIL_REGISTER, alertEmailError);
    }
}
