package pages;

import libs.TestData;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends ParentPage {
    //Sign IN
    @FindBy(xpath = ".//input[@name='username' and @placeholder='Username']")
    private WebElement inputLoginSignIn;

    @FindBy(xpath = ".//input[@placeholder='Password']")
    private WebElement inputPasswordSignIn;

    @FindBy(xpath = ".//button[text()='Sign In']")
    private WebElement buttonSignIn;
    // Sign Up Form
    @FindBy(xpath = ".//*[@type='submit']")
    private WebElement buttonSignUPForOurApp;

    @FindBy(xpath = ".//input[@id='username-register']")
    private WebElement inputUserNameInSignUpForm;

    @FindBy(xpath = ".//input[@id='email-register']")
    private WebElement inputEmailInSignUpForm;

    @FindBy(xpath = ".//input[@id='password-register']")
    private WebElement inputUserPassWordInSignUpForm;

    @FindBy(xpath = ".//div[text()='Username must be at least 3 characters.']")
    private WebElement validationUserNameMessage;

    @FindBy(xpath = ".//div[text()='You must provide a valid email address.']")
    private WebElement validationUserEmailMessage;

    @FindBy(xpath = ".//div[text()='Password must be at least 12 characters.']")
    private WebElement validationUserPasswordMessage;


    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void openLoginPage() {
        try {
            webDriver.get("https://qa-complex-app-for-testing.herokuapp.com/");
            logger.info("Login Page was opened");

        } catch (Exception e) {
            logger.error("Can not open Login Page " + e);
            //Assert message will send in the Test Report
            Assert.fail("Can not open Login Page " + e);
        }
    }

    public void enterLoginIntoInputLogin(String login) {
//        try {
//            inputLoginSignIn.clear();
//            inputLoginSignIn.sendKeys(login);
//            logger.info(login + " was inputted into Input Login field");
//
//        } catch (Exception e) {
//            printErrorAndStopTest(e);
//        }
        enterTextInToElement(inputLoginSignIn, login);
    }

    public void enterPasswordIntoInputPassword(String password) {
        enterTextInToElement(inputPasswordSignIn, password);
    }

    public void clickOnButtonSignIn() {
        clickOnElement(buttonSignIn);
    }

    public HomePage loginWithValidCred() {
        openLoginPage();
        enterLoginIntoInputLogin(TestData.VALID_LOGIN);
        enterPasswordIntoInputPassword(TestData.VALID_PASS);
        clickOnButtonSignIn();
        return new HomePage(webDriver);
    }

    public boolean verifyIfTheSignUPButtonIsDisplayed() {
        return isElementDisplayed(buttonSignUPForOurApp);
    }

    public void enterUserNameInTheSignUpForm(String userName) {
        enterTextInToElement(inputUserNameInSignUpForm, userName);
    }

    public void enterUserEmailInTheSignUpForm(String userEmail) {
        enterTextInToElement(inputEmailInSignUpForm, userEmail);
    }

    public void enterUserPasswordInTheSignUpForm(String userPassword) {
        enterTextInToElement(inputUserPassWordInSignUpForm, userPassword);
    }

    public void clickOnButtonSignUpForOurApp() {
        clickOnElement(buttonSignUPForOurApp);
    }

    public LoginPage enterInvalidDataInTheSignUpForm() {
        enterUserNameInTheSignUpForm("tr");
        enterUserEmailInTheSignUpForm("test.com");
        enterUserPasswordInTheSignUpForm("123");
        clickOnButtonSignUpForOurApp();
        return new LoginPage(webDriver);
    }

    public boolean isValidationUserNameMessageDisplayed() {
        return isElementDisplayed(validationUserNameMessage);

    }

    public boolean isValidationUserEmailMessageDisplayed() {
        return isElementDisplayed(validationUserEmailMessage);

    }

    public boolean isValidationUserPasswordMessageDisplayed() {
        return isElementDisplayed(validationUserPasswordMessage);

    }
}

