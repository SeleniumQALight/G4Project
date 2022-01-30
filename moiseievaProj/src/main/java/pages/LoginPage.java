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
    private WebElement inputLoginSignIn;
    @FindBy(xpath = "//header//input[@name='password']")
    private WebElement inputPasswordSignIn;
    @FindBy(xpath = "//header//button")
    private WebElement buttonSignIn;

    @FindBy(id = "username-register")
    private WebElement inputLoginRegistration;

    @FindBy(id = "email-register")
    private WebElement inputEmailRegistration;

    @FindBy(id = "password-register")
    private WebElement inputPassWordRegistration;




    public void openLoginPage() {
        try {
            webDriver.get(baseUrl + "/");
            logger.info("Login Page was opened");
        } catch (Exception e) {
            logger.error("Can not open Login Page " + e);
            Assert.fail("Can not open Login Page " + e);
        }
    }

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

    public void enterPasswordIntoInputLogin(String password) {
        enterTextToElement(inputPasswordSignIn, password);
    }

    public void clickOnButtonSignIn() {
//        try {
//            buttonSignIn.click();
//            logger.info("Button Sign In was clicked");
//        } catch (Exception e) {
//            printErrorAndStopTest(e);
//        }
        clickOnElement(buttonSignIn);
    }


    public boolean isButtonSignInDisplayed() {
        try {
            return webDriver.findElement(By.xpath("//header//button")).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getTextFromAllert() {
        try {
            return webDriver.findElement(By.xpath("//div[@class='alert alert-danger text-center']")).getText();
        } catch (Exception e) {
            return "Text was not found";
        }
    }

    public HomePage loginWithValidaCred(){
        openLoginPage();
        enterLoginIntoInputLogin(TestData.VALID_LOGIN);
        enterPasswordIntoInputLogin(TestData.VALID_PASS);
        clickOnButtonSignIn();
        return new HomePage(webDriver);
    }
    public LoginPage enterLoginRegistration(String login){
        enterTextToElement(inputLoginRegistration, login);
        return this;
    }

    public LoginPage enterEmailRegistration(String email) {
        enterTextToElement(inputEmailRegistration, email);
        return this;
    }

    public LoginPage enterPassWordRegistration(String pass) {
        enterTextToElement(inputPassWordRegistration, pass);
        return this;
    }

    public LoginPage checkErrorsMessages(String expErrors) {
        String[] expectedErrorsArray = expErrors.split(";");
        webDriverWait10
                .withMessage("Numbers of errors ")
                .until(ExpectedConditions.numberOfElementsToBe(By.xpath(listErrorsLocators), expectedErrorsArray.length));

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
