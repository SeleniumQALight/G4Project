package pages;

import libs.TestData;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class LoginPage extends ParentPage {
    @FindBy(xpath = ".//input[@name='username' and @placeholder='Username']")
    private WebElement inputLoginSignIn;

    @FindBy(xpath = ".//input[@placeholder='Password']")
    private WebElement inputPasswordSignIn;

    @FindBy(xpath = ".//button[text()='Sign In']")
    private WebElement buttonSignIn;

    @FindBy(xpath = ".//input[@name='username'and@placeholder='Pick a username']")
    private WebElement inputUsernameRegistration;

    @FindBy(xpath = ".//input[@name='email'and@placeholder='you@example.com']")
    private WebElement inputEmailRegistration;

    @FindBy(xpath = ".//input[@placeholder='Create a password']")
    private WebElement inputCreatePasswordRegistration;

    @FindBy(xpath = ".//button[text()='Sign up for OurApp']")
    private WebElement buttonSignUpForOurApp;

    @FindBy(xpath = ".//div[text()='Username must be at least 3 characters.']")
    private WebElement messageFieldPickUsername;

    @FindBy(xpath = ".//div[text()='You must provide a valid email address.']")
    private WebElement messageFieldEmail;

    @FindBy(xpath = ".//div[text()='Password must be at least 12 characters.']")
    private WebElement messageFieldCreatePassword;

    @FindBy(xpath = ".//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']")
    private List<WebElement> listOfErrors;

    private String listErrorsLocator =
            ".//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']";

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

    public LoginPage enterLoginRegistration(String username) {
        enterTextIntoElement(inputUsernameRegistration, username);
        return this;
    }

    public LoginPage enterEmailRegistration(String email) {
        enterTextIntoElement(inputEmailRegistration, email);
        return this;
    }

    public LoginPage enterCreatePasswordRegistration(String createPassword) {
        enterTextIntoElement(inputCreatePasswordRegistration, createPassword);
        return this;
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

    public LoginPage checkErrorMessages(String expectedErrors) {
        String[] expectedErrorsArray = expectedErrors.split(";");
        webDriverWait10
                .withMessage("Numbers of messages ")
                .until(ExpectedConditions.numberOfElementsToBe(
                        By.xpath(listErrorsLocator), expectedErrorsArray.length));
        Assert.assertEquals("", expectedErrorsArray.length, listOfErrors.size());
        ArrayList<String> actualTextFromErrors = new ArrayList<>();
        for (WebElement element : listOfErrors) {
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
