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

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }


    public void openLoginPage() {
        try {
            webDriver.get("https://qa-complex-app-for-testing.herokuapp.com/");
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

    public void clickOnButtonSignIn() {
        clickOnElement(buttonSignIn);
    }

    public HomePage loginWithValidCredentials(){
        openLoginPage();
        enterLoginIntoInputLogin(TestData.VALID_LOGIN);
        enterPasswordIntoInputPassword(TestData.VALID_PASS);
        clickOnButtonSignIn();
        return new HomePage(webDriver);
    }
}
