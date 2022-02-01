package pages;

import libs.TestData;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage  extends ParentPage{
    @FindBy(xpath = ".//input[@name='username' and @placeholder='Username']")
    private WebElement inputLoginSignIn;

    @FindBy(xpath = ".//input[@placeholder='Password']")
    private WebElement inputPassWordSingIn;

    @FindBy(xpath = ".//button[text()='Sign In']")
    private WebElement buttonSingIn;

    @FindBy(xpath = ".//*[@id='username-register']")
    private WebElement registrationLogin;

    @FindBy (xpath =".//*[@id='email-register']")
    private WebElement registrationMail;

    @FindBy (xpath =".//*[@id='password-register']")
    private WebElement registrationPassword;

    @FindBy(xpath=".//*[@type='submit']")
    private WebElement signUpButton;

    @FindBy(xpath = ".//*[text()='Username must be at least 3 characters.']")
    private WebElement usernameValidationMes;

    @FindBy(xpath = ".//*[contains(text(),'You must provide a valid email address.')]")
    private WebElement mailValidationMes;

    @FindBy(xpath = ".//*[text()='Password must be at least 12 characters.']")
    private WebElement passwordValidationMes;



    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/";
    }


    public  void openLoginPage(){
        try{
            webDriver.get(baseUrl + "/");
            logger.info("Login page was open");

        }catch ( Exception e){
            logger.error("Cannot open Login Page" + e);
            Assert.fail("Cannot open Login Page" + e);
        }
    }

    public void enterLoginIntoInputLogin(String login) {
//        try{
//            inputLoginSignIn.clear();
//            inputLoginSignIn.sendKeys(login);
//            logger.info(login + " was inputted into Input Login");
//
//        }catch(Exception e){
//            printErrorAndStopTest(e);
//        }
        enterTextInToElement(inputLoginSignIn, login);
    }
    public void enterPasswordIntoInputPassword(String passWord){

        enterTextInToElement(inputPassWordSingIn, passWord);
    }

    public void clickOnButtonSignIn(){
//        try{
//           buttonSingIn.click();
//            logger.info("Button Sign In was clicked");
//        } catch (Exception e){
//            printErrorAndStopTest(e);
//        }
        clickOnElement(buttonSingIn);
    }
    public HomePage loginWithValidCred(){
        openLoginPage();
        enterLoginIntoInputLogin(TestData.VALID_LOGIN);
        enterPasswordIntoInputPassword(TestData.VALID_PASS);
        clickOnButtonSignIn();
        return  new HomePage(webDriver);
}


    public void enterLoginForRegistration(String regLogin) {
        enterTextInToElement(registrationLogin, regLogin);
    }

    public void enterEmailForRegistration(String regMail) {
        enterTextInToElement(registrationMail, regMail);
    }

    public void enterPasswordForRegistration(String regPass) {
        enterTextInToElement(registrationPassword,regPass);
    }
    public void clickOnSignUpButton() {
        clickOnElement(signUpButton);
    }

    public LoginPage checkTextInValidationUsername(String text){
        Assert.assertEquals("Text in username Validation Message ", text, usernameValidationMes.getText()); ;
        return this;
    }
    public LoginPage checkTextInValidationMail(String text1){
        Assert.assertEquals("Text in Mail Validation Message ", text1, mailValidationMes.getText()); ;
        return this;
    }
    public LoginPage checkTextInValidationPassword(String text){
        Assert.assertEquals("Text in Password Validation Message ", text,passwordValidationMes.getText());
    return this;
    }
}
