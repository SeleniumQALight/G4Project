package pages;

import libs.TestData;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends ParentPage {
    @FindBy(xpath = ".//input[@name='username' and @placeholder='Username']")
    private WebElement inputLoginSingIn;

    @FindBy(xpath = ".//input[@placeholder='Password']")
    private WebElement inputPasswordSingIn;

    @FindBy(xpath = ".//button[text()='Sign In']")
    private WebElement buttonSingIn;


    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void openLoginPage() {
        try {
            webDriver.get("https://qa-complex-app-for-testing.herokuapp.com/");
            logger.info("Login page was opened");
        } catch (Exception e) {
            logger.error("Can not open Login Page" + e);
            Assert.fail("Can not open Login Page" + e);
        }
    }

    public void enterLoginIntoInputLogin(String login) {
//        try {
//            inputLoginSingIn.clear();
//            inputLoginSingIn.sendKeys(login);
//            logger.info(login + " was inputted into Input Login");
//        } catch (Exception e) {
//            printErrorAndStopTest(e);
//        }
        enterTextIntoElement(inputLoginSingIn, login);
    }

    public void enterPassWordIntoInputPassword(String password) {
//        try {
//            inputPasswordSingIn.clear();
//            inputPasswordSingIn.sendKeys(password);
//            logger.info(password + " was inputted");
//        } catch (Exception e) {
//            printErrorAndStopTest(e);
//        }
        enterTextIntoElement(inputPasswordSingIn, password);
    }

    public void clickOnButtonSingIn() {
//        try {
//            buttonSingIn.click();
//            logger.info("Button Sign In was clicked");
//        }catch (Exception e){
//            printErrorAndStopTest(e);
//        }
        clickOnEltment(buttonSingIn);
    }

    public HomePage loginWithValidCred(){
      openLoginPage();
      enterLoginIntoInputLogin(TestData.VALID_LOGIN);
      enterPassWordIntoInputPassword(TestData.VALID_PASS);
      clickOnButtonSingIn();
      return new HomePage(webDriver);
    }
}
