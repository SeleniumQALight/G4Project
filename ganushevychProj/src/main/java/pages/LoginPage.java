package pages;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import io.qameta.allure.Step;
import libs.TestData;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.TextInput;

public class LoginPage extends ParentPage {
    @FindBy(xpath = ".//input[@name='username' and @placeholder='Username']")
    @Name("Input Login")
    private TextInput inputLoginSingIn;

    @FindBy(xpath = ".//input[@placeholder='Password']")
    private TextInput inputPassWordSingIn;

    @FindBy(xpath = ".//button[text()='Sign In']")
    private Button buttonSingIn;

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

    private String listErrorsLocator =".//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']";

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/";
    }

    @Step
    public void openLoginPage(){
        try{
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
//            inputLoginSingIn.clear();
//            inputLoginSingIn.sendKeys(login);
//            logger.info(login + " was inputted into Input Login");
//        }catch (Exception e){
//            printErrorAndStopTest(e);
//        }

        enterTextInToElement(inputLoginSingIn, login);
    }
    @Step
    public void enterPassWordIntoInputPassWord(String passWord){
        enterTextInToElement(inputPassWordSingIn, passWord);
    }
    @Step
    public void clickOnButtonSingIn(){
        clickOnElement(buttonSingIn);
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
        webDriverWait10.withMessage("Numbers of messages ")
                .until(ExpectedConditions.numberOfElementsToBe(
                By.xpath(listErrorsLocator), expectedErrorsArray.length
                                                                     ));
        Assert.assertEquals("", expectedErrorsArray.length, listOfErrors.size());
        ArrayList<String> actualTextFromErrors  = new ArrayList<>();
        for (WebElement element: listOfErrors) {
            actualTextFromErrors.add(element.getText());
        }
        SoftAssertions softAssertions = new SoftAssertions();

        for (int i = 0; i < expectedErrorsArray.length; i++) {
            softAssertions.assertThat(expectedErrorsArray[i]).isIn(actualTextFromErrors);
        }

        softAssertions.assertAll();
        logger.info("Error messages above registration inputs were checked");
        return this;
    }

    public void checkAlertMessageText(String messageText) {
        Assert.assertEquals("Message in Center ", messageText, alertInCenter.getText());
    }
}
