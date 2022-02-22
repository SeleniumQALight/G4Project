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

    @FindBy(xpath = ".//div[@class='alert alert-danger text-center']")
    private WebElement messageErrorSignIn;

    @FindBy(xpath = ".//input[@id='username-register']")
    private WebElement inputUsernameSignUp;

    @FindBy(xpath = ".//input[@id='email-register']")
    private WebElement inputEmailSignUp;

    @FindBy(xpath = ".//input[@id='password-register']")
    private WebElement inputPasswordSignUp;

    @FindBy(xpath = ".//button[@type='submit']")
    private WebElement buttonSignUp;

    @FindBy(xpath = ".//div[contains(text(), 'Username must be at least 3 characters.')]")
    private WebElement errorUsernameSignup;

    @FindBy(xpath = ".//div[contains(text(), 'You must provide a valid email address.')]")
    private WebElement errorEmailSignup;

    @FindBy(xpath = ".//div[contains(text(), 'Password must be at least 12 characters.')]")
    private WebElement errorPasswordSignup;

    private String listErrorsLocator = ".//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']";

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
            webDriver.get(baseUrl + "/");
            logger.info("LoginPage was open on " +baseUrl+ "/");
        } catch (Exception e) {
            logger.error("Cannot open LoginPage " + e);
            Assert.fail("Cannot open LoginPage " + e);
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
    public void enterLoginIntoInputPassword(String password) {
        enterTextIntoElement(inputPasswordSignIn, password);
    }

    @Step
    public LoginPage enterUsernameIntoInputUsernameSignUp(String username) {
        enterTextIntoElement(inputUsernameSignUp, username);
        return this;
    }

    @Step
    public LoginPage enterEmailIntoInputEmailSignUp(String email) {
        enterTextIntoElement(inputEmailSignUp, email);
        return this;
    }

    @Step
    public LoginPage enterPasswordIntoInputPasswordSignUp(String password) {
        enterTextIntoElement(inputPasswordSignUp, password);
        return this;
    }

    @Step
    public void clickOnButtonSignIn() {
        clickOnElement(buttonSignIn);
    }

    @Step
    public void clickOnButtonSignUp() {
        clickOnElement(buttonSignUp);
    }

    @Step
    public LoginPage checkIsErrorMessageSignInDisplayed() {
        Assert.assertTrue("Error about invalid Username is not displayed", isElementDisplayed(messageErrorSignIn));
        return this;
    }

    @Step
    public LoginPage checkIsErrorUsernameSignupDisplayed() {
        Assert.assertTrue("Error about invalid Username is not displayed", isElementDisplayed(errorUsernameSignup));
        return this;
    }

    @Step
    public LoginPage checkIsErrorEmailSignupDisplayed() {
        Assert.assertTrue("Error about invalid Email is not displayed", isElementDisplayed(errorEmailSignup));
        return this;
    }

    @Step
    public LoginPage checkIsErrorPasswordSignupDisplayed() {
        Assert.assertTrue("Error about invalid Password is not displayed", isElementDisplayed(errorPasswordSignup));
        return this;
    }

    @Step
    public HomePage loginWithValidCredentials() {
        openLoginPage();
        enterLoginIntoInputLogin(TestData.VALID_LOGIN);
        enterLoginIntoInputPassword(TestData.VALID_PASS);
        clickOnButtonSignIn();
        return new HomePage(webDriver);
    }

    @Step
    public LoginPage loginWithInvalidCredentials() {
        openLoginPage();
        enterLoginIntoInputLogin("qa");
        enterLoginIntoInputPassword("123456qwerty");
        clickOnButtonSignIn();
        return new LoginPage(webDriver);
    }

    @Step
    public LoginPage signUpWithInvalidCredentials() {
        openLoginPage();
        enterUsernameIntoInputUsernameSignUp("tr");
        enterEmailIntoInputEmailSignUp("test.com");
        enterPasswordIntoInputPasswordSignUp("123");
        clickOnButtonSignUp();
        return new LoginPage(webDriver);
    }

    @Step
    public LoginPage checkErrorsMessages(String expectedErrors) {
        String[] expectedErrorsArray = expectedErrors.split(";");
        webDriverWait10.withMessage(" Numbers of messages ").until(ExpectedConditions.numberOfElementsToBe(
                By.xpath(listErrorsLocator), expectedErrorsArray.length
        ));

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