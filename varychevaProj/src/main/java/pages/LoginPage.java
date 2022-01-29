package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static libs.TestData.VALID_LOGIN;
import static libs.TestData.VALID_PASS;
import static org.junit.Assert.fail;

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
            webDriver.get(baseUrl + "/");
            logger.info("Login page was opened");
        } catch (Exception e) {
            logger.error("Can not open Login Page" + e);
            fail("Can not open Login Page" + e);
        }
    }


    public void enterLoginIntoInputLogin(String login) {
        enterTextIntoElement(inputLoginSignIn, login);
    }

    public void enterPassWordIntoInputPassWord(String passWord) {
        enterTextIntoElement(inputPasswordSignIn, passWord);
    }

    public void clickOnButtonSingIn() {
        clickOnElement(buttonSignIn);
    }

    public boolean isButtonSignInDisplayed() {
        try {
            return buttonSignIn.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    String getRelativeUrl() {
        return "/";
    }

    public HomePage loginWithValidCred() {
        openLoginPage();
        enterLoginIntoInputLogin(VALID_LOGIN);
        enterPassWordIntoInputPassWord(VALID_PASS);
        clickOnButtonSingIn();
        return new HomePage(webDriver);
    }
}
