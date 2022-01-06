package pages;

import libs.TestData;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends ParentPage {
    @FindBy(xpath = ".//input[@name='username' and @placeholder='Username']")
    private WebElement inputLoginSignIn;

    @FindBy(xpath = ".//input[@placeholder='Password']")
    private WebElement inputPasswordSignIn;

    @FindBy(xpath = ".//button[text()='Sign In']")
    private WebElement buttonSignIn;

    @FindBy(xpath = ".//div[text()='Error']")
    private WebElement messageError;

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void openLoginPage() {
        try {
            webDriver.get("https://qa-complex-app-for-testing.herokuapp.com/");
            logger.info("LoginPage was open");
        } catch (Exception e) {
            logger.error("Cannot open LoginPage " + e);
            Assert.fail("Cannot open LoginPage " + e);
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
        enterTextIntoElement(inputLoginSignIn, login);

    }

    public void enterLoginIntoInputPassword(String password) {
        enterTextIntoElement(inputPasswordSignIn, password);
    }

    public void clickOnButtonSignIn() {
      clickOnElement(buttonSignIn);
    }

    public boolean isErrorMessageDisplayed() {
        try {
            return messageError.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public HomePage loginWithValidCredentials(){
        openLoginPage();
        enterLoginIntoInputLogin(TestData.VALID_LOGIN);
        enterLoginIntoInputPassword(TestData.VALID_PASS);
        clickOnButtonSignIn();
        return new HomePage(webDriver);
    }
}