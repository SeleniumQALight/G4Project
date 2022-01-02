package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage  extends ParentPage{
    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    public  void openLoginPage(){
        try{
            webDriver.get("https://qa-complex-app-for-testing.herokuapp.com/");
            logger.info("Login page was open");

        }catch ( Exception e){
            logger.error("Cannot open Login Page" + e);
            Assert.fail("Cannot open Login Page" + e);
        }
    }

    public void enterLoginIntoInputLogin(String login) {
        try{

            webDriver.findElement(By.xpath(".//input[@name='username' and @placeholder='Username']")).clear();
            webDriver.findElement(By.xpath(".//input[@name='username' and @placeholder='Username']")).sendKeys(login);
            logger.info(login + " was inputted into Input Login");

        }catch(Exception e){
            printErrorAndStopTest(e);
        }
    }
    public void enterPasswordIntoInputPassword(String passWord){
        try{
            webDriver.findElement(By.xpath(".//input[@type= 'password' and @placeholder='Password']")).clear();
            webDriver.findElement(By.xpath(".//input[@placeholder='Password']")).sendKeys(passWord);
          logger.info( passWord + " password entered");
        }catch (Exception e){
            printErrorAndStopTest(e);
        }

    }

    public void clickOnButtonSignIn(){
        try{
            webDriver.findElement(By.xpath(".//button[text()='Sign In']")).click();
            logger.info("Button Sign In was clicked");
        } catch (Exception e){
            printErrorAndStopTest(e);
        }
    }

    private void printErrorAndStopTest(Exception e) {
        logger.error("Can not workwith element");
        Assert.fail("Can not workwith element" + e);
    }
}
