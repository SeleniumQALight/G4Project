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
import ru.yandex.qatools.htmlelements.element.TextInput;

import java.util.ArrayList;
import java.util.List;

public class LoginPage  extends ParentPage{
    @FindBy(xpath = ".//input[@name='username' and @placeholder='Username']")
 //   @Name("Input Login")
  //  private TextInput inputLoginSignIn;
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

    @FindBy(id = "username-register")
    private WebElement inputLoginRegistration;

    @FindBy(id = "email-register")
    private WebElement inputEmailRegistration;

    @FindBy(id = "password-register")
    private WebElement inputPassWordRegistration;

   // @FindBy(xpath =".//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']")
    private String listErrorsLocator =".//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']" ;


    @FindBy(xpath =".//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']")
    private List<WebElement> listOfErrors;

    @FindBy(xpath = ".//*[contains(@class,'alert alert-danger text-center')]")
    private WebElement alertInCenter;



    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/";
    }

@Step
    public  void openLoginPage(){
        try{
            logger.info("base url: " + baseUrl);
            webDriver.get(baseUrl + "/");
            logger.info("Login page was open");

        }catch ( Exception e){
            logger.error("Cannot open Login Page " + e);
            Assert.fail("Cannot open Login Page " + e);
        }
    }

    @Step
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

    @Step
    public void enterPasswordIntoInputPassword(String passWord){

        enterTextInToElement(inputPassWordSingIn, passWord);
    }

    @Step
    public void clickOnButtonSignIn(){
//        try{
//           buttonSingIn.click();
//            logger.info("Button Sign In was clicked");
//        } catch (Exception e){
//            printErrorAndStopTest(e);
//        }
        clickOnElement(buttonSingIn);
    }

    @Step
    public HomePage loginWithValidCred(){
        openLoginPage();
        enterLoginIntoInputLogin(TestData.VALID_LOGIN);
        enterPasswordIntoInputPassword(TestData.VALID_PASS);
        clickOnButtonSignIn();
        return  new HomePage(webDriver);
}


    @Step
    public void enterLoginForRegistration(String regLogin) {
        enterTextInToElement(registrationLogin, regLogin);
    }

    @Step
    public void enterEmailForRegistration(String regMail) {
        enterTextInToElement(registrationMail, regMail);
    }

    @Step
    public void enterPasswordForRegistration(String regPass) {
        enterTextInToElement(registrationPassword,regPass);
    }

    @Step
    public void clickOnSignUpButton() {
        clickOnElement(signUpButton);
    }

    @Step
    public LoginPage checkTextInValidationUsername(String text){
        Assert.assertEquals("Text in username Validation Message ", text, usernameValidationMes.getText()); ;
        return this;
    }

    @Step
    public LoginPage checkTextInValidationMail(String text1){
        Assert.assertEquals("Text in Mail Validation Message ", text1, mailValidationMes.getText()); ;
        return this;
    }

    @Step
    public LoginPage checkTextInValidationPassword(String text){
        Assert.assertEquals("Text in Password Validation Message ", text,passwordValidationMes.getText());
    return this;
    }

    @Step
    public LoginPage enterLoginRegistration(String login) {
        enterTextInToElement(inputLoginRegistration, login);
        return this;
    }

    @Step
    public LoginPage enterEmailRegistration(String email) {
        enterTextInToElement(inputEmailRegistration, email);
        return this;
    }

    @Step
    public LoginPage enterPassWordRegistration(String password) {
        enterTextInToElement(inputPassWordRegistration, password);
        return this;
    }

    @Step
    public LoginPage checkErrorsMessages(String expectedErrors) {
        String[] expectedErrorsArray = expectedErrors.split(";");
        webDriverWait10.withMessage("Numbers of messages")
                .until(ExpectedConditions.numberOfElementsToBe(
                By.xpath(listErrorsLocator),expectedErrorsArray.length
                ));

        Assert.assertEquals("", expectedErrorsArray.length, listOfErrors.size());
        ArrayList<String> actualTextFromErrors   = new ArrayList<>();
        for (WebElement element: listOfErrors) {
            actualTextFromErrors.add(element.getText());
        }
        SoftAssertions  softAssertion  = new SoftAssertions();

        for (int i = 0; i <expectedErrorsArray.length ; i++) {
            softAssertion.assertThat(expectedErrorsArray[i]).isIn(actualTextFromErrors);
        }

        softAssertion.assertAll();
        return this;
    }

    public void checkAlertMessageText(String messageText) {
        Assert.assertEquals("Message in Center ", messageText, alertInCenter.getText() );
    }
}
