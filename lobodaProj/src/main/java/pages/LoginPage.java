package pages;

import libs.TestData;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class LoginPage extends ParentPage{
    @FindBy(xpath = ".//input[@name='username' and @placeholder='Username']")
    private WebElement inputLoginSignIn;

    @FindBy(xpath = ".//input[@name='password' and @placeholder='Password']")
    private WebElement inputPassWordSignIn;

    @FindBy(xpath = ".//button[text()='Sign In']")
    private WebElement buttonSignIn;

    @FindBy(xpath = ".//div[@class='alert alert-danger text-center']")
    private WebElement errorAlert;

    @FindBy(xpath = ".//input[@id='username-register']")
    private WebElement inputLoginSignUp;

    @FindBy(xpath = ".//input[@id='password-register']")
    private WebElement inputPassWordSignUp;

    @FindBy(xpath = ".//input[@id='email-register']")
    private WebElement inputEmailSignUp;

    @FindBy(xpath = ".//button[@type='submit']")
    private WebElement buttonSignUp;

    @FindBy(xpath = ".//input[@id='username-register']/following-sibling::div[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']")
    private WebElement alertInvalidLogin;

    @FindBy(xpath = ".//input[@id='email-register']/following-sibling::div[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']")
    private WebElement alertInvalidEmail;

    @FindBy(xpath = ".//input[@id='password-register']/following-sibling::div[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']")
    private WebElement alertInvalidPassWord;




    public LoginPage(WebDriver webDriver){
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/";
    }

    public void openLoginPage(){
        try {
            webDriver.get(baseUrl + "/");
            logger.info("Login Page was opened");

        }catch (Exception e){
            logger.error("Can not open Login Page" + e);
            Assert.fail("Can not open Login Page" + e);
        }
    }

    public void enterLoginIntoInputLogin(String login) {
        enterTextInToElement(inputLoginSignIn, login);
    }

    public void enterPassWordIntoInputPassWord(String passWord) {
        enterTextInToElement(inputPassWordSignIn, passWord);
    }

    public void clickOnButtonSignIn(){
        clickOnElement(buttonSignIn);
    }

    public boolean isButtonSignInDisplayed(){
        try {
            return buttonSignIn.isDisplayed();
        }catch (Exception e){
            return false;
        }
    }

    public boolean isErrorAlertDisplayed(){
        try {
            return errorAlert.isDisplayed();
        }catch (Exception e){
            return false;
        }
    }

    public HomePage loginWithValidCred(){
        openLoginPage();
        enterLoginIntoInputLogin(TestData.VALID_LOGIN);
        enterPassWordIntoInputPassWord(TestData.VALID_PASS);
        clickOnButtonSignIn();
        return new HomePage(webDriver);
    }

    public void enterLoginIntoInputLoginSignUp(String login) {
        enterTextInToElement(inputLoginSignUp, login);
    }

    public void enterPassWordIntoInputPassWordSignUp(String passWord) {
        enterTextInToElement(inputPassWordSignUp, passWord);
    }

    public void enterEmailIntoInputEmailSignUp(String email) {
        enterTextInToElement(inputEmailSignUp, email);
    }

    public void clickOnButtonSignUp(){
        clickOnElement(buttonSignUp);
    }

    public boolean isTextInAlertInvalidLoginCorrect() {
        try {
            return getTextFromElement(alertInvalidLogin).equals("Username must be at least 3 characters.");
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isTextInAlertInvalidEmailCorrect() {
        try {
            return getTextFromElement(alertInvalidEmail).equals("You must provide a valid email address.");
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isTextInAlertInvalidPassWordCorrect() {
        try {
            return getTextFromElement(alertInvalidPassWord).equals("Password must be at least 12 characters.");
        } catch (Exception e) {
            return false;
        }
    }

}
