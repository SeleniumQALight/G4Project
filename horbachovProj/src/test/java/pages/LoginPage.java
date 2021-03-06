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

import java.security.spec.ECField;
import java.util.ArrayList;
import java.util.List;


public class LoginPage extends ParentPage {

    @FindBy(xpath = ".//input[@name='username' and @placeholder='Username']")
    @Name("Input Login")
    private TextInput inputLoginSingIn;

    @FindBy(xpath = ".//input[@placeholder='Password']")
    private TextInput inputPasswordSingIn;

    @FindBy(xpath = ".//button[text()='Sign In']")
    private Button buttonSingIn;

    @FindBy(xpath = ".//button[text()='Sign up for OurApp']")
    private Button buttonSignUp;

    @FindBy(id = "username-register")
    private WebElement inputLoginRegistration;

    @FindBy(id = "email-register")
    private WebElement inputEmailRegistration;

    @FindBy(id = "password-register")
    private WebElement inputPassWordRegistration;

    @FindBy(xpath = ".//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']")
    private List<WebElement> listOfErrors;

    @FindBy(xpath = ".//*[contains(@class,'alert alert-danger text-center')]")
    private WebElement alertInCenter;


    private String listErrorsLocator = ".//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']";


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
            logger.info("Login page was opened " + baseUrl + "/");
        } catch (Exception e) {
            logger.error("Cannot open Login Page" + e);
            Assert.fail("Cannot open Login Page" + e);
        }
    }
    @Step
    public void enterLoginIntoInputLogin(String login) {
//        try {
//            inputLoginSignIn.clear();
//            inputLoginSignIn.sendKeys(login);
//            logger.info(login + " was inputted into Input Login");
//    } catch (Exception e) {
//            printErrorAndStopTest(e);
//        }
        enterTextIntoElement(inputLoginSingIn, login);
}
    @Step
public void enterPasswordIntoInputPassword(String password){
//        try {
//            inputPasswordSignIn.clear();
//            inputPasswordSignIn.sendKeys(password);
//            logger.info(password + " was inputted ");
//
//} catch (Exception e) {
//            printErrorAndStopTest(e);
//        }
    enterTextIntoElement(inputPasswordSingIn, password);
}

    @Step
public void clickOnButtonSignIn() {
//    try {
//        buttonSignIn.click();
//        logger.info("Button Sign In was clicked");
//    } catch (Exception e) {
//        printErrorAndStopTest(e);
//    }
    clickOnElement(buttonSingIn);
}

    public void clickOnButtonSignUp() {

        clickOnElement(buttonSignUp);
    }

public HomePage loginWithValidCred(){
        openLoginPage();
        enterLoginIntoInputLogin(TestData.VALID_LOGIN);
        enterPasswordIntoInputPassword(TestData.VALID_PASS);
        clickOnButtonSignIn();
        return new HomePage(webDriver);

    }

    public LoginPage enterLoginRegistration(String login) {
        enterTextIntoElement(inputLoginRegistration, login);
        return this;
    }
    public LoginPage enterEmailRegistration(String email) {
        enterTextIntoElement(inputEmailRegistration, email);
        return this;
    }

    public LoginPage enterPasswordRegistration(String password) {
        enterTextIntoElement(inputPassWordRegistration, password);
        return this;
    }

    public LoginPage checkErrorsMessages(String expectedErrors) {
        String[] expectedErrorsArray = expectedErrors.split(";");
        webDriverWait10.withMessage("Number of messages ")
                .until(ExpectedConditions.numberOfElementsToBe(
                        By.xpath(listErrorsLocator), expectedErrorsArray.length));
        Assert.assertEquals("", expectedErrorsArray.length, listOfErrors.size());
        ArrayList<String> actualTextFromErrors = new ArrayList<>();
        for (WebElement element:listOfErrors
             ) {
            actualTextFromErrors.add(element.getText());
        }
        SoftAssertions softAssertions = new SoftAssertions();

        for (int i = 0; i < expectedErrorsArray.length; i++) {
            softAssertions.assertThat(expectedErrorsArray[i]).isIn(actualTextFromErrors);

        }

        softAssertions.assertAll();
        return this;
    }

    public void checkAlertMessageText(String messageText) {
        Assert.assertEquals("Message in Center ", messageText, alertInCenter.getText());
    }
}
