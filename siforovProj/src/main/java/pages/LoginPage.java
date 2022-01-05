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

    @FindBy(xpath = ".//input[@id='username-register']")
    private WebElement inputUserNameSignUpForm;

    @FindBy(xpath = ".//input[@id='email-register']")
    private WebElement inputEmailSignUpForm;

    @FindBy(xpath = ".//input[@id='password-register']")
    private WebElement inputPasswordSignUpForm;

    @FindBy(xpath = ".//button[@type='submit']")
    private WebElement buttonSignUp;

    @FindBy(xpath = ".//div[text()='Username must be at least 3 characters.']")
    private WebElement alertTextUserNameAtLeast3Chars;

    @FindBy(xpath = ".//div[text()='You must provide a valid email address.']")
    private WebElement alertTextEmailMustBeValid;

    @FindBy(xpath = ".//div[text()='Password must be at least 12 characters.']")
    private WebElement alertPasswordAtLeast12Chars;

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }


    public void openLoginPage() {
        try {
            webDriver.get("https://qa-complex-app-for-testing.herokuapp.com/");
            logger.info("Login page has been opened");
        } catch (Exception e) {
            logger.error("Can not open Login page " + e);
            Assert.fail("Can not open Login page " + e);
        }
    }

    public void enterLoginIntoInputLogin(String login) {
        enterTextIntoElement(inputLoginSignIn, login);
    }

    public void enterPasswordIntoInputPassword(String password) {
        enterTextIntoElement(inputPasswordSignIn, password);
    }

    public void clickOnButton() {
        clickOnElement(buttonSignIn);
    }

    public void enterUsernameIntoInputUsernameSignUpForm(String username){
        enterTextIntoElement(inputUserNameSignUpForm, username);
    }

    public void enterEmailIntiEmailInputSignUpForm(String email){
        enterTextIntoElement(inputEmailSignUpForm, email);
    }

    public void enterPasswordIntoPasswordInputSignUpForm(String password){
        enterTextIntoElement(inputPasswordSignUpForm, password);
    }

    public void clickOnSignUpButton(){
        clickOnElement(buttonSignUp);
    }

    public HomePage loginWithValidCredentials(){
        openLoginPage();
        enterLoginIntoInputLogin(TestData.VALID_LOGIN);
        enterPasswordIntoInputPassword(TestData.VALID_PASS);
        clickOnButton();
        return new HomePage(webDriver);
    }
}
