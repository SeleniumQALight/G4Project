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

public class LoginPage extends ParentPage{
    @FindBy(xpath = ".//input[@name='username' and @placeholder='Username']")
    private WebElement inputLoginSingIn;
    @FindBy(xpath = ".//input[@placeholder='Password']")
    private WebElement inputPassWordSingIn;
    @FindBy(xpath = ".//button[text()='Sign In']")
    private WebElement buttonSingIn;
    @FindBy(id = "username-register")
    private WebElement inputLoginRegistration;
    @FindBy(id = "email-register")
    private WebElement inputEmailRegistration;
    @FindBy(id = "password-register")
    private WebElement inputPasswordRegistration;
    @FindBy(xpath = ".//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']")
    private List<WebElement> listOfErrors;

    private String listErrorsLocator = ".//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']";

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRalativeUrl() {
        return "/";
    }

    public void openLoginPage(){
        try{
            webDriver.get(baseUrl + "/");
            logger.info("Login page was opened");
        }catch (Exception e){
            logger.error("Can not open Login Page" + e);
            Assert.fail("Can not open Login Page" + e);
        }

    }

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

    public void clickOnButtonSingIn(){
//        try{
//            buttonSingIn.click();
//            logger.info("Button Sign In was clicked");
//        }catch (Exception e){
//            printErrorAndStopTest(e);
//        }
        clickOnElement(buttonSingIn);
    }

    private void printErrorAndStopTest(Exception e) {
        logger.error("Can not work with element" +e);
        Assert.fail("Can not work with element" + e);
    }

    public HomePage loginWithValidCred(){
        openLoginPage();
        enterLoginIntoInputLogin(TestData.VALID_LOGIN);
        enterPassWordIntoInputPassWord(TestData.VALID_PASS);
        clickOnButtonSingIn();
        return new HomePage(webDriver);
    }

    public boolean errorMessageMainPage() {
        try {
            return webDriver.findElement(By.xpath(".//div[@Class = 'alert alert-danger text-center']")).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public LoginPage enterLoginRegistration(String login) {
        enterTextIntoElement(inputLoginRegistration, login);
        return this;
    }

    public LoginPage enterEmailRegisstration(String email) {
        enterTextIntoElement(inputEmailRegistration, email);
        return this;
    }

    public LoginPage enterPasswordRegistration(String password) {
        enterTextIntoElement(inputPasswordRegistration, password);
        return this;
    }

    public LoginPage checkErrorsMessages(String expectedErrors) {
        String[] expectedErrorsArray = expectedErrors.split(";");
        webDriverWait10.withMessage("Numbers of messages ")
                .until(ExpectedConditions.numberOfElementsToBe(
                By.xpath(listErrorsLocator), expectedErrorsArray.length
        ));
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
}
