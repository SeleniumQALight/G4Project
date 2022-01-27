package pages;

import libs.TestData;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends ParentPage {
    @FindBy(xpath = ".//input[@name='username' and @placeholder='Username']")
    private WebElement inputLoginSingIn;

    @FindBy(xpath = ".//input[@placeholder='Password']")
    private WebElement inputPasswordSingIn;

    @FindBy(xpath = ".//button[text()='Sign In']")
    private WebElement buttonSingIn;

    @FindBy(xpath = ".//input[ @placeholder ='Pick a username']")
    private WebElement inputLoginSignUp;

    @FindBy(xpath = ".//input[@name = 'email']")
    private WebElement inputEmailSignUp;

    @FindBy(xpath = ".//input[ @placeholder = 'Create a password']")
    private WebElement inputPasswordSignUp;

    @FindBy(xpath = ".//button[ @type = 'submit']")
    private WebElement buttonSignUp;

    @FindBy(xpath = ".//div[contains(text(), 'Username must be at least 3 characters.') ]")
    private WebElement usernameValidationMessage;

    @FindBy(xpath = ".//div[contains(text(), 'You must provide a valid email address.') ]")
    private WebElement emailValidationMessage;

    @FindBy(xpath = ".//div[contains(text(), 'Password must be at least 12 characters.') ]")
    private WebElement passwordValidationMessage;

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRalativeUrl() {
        return "/";
    }

    public void openLoginPage() {
        try {
            webDriver.get(baseUrl + "/");
            logger.info("Login page was open");

        } catch (Exception e) {
            logger.error("Can not open Login Page" + e);
            Assert.fail("Can not open Login Page" + e);
        }
    }
    public void enterLoginIntoInputLogin(String login) {
//        try {
//            inputLoginSingIn.clear();
//            inputLoginSingIn.sendKeys(login);
//            logger.info(login+"was inputted into Input Login");
//        }catch(Exception e){
//            printErrorAndStopTest(e);
//        }
        enterTextInToElement(inputLoginSingIn, login);
    }
     public  void enterPassWordIntoInputPassWord (String passWord){
//        try{
//            inputPasswordSingIn.clear();
//            inputPasswordSingIn.sendKeys(passWord);
//
//        }catch (Exception e){
//            printErrorAndStopTest(e);
//        }
         enterTextInToElement(inputPasswordSingIn, passWord);
     }

     public void clickOnButtonSingIn(){
//        try {
//            buttonSingIn.click();
//            logger.info("Button Sing In was clicked");
//        }catch(Exception e){
//            printErrorAndStopTest(e);
//        }
         clickOnElement(buttonSingIn);
     }

//    private void printErrorAndStopTest(Exception e) {
//        logger.error("Can not work with element" + e);
//        Assert.fail("Can not work with element"+ e);
//
//    }
    public HomePage loginWithValidCred(){
        openLoginPage();
        enterLoginIntoInputLogin(TestData.VALID_LOGIN);
        enterPassWordIntoInputPassWord(TestData.VALID_PASS);
        clickOnButtonSingIn();
        return new HomePage(webDriver);
    }

    public boolean isButtonSingInDisplayed() {
        try {
           return buttonSingIn != null && buttonSingIn.getText().equals("Sign In");
        } catch (Exception e) {
            return false;
        }
    }

    public void enterLoginIntoRegistrationForm(String login) {
        enterTextInToElement(inputLoginSignUp, login);
    }

    public void enterEmailIntoRegistrationForm(String email) {
        enterTextInToElement(inputEmailSignUp, email);
    }

    public void enterPasswordIntoRegistrationForm(String password) {
        enterTextInToElement(inputPasswordSignUp, password);
    }

    public void clickOnButtonSignUp() {
        clickOnElement(buttonSignUp);
    }

    public boolean checkValidationFormsDisplayed() {
        boolean isUsernameErrorMessageDisplayed = isElementDisplayed(usernameValidationMessage);
        boolean isEmailErrorMessageDisplayed = isElementDisplayed(emailValidationMessage);
        boolean isPasswordErrorMessageDisplayed = isElementDisplayed(passwordValidationMessage);

        return isUsernameErrorMessageDisplayed && isEmailErrorMessageDisplayed && isPasswordErrorMessageDisplayed;
    }


}

