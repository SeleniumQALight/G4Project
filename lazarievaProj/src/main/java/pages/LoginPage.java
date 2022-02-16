package pages;


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

public class LoginPage extends ParentPage {
    //Sign IN
    @FindBy(xpath = ".//input[@name='username' and @placeholder='Username']")
    @Name("Input Login")
    //в логах буде вказуватись @Name("Input Login") з 21р додали в ParentPage
    private TextInput inputLoginSignIn;

    @FindBy(xpath = ".//input[@placeholder='Password']")
    private TextInput inputPasswordSignIn;

    @FindBy(xpath = ".//button[text()='Sign In']")
    private WebElement buttonSignIn;
    // Sign Up Form
    @FindBy(xpath = ".//*[@type='submit']")
    private WebElement buttonSignUPForOurApp;

    @FindBy(xpath = ".//input[@id='username-register']")
    private WebElement inputUserNameInSignUpForm;

    @FindBy(xpath = ".//input[@id='email-register']")
    private WebElement inputEmailInSignUpForm;

    @FindBy(xpath = ".//input[@id='password-register']")
    private WebElement inputUserPassWordInSignUpForm;

    @FindBy(xpath = ".//div[text()='Username must be at least 3 characters.']")
    private WebElement validationUserNameMessage;

    @FindBy(xpath = ".//div[text()='You must provide a valid email address.']")
    private WebElement validationUserEmailMessage;

    @FindBy(xpath = ".//div[text()='Password must be at least 12 characters.']")
    private WebElement validationUserPasswordMessage;

    @FindBy(xpath = ".//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']")
    private List<WebElement> listOfErrors;

    private String listErrorsLocator = ".//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']";


    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/";
    }

    public void openLoginPage() {
        try {
            webDriver.get("https://qa-complex-app-for-testing.herokuapp.com/");
            logger.info("Login Page was opened");

        } catch (Exception e) {
            logger.error("Can not open Login Page " + e);
            //Assert message will send in the Test Report
            Assert.fail("Can not open Login Page " + e);
        }
    }

    public void enterLoginIntoInputLogin(String login) {
//        try {
//            inputLoginSignIn.clear();
//            inputLoginSignIn.sendKeys(login);
//            logger.info(login + " was inputted into Input Login field");
//
//        } catch (Exception e) {
//            printErrorAndStopTest(e);
//        }
        enterTextInToElement(inputLoginSignIn, login);
    }

    public void enterPasswordIntoInputPassword(String password) {
        enterTextInToElement(inputPasswordSignIn, password);
    }

    public void clickOnButtonSignIn() {
        clickOnElement(buttonSignIn);
    }

    public HomePage loginWithValidCred() {
        openLoginPage();
        enterLoginIntoInputLogin(TestData.VALID_LOGIN);
        enterPasswordIntoInputPassword(TestData.VALID_PASS);
        clickOnButtonSignIn();
        return new HomePage(webDriver);
    }

    public boolean verifyIfTheSignUPButtonIsDisplayed() {
        return isElementDisplayed(buttonSignUPForOurApp);
    }

    public LoginPage enterUserNameInTheSignUpForm(String userName) {
        enterTextInToElement(inputUserNameInSignUpForm, userName);
        return this;
    }

    public LoginPage enterUserEmailInTheSignUpForm(String userEmail) {
        enterTextInToElement(inputEmailInSignUpForm, userEmail);
        return this;
    }

    public LoginPage enterUserPasswordInTheSignUpForm(String userPassword) {
        enterTextInToElement(inputUserPassWordInSignUpForm, userPassword);
        return this;
    }

    public void clickOnButtonSignUpForOurApp() {
        clickOnElement(buttonSignUPForOurApp);
    }

    public LoginPage enterInvalidDataInTheSignUpForm() {
        enterUserNameInTheSignUpForm("tr");
        enterUserEmailInTheSignUpForm("test.com");
        enterUserPasswordInTheSignUpForm("123");
        clickOnButtonSignUpForOurApp();
        return new LoginPage(webDriver);
    }

    public boolean isValidationUserNameMessageDisplayed() {
        return isElementDisplayed(validationUserNameMessage);

    }

    public boolean isValidationUserEmailMessageDisplayed() {
        return isElementDisplayed(validationUserEmailMessage);

    }

    public boolean isValidationUserPasswordMessageDisplayed() {
        return isElementDisplayed(validationUserPasswordMessage);

    }

    public LoginPage checkErrorMessages(String expectedErrors) {
        //depends on the amount of the errors -1.2.3
        String[] expectedErrorsArray = expectedErrors.split(";");
        webDriverWait10.withMessage("Number of messages");
        webDriverWait10.until(ExpectedConditions.numberOfElementsToBe(
                By.xpath(listErrorsLocator), expectedErrorsArray.length));
        Assert.assertEquals(" ", expectedErrorsArray.length, listOfErrors.size());

        ArrayList<String> actualTextFromErrors = new ArrayList<>();
        for (WebElement element : listOfErrors) {
            actualTextFromErrors.add(element.getText());

        }
        SoftAssertions softAssertions = new SoftAssertions();
        for (int i = 0; i < expectedErrorsArray.length; i++) {
            softAssertions.assertThat(expectedErrorsArray[i]).isIn(actualTextFromErrors);
        }

        //assertAll - verifying all messages!
        softAssertions.assertAll();

        return this;
    }
}

