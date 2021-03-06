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
import java.util.concurrent.TimeUnit;

public class LoginPage extends ParentPage {
    //postoyanno ispolyzuemie elementi
    @FindBy(xpath = ".//input[@name='username' and @placeholder='Username']")
    @Name("Input Login")
    private TextInput inputLoginSingIn;

    @FindBy(xpath = ".//input[@type= 'password' and @placeholder='Password']")
    //@Name("Input Password")
    private TextInput inputPasswordSingIn;

    @FindBy(xpath = ".//button[text()='Sign In']")
    private TextInput clickButtonSingIn;

    @FindBy(xpath = ".//input[@id='username-register']")
    private WebElement inputLoginSignUp;

    @FindBy(xpath = ".//input[@id='email-register']")
    private WebElement inputMailSignUP;

    @FindBy(xpath = ".//input[@id='password-register']")
    private WebElement inputPassSignUp;

    @FindBy(xpath = ".//button[text()='Sign up for OurApp']")
    private WebElement buttonSignUp;

    @FindBy(xpath = ".//div[text()='Username must be at least 3 characters.']")
    private WebElement errorTextLoginSignUp;

    @FindBy(xpath = ".//div[text()='You must provide a valid email address.']")
    private WebElement errorTextMailSignUp;

    @FindBy(xpath = ".//div[text()='Password must be at least 12 characters.']")
    private WebElement errorTextPassSignUp;

    @FindBy(xpath = ".//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']")
    private List<WebElement> listOfErrors;

    @FindBy(xpath = ".//*[contains(@class,'alert alert-danger text-center')]")
    private WebElement alertInCenter;

    @FindBy(xpath = ".//*[@id='username-register']/../div")
    private WebElement alertLoginSignUp;

    @FindBy(xpath = ".//*[@id='email-register']/../div")
    private WebElement alertEmailSignUp;

    @FindBy(xpath = ".//*[@id='password-register']/../div")
    private WebElement alertPassSignUp;

    @FindBy(xpath = "//*[text()='That username is already taken.']")
    private WebElement alertExistingLoginSignUp;

    private String listErrorsLocator = ".//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']";


    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Override
    String getRelativeUrl() {
        return "/";
    }

    @Step
    public void openLoginPage(){
        try {
            driver.get(baseUrl +  "/");
            logger.info("Login page was opened" + baseUrl + "/");
        }catch (Exception e){
            logger.error("Can't open login page" + e);
            Assert.fail("Can't open login page" + e);
        }
    }

    @Step
    public void enterLoginIntoInputLogin(String login) {
//        try {
//            inputLoginSingIn.clear();
//            inputLoginSingIn.sendKeys(login);
//            logger.info(login + " was entered into login field");
//        }catch (Exception e){
//            printErrorAndStopTest(e);
//        }
        enterTextIntoElement(inputLoginSingIn, login);
    }

    @Step
    public void enterPasswordIntoInputPassword(String pass){
//        try {
//            inputPasswordSingIn.clear();
//            inputPasswordSingIn.sendKeys(pass);
//            logger.info((pass + " was entered into password field"));
//        }catch (Exception e){
//            printErrorAndStopTest(e);
//        }
        enterTextIntoElement(inputPasswordSingIn,pass);
    }

    @Step
    public void clickOnSignIn(){
//        try {
//            clickButtonSingIn.click();
//            logger.info("Button Sign In was clicked");
//        }catch (Exception e){
//            printErrorAndStopTest(e);
//        }
        clickOnElement(clickButtonSingIn);
    }

    @Step
    public HomePage logedInHomepage(){
        openLoginPage();
        enterLoginIntoInputLogin(TestData.VALID_LOGIN);
        enterPasswordIntoInputPassword(TestData.VALID_PASS);
        clickOnSignIn();
        return new  HomePage(driver);
    }

    @Step
    public LoginPage enterLoginSignUp(String login) {
        enterTextIntoElement(inputLoginSignUp, login);
        return this;
    }

    @Step
    public LoginPage enterMailSignUp(String mail){
        enterTextIntoElement(inputMailSignUP, mail);
        return this;
    }

    @Step
    public LoginPage enterPasswordSignUp(String pass){
        enterTextIntoElement(inputPassSignUp, pass);
        return this;
    }

    @Step
    public void clickOnSignUp(){
        clickOnElement(buttonSignUp);
    }

    @Step
    public boolean displayedMessageError(){
        try {
            return driver.findElement(By.xpath(".//div[@class='alert alert-danger text-center']")).isDisplayed();

        }catch (Exception e){
            return false;
        }
    }

    @Step
    public boolean buttonSingUpIsDisplayed(){
        try {
            return driver.findElement(By.xpath(".//button[@type='submit']")).isDisplayed();
        }catch (Exception e){
            return false;
        }
    }

    @Step
    public LoginPage checkErrorTextSignUpLogin(){
        Assert.assertTrue("Message on login field isn't displayed", checkErrorText(errorTextLoginSignUp));
        return this;
    }

    @Step
    public LoginPage checkErrorTextSignUpMail(){
        Assert.assertTrue("Message on email field isn't displayed", checkErrorText(errorTextMailSignUp));
        return this;
    }

    @Step
    public LoginPage checkErrorTextSignUpPass(){
        Assert.assertTrue("Message on password field isn't displayed", checkErrorText(errorTextPassSignUp));
        return this;
        }


    @Step
    public LoginPage checkErrorsMessages(String expectedErrors) {
        String[] expectedErrorsAray = expectedErrors.split(";");
        webDriverWait10.withMessage("Numbers of message(s) ")
                .until(ExpectedConditions.numberOfElementsToBe(By.xpath(listErrorsLocator), expectedErrorsAray.length));
        Assert.assertEquals("", expectedErrorsAray.length, listOfErrors.size());
        ArrayList<String> actualTextFromErrors = new ArrayList<>();
        for (WebElement element: listOfErrors) {
            actualTextFromErrors.add(element.getText());
        }
        SoftAssertions softAssertions = new SoftAssertions();
        for (int i = 0; i < expectedErrorsAray.length; i++) {
            softAssertions.assertThat(expectedErrorsAray[i]).isIn(actualTextFromErrors);
        }
        softAssertions.assertAll();

        return this;
    }

    public void checkAlertMessageText(String messageText) {
        Assert.assertEquals("Incorrect login message ", messageText, alertInCenter.getText());
    }

    public void checkTextAlertSignUpLogin(String popUp){
        //driver.manage().timeouts().setScriptTimeout(1, TimeUnit.SECONDS);
        Assert.assertEquals("Incorrect login message in sign up form", popUp, errorTextLoginSignUp.getText());
    }


    public void checkTextAlertSignUpEmail(String popUp) {
        //driver.manage().timeouts().setScriptTimeout(1, TimeUnit.SECONDS);
        Assert.assertEquals("Incorrect email message in sign up form", popUp, errorTextMailSignUp.getText());
    }

    public void checkTextAlertSignUpPassword(String popUp) {
        //driver.manage().timeouts().setScriptTimeout(1, TimeUnit.SECONDS);
        Assert.assertEquals("Incorrect password message in sign up form", popUp, errorTextPassSignUp.getText());
    }

    public void checkTextAlertSignUpLoginExisting(String popUp) {
        Assert.assertEquals("Incorrect login message in sign up form", popUp, alertExistingLoginSignUp);
    }
}
