package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends ParentPage {
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void openLoginPage(){
        try {
            driver.get("https://qa-complex-app-for-testing.herokuapp.com/");
            logger.info("Login page was opened");
        }catch (Exception e){
            logger.error("Can't open login page" + e);
            Assert.fail("Can't open login page" + e);
        }
    }

    public void enterLoginIntoInputLogin(String login) {
        try {
            driver.findElement(By.xpath(".//input[@name='username' and @placeholder='Username']")).clear();
            driver.findElement(By.xpath(".//input[@name='username' and @placeholder='Username']")).sendKeys(login);
            logger.info(login + " was entered into login field");
        }catch (Exception e){
            printErrorAndStopTest(e);
        }
    }
    public void enterPasswordIntoInputPassword(String pass){
        try {
            driver.findElement(By.xpath(".//input[@type= 'password' and @placeholder='Password']")).clear();
            driver.findElement(By.xpath(".//input[@type= 'password' and @placeholder='Password']")).sendKeys(pass);
            logger.info((pass + " was entered into password field"));
        }catch (Exception e){
            printErrorAndStopTest(e);
        }
    }

    public void clickOnSignIn(){
        try {
            driver.findElement(By.xpath(".//button[text()='Sign In']")).click();
            logger.info("Button Sign In was clicked");
        }catch (Exception e){
            printErrorAndStopTest(e);
        }
    }

    private void printErrorAndStopTest(Exception e) {
        logger.error("Can't work with element" + e);
        Assert.fail("Can't work with element" + e);
    }
}
