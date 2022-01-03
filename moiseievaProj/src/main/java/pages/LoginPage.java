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
            logger.info("Login Page was opened");
        } catch (Exception e) {
            logger.error("Can not open Login Page " + e);
            Assert.fail("Can not open Login Page " + e);
        }
    }

    public void enterLoginIntoInputLogin(String login) {
        try {
            webDriver.findElement(By.xpath("//header//input[@name='username']")).clear();
            webDriver.findElement(By.xpath("//header//input[@name='username']")).sendKeys(login);
            logger.info(login + " was inputted into Input Login");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    public void enterPasswordIntoInputLogin(String password) {
        try {
            webDriver.findElement(By.xpath("//header//input[@name='password']")).clear();
            webDriver.findElement(By.xpath("//header//input[@name='password']")).sendKeys(password);
            logger.info(password + " was inputted");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    public void clickOnButtonSignIn() {
        try {
            webDriver.findElement(By.xpath("//header//button")).click();
            logger.info("Button Sign In was clicked");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    private void printErrorAndStopTest(Exception e) {
        logger.error("Can not work with element " + e);
        Assert.fail("Can not work with element " + e);
    }

    public boolean isButtonSignInDisplayed() {
        try {
            return webDriver.findElement(By.xpath("//header//button")).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String isErrorTextDisplayed() {
        try {
            return webDriver.findElement(By.xpath("//div[@class='alert alert-danger text-center']")).getText();
        } catch (Exception e) {
            return "Text was not found";
        }
    }
}
