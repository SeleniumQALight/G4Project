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

    @FindBy(xpath = "//header//input[@name='username']")
    private WebElement inputLoginSignIn;
    @FindBy(xpath = "//header//input[@name='password']")
    private WebElement inputPasswordSignIn;
    @FindBy(xpath = "//header//button")
    private WebElement buttonSignIn;

    public void openLoginPage() {
        try {
            webDriver.get("https://qa-complex-app-for-testing.herokuapp.com/");
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
}
