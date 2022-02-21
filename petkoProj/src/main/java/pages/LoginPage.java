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
    @Name("Input login")
    private TextInput inputLoginSignIn;

    @FindBy(xpath = ".//input[@placeholder='Password']")
    private TextInput inputPasswordSignIn;

    @FindBy(xpath = ".//button[text()='Sign In']")
    private Button buttonSignIn;

    @FindBy(xpath = ".//input[@id = 'username-register']")
    private WebElement inputLoginSignUp;

    @FindBy(xpath = ".//input[@id = 'password-register']")
    private WebElement inputPasswordSignUp;

    @FindBy(xpath = ".//input[@id = 'email-register']")
    private WebElement inputEmailSignUp;

    @FindBy(xpath = ".//button[@type = 'submit']")
    private WebElement buttonSignUp;

    @FindBy(xpath = ".//div[text() = 'Username must be at least 3 characters.']")
    private WebElement errorSignUpLoginValidation;

    @FindBy(xpath = ".//div[text() = 'You must provide a valid email address.']")
    private WebElement errorSignUpEmailValidation;

    @FindBy(xpath = ".//div[text() = 'Password must be at least 12 characters.']")
    private WebElement errorSignUpPasswordValidation;

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

    @Step
    public void openLoginPage(){
        try {
            webDriver.get(baseUrl + "/");
            logger.info("Login page was opened");
        }catch (Exception e){
            logger.error("Can not open Login Page" + e);
            Assert.fail("Can not open Login Page" + e);
        }
    }

    @Step
    public void enterLoginIntoInputLogin(String login) {
//        try{
//            inputLoginSignIn.clear();
//            inputLoginSignIn.sendKeys(login);
//            logger.info(login + "was inputted into Input Login");
//        }catch (Exception e) {
//            printErrorAndStopTest(e);
//        }
        enterTextInToElement(inputLoginSignIn, login);
    }

    @Step
    public void enterPassWordIntoInputPassWord(String password){
        enterTextInToElement(inputPasswordSignIn, password);
    }

    @Step
    public LoginPage enterLoginRegistration(String login){
        enterTextInToElement(inputLoginSignUp, login);
        return this;
    }

    @Step
    public LoginPage enterEmailRegistration(String email){
        enterTextInToElement(inputEmailSignUp, email);
        return this;
    }

    @Step
    public LoginPage enterPasswordRegistration(String password){
        enterTextInToElement(inputPasswordSignUp, password);
        return this;
    }

    @Step
    public LoginPage checkErrorsMessages(String expectedErrors) {
        String[] expectedErrorsArray = expectedErrors.split(";");
        webDriverWait5.withMessage("Numbers of messages ")
                        .until(ExpectedConditions.numberOfElementsToBe(By.xpath(listErrorsLocator),expectedErrorsArray.length));
        Assert.assertEquals("", expectedErrorsArray.length, listOfErrors.size());
        ArrayList<String> actualTextFromErrors = new ArrayList<>();
        for (WebElement element:listOfErrors) {
            actualTextFromErrors.add(element.getText());
        }

        SoftAssertions softAssertions = new SoftAssertions();

        for (int i = 0; i == expectedErrors.length(); i++) {
            softAssertions.assertThat(expectedErrorsArray[i]).isIn(actualTextFromErrors);
        }

        softAssertions.assertAll();
        return this;
    }

    @Step
    public void clickOnButtonSignIn(){
        clickOnElement(buttonSignIn);
    }

    @Step
    public void clickOnButtonSignUp(){
        clickOnElement(buttonSignUp);
    }

    @Step
    public HomePage loginWithValidCred(){
        openLoginPage();
        enterLoginIntoInputLogin(TestData.VALID_LOGIN);
        enterPassWordIntoInputPassWord(TestData.VALID_PASS);
        clickOnButtonSignIn();
        return new HomePage(webDriver);
    }

    @Step
    public boolean isButtonSignInDisplayed(){
        return isElementDisplayed(buttonSignIn);
    }

    public boolean isErrorLoginValidationForSignUpDisplayed(){
       return isElementDisplayed(errorSignUpLoginValidation);
    }

    public boolean isErrorEmailValidationForSignUpDisplayed(){
       return isElementDisplayed(errorSignUpEmailValidation);
    }

    public boolean isErrorPasswordValidationForSignUpDisplayed(){
        return isElementDisplayed(errorSignUpPasswordValidation);
    }

}
