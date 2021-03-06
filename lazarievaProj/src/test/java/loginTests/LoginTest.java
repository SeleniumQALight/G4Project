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
        System.out.println(" Site was opened");

        webDriver.findElement(By.xpath(".//input[@name='username' and @placeholder='Username']"));
        webDriver.findElement(By.xpath(".//input[@name='username' and @placeholder='Username']")).sendKeys("qaauto");
        System.out.println("Login was entered");

        webDriver.findElement(By.xpath(".//input[@placeholder='Password']"));
        webDriver.findElement(By.xpath(".//input[@placeholder='Password']")).sendKeys("123456qwerty");
        System.out.println("Password was entered");

        webDriver.findElement(By.xpath(".//button[text()='Sign In']")).click();
        System.out.println("Button was clicked");

        Assert.assertTrue("Button Sign Out not displayed", isButtonSignOutDisplayed());

        webDriver.quit();
        System.out.println("Browser closed");

    }

    private boolean isButtonSignOutDisplayed() {
        try {
            return webDriver.findElement(By.xpath(".//button[text()='Sign Out']")).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }


    // HomeWork 2
    @Test
    public void invalidLogin() {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);

        webDriver.get("https://qa-complex-app-for-testing.herokuapp.com/");
        System.out.println(" Site was opened");

        webDriver.findElement(By.xpath(".//input[@name='username' and @placeholder='Username']"));
        webDriver.findElement(By.xpath(".//input[@name='username' and @placeholder='Username']")).sendKeys("testqaauto");
        System.out.println("Invalid Login was entered");

        webDriver.findElement(By.xpath(".//input[@placeholder='Password']"));
        webDriver.findElement(By.xpath(".//input[@placeholder='Password']")).sendKeys("7qwerty");
        System.out.println("Invalid Password was entered");

        webDriver.findElement(By.xpath(".//button[text()='Sign In']")).click();
        System.out.println("Button was clicked");
//      Verify if the 'Sign Out' button was NOT displayed & the field 'UserName' of the SignUP Form is displayed
        Assert.assertFalse("Button Sign Out displayed", isButtonSignOutDisplayed());
        System.out.println("Button Sign Out was NOT displayed");
        Assert.assertTrue("The field 'UserName' of the SignUP Form isn`t displayed",
                webDriver.findElement(By.xpath(".//input[@id='username-register']")).isDisplayed());
        System.out.println("The field 'UserName' of the SignUP Form is displayed");

        webDriver.quit();
        System.out.println("Browser closed");
    }





}

