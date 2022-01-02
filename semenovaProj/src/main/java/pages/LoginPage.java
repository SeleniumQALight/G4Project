package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends ParentPage {

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
        try {
            webDriver.findElement(By.xpath(".//input[@name='username' and @placeholder='Username']")).clear();
            webDriver.findElement(By.xpath(".//input[@name='username' and @placeholder='Username']")).sendKeys(login);
            logger.info(login + " was inputted into Input Login");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    public void enterPassWordIntoInputPassword(String password) {
        try {
            webDriver.findElement(By.xpath(".//input[@placeholder='Password']")).clear();
            webDriver.findElement(By.xpath(".//input[@placeholder='Password']")).sendKeys(password);
            logger.info(password + " was inputed");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    public void clickOnButtonSingIn() {
        try {
            webDriver.findElement(By.xpath(".//button[text()='Sign In']")).click();
            logger.info("Button Sign In was clicked");
        }catch (Exception e){
            printErrorAndStopTest(e);
        }
    }

    private void printErrorAndStopTest(Exception e) {
        logger.error("Can not work with element" + e);
        Assert.fail("Can not work with element");
    }
}
