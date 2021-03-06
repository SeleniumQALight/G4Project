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
    public void validLogin() {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);

        webDriver.get("https://qa-complex-app-for-testing.herokuapp.com/");
        System.out.println("Site was open");

        webDriver.findElement(By.xpath(".//input[@name='username' and @placeholder='Username']")).clear();
        webDriver.findElement(By.xpath(".//input[@name='username' and @placeholder='Username']"))
                .sendKeys("qaauto");
        System.out.println("Login was entered");

        webDriver.findElement(By.xpath(".//input[@placeholder='Password']")).clear();
        webDriver.findElement(By.xpath(".//input[@placeholder='Password']"))
                .sendKeys("123456qwerty");
        System.out.println("Password was entered");

        webDriver.findElement(By.xpath(".//button[text()='Sign In']")).click();
        System.out.println("Button was clicked");

        Assert.assertTrue("Button Sign Out is not displayed"
                , isButtonSignOutDisplayed());

        webDriver.quit();
        System.out.println("Browser was closed");
    }

    @Test
    public void invalidLogin() {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);

        webDriver.get("https://qa-complex-app-for-testing.herokuapp.com/");
        System.out.println("Site was open");

        webDriver.findElement(By.xpath(".//input[@name='username' and @placeholder='Username']")).clear();
        webDriver.findElement(By.xpath(".//input[@name='username' and @placeholder='Username']"))
                .sendKeys("qa");
        System.out.println("Login was entered");

        webDriver.findElement(By.xpath(".//input[@placeholder='Password']")).clear();
        webDriver.findElement(By.xpath(".//input[@placeholder='Password']"))
                .sendKeys("123456qwerty");
        System.out.println("Password was entered");

        webDriver.findElement(By.xpath(".//button[text()='Sign In']")).click();
        System.out.println("Button was clicked");

        Assert.assertFalse("Button Sign Out is displayed"
                , isButtonSignOutDisplayed());

        Assert.assertTrue("Error message is not displayed"
                , isErrorMessageDisplayed());

        webDriver.quit();
        System.out.println("Browser was closed");
    }

    private boolean isButtonSignOutDisplayed() {
        try {
            return webDriver.findElement(By.xpath(".//button[text()='Sign Out']")).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isErrorMessageDisplayed() {
        try {
            return webDriver.findElement(By.xpath(".//div[@class='alert alert-danger text-center']")).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

}
