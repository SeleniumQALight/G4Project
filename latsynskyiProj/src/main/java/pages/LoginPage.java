package pages;

import io.qameta.allure.Step;
import libs.TestData;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.TextInput;

import java.util.ArrayList;
import java.util.List;

public class LoginPage extends ParentPage{

    @FindBy(xpath = ".//input[@name='username' and @placeholder='Username']")
    @Name("Input Login")
    private TextInput inputLoginSingIn;

    @FindBy(xpath = ".//input[@placeholder='Password']")
    private TextInput inputPassWordSingIn;

    @FindBy(xpath = ".//button[text()='Sign In']")
    private Button buttonSingIn;

    @FindBy(xpath =".//div[text()='Invalid username / password']" )
    private WebElement errormsg;

    @FindBy(xpath = ".//input[@name='username'and @id='username-register']")
    private WebElement inputUsernameForRegistration;

    @FindBy(xpath = ".//input[@name='email'and @id='email-register']")
    private WebElement inputEmailForRegistration;

    @FindBy(xpath = ".//input[@name='password'and @id='password-register']")
    private WebElement inputPasswordForRegistration;

    @FindBy(xpath = ".//button[text()='Sign up for OurApp']")
    private WebElement buttonSignUpForOurApp;

    @FindBy(xpath = ".//div[text()='Username must be at least 3 characters.']")
    private WebElement errorMsgValidUsername;
    @FindBy(xpath = ".//div[text()='You must provide a valid email address.']")
    private WebElement errorMsgValidEmail;
    @FindBy(xpath = ".//div[text()='Password must be at least 12 characters.']")
    private WebElement errorMsgValidPassword;
    private String listErrorsLocator=".//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']";

    @FindBy(xpath = ".//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']")
    private List<WebElement> listOfErrors;
    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/";
    }
@Step
    public void openLoginPage() {
        try {
            webDriver.get(baseUrl +"/");
            logger.info("Login page was opened  "+baseUrl+"/");
            logger.info("Login page was opened");
        } catch (Exception e) {
            logger.error("Can not open Login Page"+e);
            Assert.fail("Can not open Login Page"+e);
        }
        }
    @Step
    public void enterLoginIntoInputLogin(String login) {
//        try{
//            inputLoginSingIn.clear();
//            inputLoginSingIn.sendKeys(login);
//            logger.info(login+ "was inputted into input login");
//
//        }catch (Exception e){
//            printErrorAndStopTest(e);
//        }
        enterTextInToElement(inputLoginSingIn,login);
    }
    @Step
    public void enterPassWordIntoInputPassWord(String passWord) {
//        try {
//            inputPassWordSingIn.clear();
//            inputPassWordSingIn.sendKeys(passWord);
//            logger.info(passWord + "was inputted");
//
//        } catch (Exception e) {
//            printErrorAndStopTest(e);
//        }
        enterTextInToElement(inputPassWordSingIn,passWord);
    }@Step
    public void clickOnButtonSingIn(){
//        try{
//            ButtonSingIn.click();
//            logger.info("Button Sign in was clicked ");
//        }catch (Exception e){
//            printErrorAndStopTest(e);
//        }
        clickOnElement(buttonSingIn);
    }

    @Step
    private void printErrorAndStopTest(Exception e) {
        logger.error("Can not work with element"+e);
        Assert.fail("Can not work with element"+e);
    }@Step
    public HomePage loginWithValidCred(){
        openLoginPage();
        enterLoginIntoInputLogin(TestData.VALID_LOGIN);
        enterPassWordIntoInputPassWord(TestData.VALID_PASS);
        clickOnButtonSingIn();
          return new HomePage(webDriver);
    }@Step
    public boolean errormsgIsDisplayed (){
        try{
            return  errormsg.isDisplayed();
        }catch (Exception e){
            return false;
        }
    }

    public void enterUsernameForRegistration(String Username){
enterTextInToElement(inputUsernameForRegistration,Username);
    }
    public void enterEmailForRegistration(String Email){
        enterTextInToElement(inputEmailForRegistration,Email);
    }
    public void enterPasswordForRegistration(String Password){
        enterTextInToElement(inputPasswordForRegistration,Password);
    }
    public void clickOnButtonSignUpForOurApp(){
        clickOnElement(buttonSignUpForOurApp);
    }

    public boolean errormsgValidUsernameIsDisplayed() {
        try{
            return  errorMsgValidUsername.isDisplayed();
        }catch (Exception e){
            return false;
        }
    }

    public boolean errormsgValidemailIsDisplayed() {
        try{
            return  errorMsgValidEmail.isDisplayed();
        }catch (Exception e){
            return false;
        }
    }

    public boolean errormsgValidPasswordIsDisplayed() {
        try{
            return  errorMsgValidPassword.isDisplayed();
        }catch (Exception e){
            return false;
        }
    }
    public boolean isButtonSignUpForOurAppDisplayed() {
        try{
            return  buttonSignUpForOurApp.isDisplayed();
        }catch (Exception e){
            return false;
        }
    }
public  LoginPage checkErrorsMessages(String expectedErrors){
        String[] expectedErrorsArray = expectedErrors.split(";");
        webDriverWait10.withMessage(" Numbers of messages ").until(ExpectedConditions.numberOfElementsToBe(
                By.xpath(listErrorsLocator),expectedErrorsArray.length
                                                                       ));
    Assert.assertEquals("",expectedErrorsArray.length,listOfErrors.size());
        ArrayList<String> actualTextFromErrors =new ArrayList<>();
    for (WebElement element:listOfErrors) {
        actualTextFromErrors.add(element.getText());
    }
    SoftAssertions softAssertions = new SoftAssertions();

    for (int i = 0; i < expectedErrorsArray.length; i++) {
        softAssertions.assertThat(expectedErrorsArray[i]).isIn(actualTextFromErrors);
    }

    softAssertions.assertAll();
        return this;
}
}

