package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends ParentPage { //alt + entr создать конструктор

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void openLoginPage() {
        try {
            webDriver.get("https://qa-complex-app-for-testing.herokuapp.com/"); //открыть урл
            logger.info("Login page was opened");// сообщение в логе о открытии страницы в браузере

        } catch (Exception e) {
            logger.error("Can not open Login Page" + e); //вывод в консоль и файл
            Assert.fail("Can not open Login Page" + e); //попадает в отчет и останавливает тест
        }
    }

    public void enterLoginIntoInputLogin(String login) {
        try {
            webDriver.findElement(By.xpath(".//input[@name='username' and @placeholder='Username']")).clear();
            webDriver.findElement(By.xpath(".//input[@name='username' and @placeholder='Username']")).sendKeys(login);
            logger.info(login + " was inputted into Input Login ");
        }catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    public void enterPassWordIntoInputPassWord (String passWord){
        try {
            webDriver.findElement(By.xpath(".//input[@placeholder='Password']")).clear();
            webDriver.findElement(By.xpath(".//input[@placeholder='Password']")).sendKeys(passWord);
            logger.info(passWord + " was inputted ");
        }catch (Exception e){
            printErrorAndStopTest(e);
        }
    }

    public void clickOnButtonSignIn(){
        try {
            webDriver.findElement(By.xpath(".//button[text()='Sign In']")).click();
            logger.info("Button Sing In was clicked");

        }catch (Exception e){
            printErrorAndStopTest(e);
        }

    }

    private void printErrorAndStopTest(Exception e) {
        logger.error("Can not work with element" + e);
        Assert.fail("Can not work with element" + e);
    }
}
