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
    private WebElement inputPassWordSingIn;
    @FindBy(xpath = ".//button[text()='Sign In']")
    private Button buttonSingIn;
    @FindBy(id = "username-register")
    private WebElement inputLoginRegistration;
    @FindBy(id = "email-register")
    private WebElement inputEmailRegistration;
    @FindBy(id = "password-register")
    private WebElement inputPasswordRegistration;
    @FindBy(xpath = ".//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']")
    private List<WebElement> listOfErrors;

    private String listErrorsLocator = ".//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']";

    @FindBy(xpath = ".//input[@id= 'username-register']")
    private WebElement inputUsernameRegister;
    @FindBy(xpath = ".//input[@id= 'email-register']")
    private WebElement inputEmailRegister;
    @FindBy(xpath = ".//input[@id= 'password-register']")
    private WebElement inputPasswordRegister;
    @FindBy(xpath = ".//div[text()='Username must be at least 3 characters.' and @class =  'alert alert-danger small liveValidateMessage liveValidateMessage--visible'] ")
    private WebElement invalidMassageUsername;
    @FindBy(xpath = ".//div[text()='You must provide a valid email address.' and @class =  'alert alert-danger small liveValidateMessage liveValidateMessage--visible'] ")
    private WebElement invalidMessageEmail;
    @FindBy(xpath = ".//div[text()='Password must be at least 12 characters.' and @class = 'alert alert-danger small liveValidateMessage liveValidateMessage--visible'] ")
    private WebElement invalidMessagePassword;
    @FindBy(xpath = ".//button[text()='Sign up for OurApp']")
    private WebElement buttonSingOurApp;

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRalativeUrl() {
        return "/";
    }

    @Step
    public void openLoginPage(){
        try{
            webDriver.get(baseUrl + "/");
            logger.info("Login page was opened" + baseUrl + "/");
        }catch (Exception e){
            logger.error("Can not open Login Page" + e);
            Assert.fail("Can not open Login Page" + e);
        }

    }

    @Step
    public void enterLoginIntoInputLogin(String login) {
//        try{
//            inputLoginSingIn.clear();
//            inputLoginSingIn.sendKeys(login);
//            logger.info(login + " was inputed into Lolin");
//        }catch (Exception e){
//            printErrorAndStopTest(e);
//        }
        enterTextIntoElement(inputLoginSingIn, login);
    }

    @Step
    public void enterPassWordIntoInputPassWord(String passWord){
//        try {
//            inputPassWordSingIn.clear();
//            inputPassWordSingIn.sendKeys(passWord);
//            logger.info(passWord + "was inputed");
//        }catch (Exception e){
//            printErrorAndStopTest(e);
//        }
        enterTextIntoElement(inputPassWordSingIn, passWord);
    }

    @Step
    public void clickOnButtonSingIn(){
//        try{
//            buttonSingIn.click();
//            logger.info("Button Sign In was clicked");
//        }catch (Exception e){
//            printErrorAndStopTest(e);
//        }
        clickOnElement(buttonSingIn);
    }

    @Step
    private void printErrorAndStopTest(Exception e) {
        logger.error("Can not work with element" +e);
        Assert.fail("Can not work with element" + e);
    }

    @Step
    public HomePage loginWithValidCred(){
        openLoginPage();
        enterLoginIntoInputLogin(TestData.VALID_LOGIN);
        enterPassWordIntoInputPassWord(TestData.VALID_PASS);
        clickOnButtonSingIn();
        return new HomePage(webDriver);
    }

    @Step
    public boolean errorMessageMainPage() {
        try {
            return webDriver.findElement(By.xpath(".//div[@Class = 'alert alert-danger text-center']")).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    @Step
    public LoginPage enterLoginRegistration(String login) {
        enterTextIntoElement(inputLoginRegistration, login);
        return this;
    }

    @Step
    public LoginPage enterEmailRegisstration(String email) {
        enterTextIntoElement(inputEmailRegistration, email);
        return this;
    }

    @Step
    public LoginPage enterPasswordRegistration(String password) {
        enterTextIntoElement(inputPasswordRegistration, password);
        return this;
    }

    @Step
    public LoginPage checkErrorsMessages(String expectedErrors) {
        String[] expectedErrorsArray = expectedErrors.split(";");
        webDriverWait10.withMessage("Numbers of messages ")
                .until(ExpectedConditions.numberOfElementsToBe(
                By.xpath(listErrorsLocator), expectedErrorsArray.length
        ));
        Assert.assertEquals("", expectedErrorsArray.length, listOfErrors.size());
        ArrayList<String> actualTextFromErrors = new ArrayList<>();
        for (WebElement element: listOfErrors) {
            actualTextFromErrors.add(element.getText());
        }
        SoftAssertions softAssertions = new SoftAssertions();

        for (int i = 0; i < expectedErrorsArray.length; i++) {
            softAssertions.assertThat(expectedErrorsArray[i]).isIn(actualTextFromErrors);
        }

        softAssertions.assertAll();
        return this;
    }




    public LoginPage clickOnButtonSingOurApp() {
        clickOnElement(buttonSingOurApp);
        return new LoginPage(webDriver);
    }


    public LoginPage checkIsInputUsernameRegisterDisplayed() {
        //Assert.assertTrue("-----------------------Input username register is not displayed", isElementDisplayed(inputUsernameRegister));
        checkIsElementDisplayed("Input username register is not displayed",inputUsernameRegister);
        return this;
    }
    public LoginPage checkIsInputEmailRegisterDisplayed() {
        //Assert.assertTrue("Input email register is not displayed", isElementDisplayed(inputEmailRegister));
        checkIsElementDisplayed("Input email register is not displayed",inputEmailRegister);
        return this;
    }
    public LoginPage checkIsInputPasswordRegisterDisplayed() {
        //Assert.assertTrue("Input password register is not displayed", isElementDisplayed(inputPasswordRegister));
        checkIsElementDisplayed("Input password register is not displayed",inputPasswordRegister);
        return this;
    }

    public LoginPage checkIsInvalidMassageUsernameDisplayed() {
        //Assert.assertTrue("Invalid massage username is not displayed", isElementDisplayed(invalidMassageUsername));
        checkIsElementDisplayed("Invalid message username is not displayed",invalidMassageUsername);
        return this;
    }
    public LoginPage checkIsInvalidMassageEmailDisplayed() {
        //Assert.assertTrue("Invalid massage email is not displayed", isElementDisplayed(invalidMassageEmail));
        checkIsElementDisplayed("Invalid massage email is not displayed",invalidMessageEmail);
        return this;
    }
    public LoginPage checkIsInvalidMassagePasswordDisplayed() {
        //Assert.assertTrue("Invalid massage password is not displayed", isElementDisplayed(invalidMassagePassword));
        checkIsElementDisplayed("Invalid massage password is not displayed",invalidMessagePassword);
        return this;
    }

    public LoginPage enterTextIntoUsernameRegisterInput(String text) {
        enterTextIntoElement(inputUsernameRegister, text);
        return this;
    }
    public LoginPage enterTextIntoEmailRegisterInput(String text) {
        enterTextIntoElement(inputEmailRegister, text);
        return this;
    }
    public LoginPage enterTextIntoPasswordRegisterInput(String text) {
        enterTextIntoElement(inputPasswordRegister, text);
        return this;
    }
}
