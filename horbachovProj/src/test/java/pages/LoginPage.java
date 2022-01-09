package pages;

import libs.TestData;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.security.spec.ECField;


public class LoginPage extends ParentPage {

    @FindBy(xpath = ".//input[@name='username' and @placeholder='Username']")
    private WebElement inputLoginSignIn;

    @FindBy(xpath = ".//input[@placeholder='Password']")
    private WebElement inputPasswordSignIn;

    @FindBy(xpath = ".//button[text()='Sign In']")
    private WebElement buttonSignIn;


    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void openLoginPage() {
        try {
            webDriver.get("https://qa-complex-app-for-testing.herokuapp.com");
            logger.info("Login page was opened");
        } catch (Exception e) {
            logger.error("Cannot open Login Page" + e);
            Assert.fail("Cannot open Login Page" + e);
        }
    }

    public void enterLoginIntoInputLogin(String login) {
//        try {
//            inputLoginSignIn.clear();
//            inputLoginSignIn.sendKeys(login);
//            logger.info(login + " was inputted into Input Login");
//    } catch (Exception e) {
//            printErrorAndStopTest(e);
//        }
        enterTextIntoElement(inputLoginSignIn, login);
}
public void enterPasswordIntoInputPassword(String password){
//        try {
//            inputPasswordSignIn.clear();
//            inputPasswordSignIn.sendKeys(password);
//            logger.info(password + " was inputted ");
//
//} catch (Exception e) {
//            printErrorAndStopTest(e);
//        }
    enterTextIntoElement(inputPasswordSignIn, password);
}

public void clickOnButtonSignIn() {
//    try {
//        buttonSignIn.click();
//        logger.info("Button Sign In was clicked");
//    } catch (Exception e) {
//        printErrorAndStopTest(e);
//    }
    clickOnElement(buttonSignIn);
}

public HomePage loginWithValidCred(){
        openLoginPage();
        enterLoginIntoInputLogin(TestData.VALID_LOGIN);
        enterPasswordIntoInputPassword(TestData.VALID_PASS);
        clickOnButtonSignIn();
        return new HomePage(webDriver);

    }

}
