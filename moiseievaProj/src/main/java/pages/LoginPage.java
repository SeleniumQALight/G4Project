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

    private String listErrorsLocators = ".//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']";

    @FindBy (xpath = "//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']")
    private List<WebElement> listOfErrors;

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/";
    }

    @FindBy(xpath = "//header//input[@name='username']")
    @Name("Input Login")
    private TextInput inputLoginSignIn;
    @FindBy(xpath = "//header//input[@name='password']")
    private TextInput inputPasswordSignIn;
    @FindBy(xpath = "//header//button")
    private Button buttonSignIn;

    @FindBy(id = "username-register")
    private WebElement inputLoginRegistration;

    @FindBy(id = "email-register")
    private WebElement inputEmailRegistration;

    @FindBy(id = "password-register")
    private WebElement inputPassWordRegistration;



    @Step
    public void openLoginPage() {
        try {
            webDriver.get(baseUrl + "/");
            logger.info("Login Page was opened: " + baseUrl);
        } catch (Exception e) {
            logger.error("Can not open Login Page " + e);
            Assert.fail("Can not open Login Page " + e);
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
        enterTextToElement(inputLoginSignIn, login);
    }
    @Step
    public void enterPasswordIntoInputLogin(String password) {
        enterTextToElement(inputPasswordSignIn, password);
    }
    @Step
    public void clickOnButtonSignIn() {
//        try {
//            buttonSignIn.click();
//            logger.info("Button Sign In was clicked");
//        } catch (Exception e) {
//            printErrorAndStopTest(e);
//        }
        clickOnElement(buttonSignIn);
    }

    @Step
    public boolean isButtonSignInDisplayed() {
        try {
            return webDriver.findElement(By.xpath("//header//button")).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    @Step
    public String getTextFromAllert() {
        try {
            return webDriver.findElement(By.xpath("//div[@class='alert alert-danger text-center']")).getText();
        } catch (Exception e) {
            return "Text was not found";
        }
    }
    @Step
    public HomePage loginWithValidaCred(){
        openLoginPage();
        enterLoginIntoInputLogin(TestData.VALID_LOGIN);
        enterPasswordIntoInputLogin(TestData.VALID_PASS);
        clickOnButtonSignIn();
        return new HomePage(webDriver);
    }
    @Step
    public LoginPage enterLoginRegistration(String login){
        enterTextToElement(inputLoginRegistration, login);
        return this;
    }
    @Step
    public LoginPage enterEmailRegistration(String email) {
        enterTextToElement(inputEmailRegistration, email);
        return this;
    }
    @Step
    public LoginPage enterPassWordRegistration(String pass) {
        enterTextToElement(inputPassWordRegistration, pass);
        return this;
    }
    @Step
    public LoginPage checkErrorsMessages(String expErrors) {
        String[] expectedErrorsArray = expErrors.split(";");
        webDriverWait10
                .withMessage("Numbers of errors ")
                .until(ExpectedConditions.numberOfElementsToBe(By.xpath(listErrorsLocators), expectedErrorsArray.length));
        Assert.assertEquals("", expectedErrorsArray.length, listOfErrors.size());
        ArrayList<String> actualTextFromErrors = new ArrayList<>();
        for (WebElement element : listOfErrors){
            actualTextFromErrors.add(element.getText());
        }
        SoftAssertions softAssertions = new SoftAssertions();
        for (int i=0; i<expectedErrorsArray.length; i++){
            softAssertions.assertThat(expectedErrorsArray[i]).isIn(actualTextFromErrors);
        }
        softAssertions.assertAll();

        return this;
    }
}
