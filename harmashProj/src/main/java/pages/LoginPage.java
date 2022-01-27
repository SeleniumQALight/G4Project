package pages;

import libs.TestData;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends ParentPage {

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/";
    }

    @FindBy(xpath=".//input[@name='username' and @placeholder='Username']")
    private WebElement inputLoginSignIn;
    @FindBy(xpath = ".//input[@placeholder='Password']")
    private WebElement inputPassWordSignIn;
    @FindBy(xpath = ".//button[text()='Sign In']")
    private WebElement buttonSignIn;

    public void openLoginPage() {
        try {
            webDriver.get(baseUrl + "/");
            logger.info("Login page was opened");


        } catch (Exception e) {
            logger.error("Can not open Login Page" + e);
            Assert.fail("Can not open Login Page" + e);
        }
    }

    public void enterLoginIntoInputLogin(String login) {
//        try {
//            inputLoginSignIn.clear();
//            inputLoginSignIn.sendKeys(login);
//            logger.info(login + " was inputted into input login");
//        } catch (Exception e) {
//            printErrorAndStopTest(e);
//        }
        enterTextInToElement(inputLoginSignIn,login);
    }

    public void enterPasswordIntoInputPassWord(String passWord) {
        enterTextInToElement(inputPassWordSignIn,passWord);
    }

    public void clickOnButtonSignIn() {
        clockOnElement(buttonSignIn);
    }

    public HomePage loginWithValidCred(){
        openLoginPage();
        enterLoginIntoInputLogin(TestData.VALID_LOGIN);
        enterPasswordIntoInputPassWord(TestData.VALID_PASS);
        clickOnButtonSignIn();
        return new HomePage(webDriver);

    }


}
