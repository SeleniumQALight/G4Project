package loginTest;

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
    public void validLogIn (){
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);

        webDriver.get("https://qa-complex-app-for-testing.herokuapp.com/");
        System.out.println("Site was opened");

        webDriver.findElement(By.xpath(".//input[@name='username' and @placeholder='Username']")).clear();
        webDriver.findElement(By.xpath(".//input[@name='username' and @placeholder='Username']")).sendKeys("qaauto");
        System.out.println("login was entered");

        webDriver.findElement(By.xpath(".//input[@placeholder='Password']")).clear();
        webDriver.findElement(By.xpath(".//input[@placeholder='Password']")).sendKeys("123456qwerty");
        System.out.println("password was entered");

        webDriver.findElement(By.xpath(".//button[text()='Sign In']")).click();
        System.out.println("Button was cliked");

        Assert.assertTrue("Button SingOut is not displayed", isButtonSingOutDisplayed() );

        webDriver.quit();
        System.out.println("browser was closed");

    }
    private boolean isButtonSingOutDisplayed(){
        try {
            return webDriver.findElement(By.xpath(".//button[text()='Sign Out']")).isDisplayed();
        }catch (Exception e){
            return false;
        }
    }

    @Test
    public void incorrectLogin (){
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);

        webDriver.get("https://qa-complex-app-for-testing.herokuapp.com/");
        System.out.println("Site was opened");

        webDriver.findElement(By.xpath(".//input[@name='username' and @placeholder='Username']")).clear();
        webDriver.findElement(By.xpath(".//input[@name='username' and @placeholder='Username']")).sendKeys("auto");
        System.out.println("login was entered");

        webDriver.findElement(By.xpath(".//input[@placeholder='Password']")).clear();
        webDriver.findElement(By.xpath(".//input[@placeholder='Password']")).sendKeys("123456qwerty");
        System.out.println("password was entered");

        webDriver.findElement(By.xpath(".//button[text()='Sign In']")).click();
        System.out.println("Button was cliked");

        Assert.assertTrue("Message Invalid username / password isn't displayed ", isMessageErrorDisplayed());

        webDriver.quit();
        System.out.println("browser was closed");
    }
    private boolean isMessageErrorDisplayed() {
        try {
            return webDriver.findElement(By.xpath(".//div [@class = 'alert alert-danger text-center']")).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
