package loginTests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class LoginTest {
    WebDriver webDriver;
    @Test
    public void validLogIn(){
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);

        webDriver.get("https://qa-complex-app-for-testing.herokuapp.com/");
        System.out.println("Site was opened");

        webDriver.findElement(By.xpath(".//input[@name='username' and @placeholder='Username']")).clear();
        webDriver.findElement(By.xpath(".//input[@name='username' and @placeholder='Username']"))
                .sendKeys("qaauto");
        System.out.println("login was entered");
        webDriver.findElement(By.xpath(".//input[@name='password' and @placeholder='Password']")).clear();
        webDriver.findElement(By.xpath(".//input[@name='password' and @placeholder='Password']"))
                .sendKeys("123456qwerty");
        System.out.println("password was entered");
        webDriver.findElement(By.xpath(".//button[text()='Sign In']")).click();
        System.out.println("Button was clicked");
        Assert.assertTrue("Button Sign Out is not displayed"
                , isButtonSignOutDisplayed());

        webDriver.quit();
        System.out.println("browser was closed");
    }


    @Test
    public void inValidLogIn(){
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);

        webDriver.get("https://qa-complex-app-for-testing.herokuapp.com/");
        System.out.println("Site was opened");

        webDriver.findElement(By.xpath(".//input[@name='username' and @placeholder='Username']")).clear();
        webDriver.findElement(By.xpath(".//input[@name='username' and @placeholder='Username']"))
                .sendKeys("qaautoX");
        System.out.println("login was entered");
        webDriver.findElement(By.xpath(".//input[@name='password' and @placeholder='Password']")).clear();
        webDriver.findElement(By.xpath(".//input[@name='password' and @placeholder='Password']"))
                .sendKeys("123456qwertyX");
        System.out.println("password was entered");
        webDriver.findElement(By.xpath(".//button[text()='Sign In']")).click();
        System.out.println("Button was clicked");
        Assert.assertFalse("Button Sign Out is displayed"
                , isButtonSignOutDisplayed());
        System.out.println("Button Sign Out is checked");
        Assert.assertTrue("Error Alert is not displayed"
                , isErrorAlertDisplayed());
        System.out.println("Error Alert is checked");

        webDriver.quit();
        System.out.println("browser was closed");
    }


    private boolean isButtonSignOutDisplayed(){
        try {
            return webDriver.findElement(By.xpath(".//button[text()='Sign Out']")).isDisplayed();
        }catch (Exception e){
            return false;
        }
    }

    private boolean isErrorAlertDisplayed(){
        try {
            return webDriver.findElement(By.xpath(".//div[@class='alert alert-danger text-center']")).isDisplayed();
        }catch (Exception e){
            return false;
        }
    }
}
