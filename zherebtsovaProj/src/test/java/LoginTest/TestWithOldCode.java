package LoginTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class TestWithOldCode {
    WebDriver webDriver;

    @Test
    public void validLogin() {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);

        webDriver.get("https://qa-complex-app-for-testing.herokuapp.com/");
        System.out.println("Site was opened");
        webDriver.findElement(By.xpath(".//input[@name='username' and @placeholder='Username']")).clear();
        webDriver.findElement(By.xpath(".//input[@name='username' and @placeholder='Username']"))
                .sendKeys("qaauto");
        webDriver.findElement(By.xpath(".//input[@name='password' and @placeholder='Password']")).clear();
        webDriver.findElement(By.xpath(".//input[@name='password' and @placeholder='Password']"))
                .sendKeys("123456qwerty");
        System.out.println("Filled");
        webDriver.findElement(By.xpath("//button[text()='Sign In']")).click();
        System.out.println("Loggined");

        Assert.assertTrue("Button SignOut is not displayed", isButtonSignOutDisplayed());

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

    @Test
    public void validInLogin() {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);

        webDriver.get("https://qa-complex-app-for-testing.herokuapp.com/");
        System.out.println("Site was opened");
        webDriver.findElement(By.xpath(".//input[@name='username' and @placeholder='Username']")).clear();
        webDriver.findElement(By.xpath(".//input[@name='username' and @placeholder='Username']"))
                .sendKeys("qaauto1");
        webDriver.findElement(By.xpath(".//input[@name='password' and @placeholder='Password']")).clear();
        webDriver.findElement(By.xpath(".//input[@name='password' and @placeholder='Password']"))
                .sendKeys("123456qwerty1");
        System.out.println("Filled");
        webDriver.findElement(By.xpath("//button[text()='Sign In']")).click();
        System.out.println("UnLoggined");

        Assert.assertTrue("Error is displayed", isErrorDisplayed());

        webDriver.quit();
        System.out.println("Browser was closed");
    }

    private boolean isErrorDisplayed() {
        try {
            return webDriver.findElement(By.xpath("//div[@class='alert alert-danger text-center']")).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}


