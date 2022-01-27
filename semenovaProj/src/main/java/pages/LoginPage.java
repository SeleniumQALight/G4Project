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

    @FindBy(xpath = ".//input[@name='username'and@placeholder='Pick a username']")
    private WebElement inputPickUsername;

    @FindBy(xpath = ".//input[@name='email'and@placeholder='you@example.com']")
    private WebElement inputEmail;

    @FindBy(xpath = ".//input[@placeholder='Create a password']")
    private WebElement inputCreatePassword;

    @FindBy(xpath = ".//button[text()='Sign up for OurApp']")
    private WebElement buttonSignUpForOurApp;

    @FindBy(xpath = ".//div[text()='Username must be at least 3 characters.']")
    private WebElement messageFieldPickUsername;

    @FindBy(xpath = ".//div[text()='You must provide a valid email address.']")
    private WebElement messageFieldEmail;

    @FindBy(xpath = ".//div[text()='Password must be at least 12 characters.']")
    private WebElement messageFieldCreatePassword;

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/";
    }

    public void openLoginPage() {
        try {
            webDriver.get(baseUrl + "/");
            logger.info("Login page was opened");
        } catch (Exception e) {
            logger.error("Can not open Login Page" + e);
            Assert.fail("Can not open Login Page" + e);
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

    public void enterPassWordIntoInputPassword(String password) {
//        try {
//            inputPasswordSignIn.clear();
//            inputPasswordSignIn.sendKeys(password);
//            logger.info(password + " was inputted");
//        } catch (Exception e) {
//            printErrorAndStopTest(e);
//        }
        enterTextIntoElement(inputPasswordSignIn, password);
    }

    public void clickOnButtonSingIn() {
//        try {
//            buttonSingIn.click();
//            logger.info("Button Sign In was clicked");
//        }catch (Exception e){
//            printErrorAndStopTest(e);
//        }
        clickOnEltment(buttonSignIn);
    }

    public HomePage loginWithValidCred() {
        openLoginPage();
        enterLoginIntoInputLogin(TestData.VALID_LOGIN);
        enterPassWordIntoInputPassword(TestData.VALID_PASS);
        clickOnButtonSingIn();
        return new HomePage(webDriver);
    }

    public void enterUsernameIntoInputPickUsername(String username) {
        enterTextIntoElement(inputPickUsername, username);
    }

    public void enterEmailIntoInputEmail(String email) {
        enterTextIntoElement(inputEmail, email);
    }

    public void enterCreatePasswordIntoInputCreatePaswword(String createPassword) {
        enterTextIntoElement(inputCreatePassword, createPassword);
    }

    public void clickOnButtonSignUpForOurApp() {
        clickOnEltment(buttonSignUpForOurApp);
    }

    public boolean messageFieldPickUsernameDisplayed() {

        return isMessageDisplayed(messageFieldPickUsername);
    }

    public boolean messageFieldEmail() {

        return isMessageDisplayed(messageFieldEmail);

    }

    public boolean messageFieldCreatePassword() {

        return isMessageDisplayed(messageFieldCreatePassword);

    }

    public LoginPage checkIsMessagesDisplayed() {
        Assert.assertTrue("Message is not displayed", messageFieldPickUsernameDisplayed());
        Assert.assertTrue("Message is not displayed", messageFieldEmail());
        Assert.assertTrue("Message is not displayed", messageFieldCreatePassword());
        return this;
    }

}
