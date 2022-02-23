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
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.TextInput;

import java.util.ArrayList;
import java.util.List;

public class LoginPage extends ParentPage {
    @FindBy(xpath = ".//input[@name='username' and @placeholder='Username']")
    @Name("Login field")
    private TextInput inputLoginSignIn;

    @FindBy(xpath = ".//input[@placeholder='Password']")
    @Name("Password field")
    private TextInput inputPasswordSignIn;

    @FindBy(xpath = ".//button[text()='Sign In']")
    @Name("Sign in")
    private Button buttonSignIn;

    @FindBy(xpath = ".//input[@id='username-register']")
    private WebElement inputUserNameSignUpForm;

    @FindBy(xpath = ".//input[@id='email-register']")
    private WebElement inputEmailSignUpForm;

    @FindBy(xpath = ".//input[@id='password-register']")
    private WebElement inputPasswordSignUpForm;

    @FindBy(xpath = ".//button[@type='submit']")
    private WebElement buttonSignUp;

    @FindBy(xpath = ".//div[text()='Username must be at least 3 characters.']")
    private WebElement alertTextUserNameAtLeast3Chars;

    @FindBy(xpath = ".//div[text()='You must provide a valid email address.']")
    private WebElement alertTextEmailMustBeValid;

    @FindBy(xpath = ".//div[text()='Password must be at least 12 characters.']")
    private WebElement alertPasswordAtLeast12Chars;

    @FindBy(xpath = ".//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']")
    private List<WebElement> listErrorsList;


    private String listErrors = ".//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']";

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
            logger.info("Login page has been opened");
        } catch (Exception e) {
            logger.error("Can not open Login page " + e);
            Assert.fail("Can not open Login page " + e);
        }
    }

    public void enterLoginIntoInputLogin(String login) {
        enterTextIntoElement(inputLoginSignIn, login);
    }

    public void enterPasswordIntoInputPassword(String password) {
        enterTextIntoElement(inputPasswordSignIn, password);
    }

    public void clickOnButton() {
        clickOnElement(buttonSignIn);
    }

    public LoginPage enterUsernameIntoInputUsernameSignUpForm(String username){
        enterTextIntoElement(inputUserNameSignUpForm, username);
        return this;
    }

    public LoginPage enterEmailIntiEmailInputSignUpForm(String email){
        enterTextIntoElement(inputEmailSignUpForm, email);
        return this;
    }

    public LoginPage enterPasswordIntoPasswordInputSignUpForm(String password){
        enterTextIntoElement(inputPasswordSignUpForm, password);
        return this;
    }

    public void clickOnSignUpButton(){
        clickOnElement(buttonSignUp);
    }

    public boolean userNameAlertTextIsVisible(){
        return elementIsVisible(alertTextUserNameAtLeast3Chars);
    }

    public boolean emailAlertTextIsVisible(){
        return elementIsVisible(alertTextEmailMustBeValid);
    }

    public boolean passwordAlertTextIsVisible(){
        return elementIsVisible(alertPasswordAtLeast12Chars);
    }

    public boolean signUpButtonIsVisible(){
        return elementIsVisible(buttonSignUp);
    }

    public boolean signInButtonIsVisible(){
        return elementIsVisible(buttonSignIn);
    }

    public HomePage loginWithValidCredentials(){
        openLoginPage();
        enterLoginIntoInputLogin(TestData.VALID_LOGIN);
        enterPasswordIntoInputPassword(TestData.VALID_PASS);
        clickOnButton();
        return new HomePage(webDriver);
    }

    public LoginPage checkErrorMessages(String expectedErrors) {
        String[] errorsArray = expectedErrors.split(";");
        webDriverWait10.withMessage("Number of messages ").until(ExpectedConditions.numberOfElementsToBe(By.xpath(listErrors),errorsArray.length));

        ArrayList<String> actualTextFromErrors = new ArrayList<>();
        for (WebElement element: listErrorsList) {
            actualTextFromErrors.add(element.getText());
        }
        SoftAssertions softAssertions = new SoftAssertions();

        for (int i = 0; i < errorsArray.length; i++) {
            softAssertions.assertThat(errorsArray[i]).isIn(actualTextFromErrors);
        }
        softAssertions.assertAll();
        return this;
    }
}
