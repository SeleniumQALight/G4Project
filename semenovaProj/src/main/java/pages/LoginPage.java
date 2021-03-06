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

public class LoginPage extends ParentPage {
    @FindBy(xpath = ".//input[@name='username' and @placeholder='Username']")
    @Name("Input Login")
    private TextInput inputLoginSignIn;

    @FindBy(xpath = ".//input[@placeholder='Password']")
    private TextInput inputPasswordSignIn;

    @FindBy(xpath = ".//button[text()='Sign In']")
    private Button buttonSignIn;

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

    @Step
    public void openLoginPage() {
        try {
            webDriver.get(baseUrl + "/");
            logger.info("Login page was opened" + baseUrl + "/");
        } catch (Exception e) {
            logger.error("Can not open Login Page" + e);
            Assert.fail("Can not open Login Page" + e);
        }
    }

    @Step
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

    @Step
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

    @Step
    public void clickOnButtonSingIn() {
//        try {
//            buttonSingIn.click();
//            logger.info("Button Sign In was clicked");
//        }catch (Exception e){
//            printErrorAndStopTest(e);
//        }
        clickOnEltment(buttonSignIn);
    }

    @Step
    public HomePage loginWithValidCred() {
        openLoginPage();
        enterLoginIntoInputLogin(TestData.VALID_LOGIN);
        enterPassWordIntoInputPassword(TestData.VALID_PASS);
        clickOnButtonSingIn();
        return new HomePage(webDriver);
    }

    @Step
    public LoginPage enterLoginRegistration(String username) {
        enterTextIntoElement(inputUsernameRegistration, username);
        return this;
    }

    @Step
    public LoginPage enterEmailRegistration(String email) {
        enterTextIntoElement(inputEmailRegistration, email);
        return this;
    }

    @Step
    public LoginPage enterCreatePasswordRegistration(String createPassword) {
        enterTextIntoElement(inputCreatePasswordRegistration, createPassword);
        return this;
    }

    @Step
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

    @Step
    public LoginPage checkIsMessagesDisplayed() {
        Assert.assertTrue("Message is not displayed", messageFieldPickUsernameDisplayed());
        Assert.assertTrue("Message is not displayed", messageFieldEmail());
        Assert.assertTrue("Message is not displayed", messageFieldCreatePassword());
        return this;
    }

    @Step
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
