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

    @FindBy(xpath = ".//div[@class='alert alert-danger text-center']")
    private WebElement messageErrorSignIn;

    @FindBy(xpath = ".//input[@id='username-register']")
    private WebElement inputUsernameSignUp;

    @FindBy(xpath = ".//input[@id='email-register']")
    private WebElement inputEmailSignUp;

    @FindBy(xpath = ".//input[@id='password-register']")
    private WebElement inputPasswordSignUp;

    @FindBy(xpath = ".//button[@type='submit']")
    private WebElement buttonSignUp;

    @FindBy(xpath = ".//div[contains(text(), 'Username must be at least 3 characters.')]")
    private WebElement errorUsernameSignup;

    @FindBy(xpath = ".//div[contains(text(), 'You must provide a valid email address.')]")
    private WebElement errorEmailSignup;

    @FindBy(xpath = ".//div[contains(text(), 'Password must be at least 12 characters.')]")
    private WebElement errorPasswordSignup;

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/";
    }

    public void openLoginPage() {
        try {
            webDriver.get(baseUrl +"/");
            logger.info("LoginPage was open");
        } catch (Exception e) {
            logger.error("Cannot open LoginPage " + e);
            Assert.fail("Cannot open LoginPage " + e);
        }
    }

    public void enterLoginIntoInputLogin(String login) {
//        try {
//            inputLoginSignIn.clear();
//            inputLoginSignIn.sendKeys(login);
//            logger.info(login + " was inputted into Input Login");
//        } catch (Exception e) {
//            printErrorAndStopTest(e);
//        }
        enterTextIntoElement(inputLoginSignIn, login);
    }

    public void enterLoginIntoInputPassword(String password) {
        enterTextIntoElement(inputPasswordSignIn, password);
    }

    public void enterUsernameIntoInputUsernameSignUp(String username) {
        enterTextIntoElement(inputUsernameSignUp, username);
    }

    public void enterEmailIntoInputEmailSignUp(String email) {
        enterTextIntoElement(inputEmailSignUp, email);
    }

    public void enterPasswordIntoInputPasswordSignUp(String password) {
        enterTextIntoElement(inputPasswordSignUp, password);
    }

    public void clickOnButtonSignIn() {
        clickOnElement(buttonSignIn);
    }

    public void clickOnButtonSignUp() {
        clickOnElement(buttonSignUp);
    }

    public LoginPage checkIsErrorMessageSignInDisplayed() {
        Assert.assertTrue("Error about invalid Username is not displayed", isElementDisplayed(messageErrorSignIn));
        return this;
    }

    public LoginPage checkIsErrorUsernameSignupDisplayed() {
        Assert.assertTrue("Error about invalid Username is not displayed", isElementDisplayed(errorUsernameSignup));
        return this;
    }

    public LoginPage checkIsErrorEmailSignupDisplayed() {
        Assert.assertTrue("Error about invalid Email is not displayed", isElementDisplayed(errorEmailSignup));
        return this;
    }

    public LoginPage checkIsErrorPasswordSignupDisplayed() {
        Assert.assertTrue("Error about invalid Password is not displayed", isElementDisplayed(errorPasswordSignup));
        return this;
    }

    public HomePage loginWithValidCredentials() {
        openLoginPage();
        enterLoginIntoInputLogin(TestData.VALID_LOGIN);
        enterLoginIntoInputPassword(TestData.VALID_PASS);
        clickOnButtonSignIn();
        return new HomePage(webDriver);
    }

    public LoginPage loginWithInvalidCredentials() {
        openLoginPage();
        enterLoginIntoInputLogin("qa");
        enterLoginIntoInputPassword("123456qwerty");
        clickOnButtonSignIn();
        return new LoginPage(webDriver);
    }

    public LoginPage signUpWithInvalidCredentials() {
        openLoginPage();
        enterUsernameIntoInputUsernameSignUp("tr");
        enterEmailIntoInputEmailSignUp("test.com");
        enterPasswordIntoInputPasswordSignUp("123");
        clickOnButtonSignUp();
        return new LoginPage(webDriver);
    }
}