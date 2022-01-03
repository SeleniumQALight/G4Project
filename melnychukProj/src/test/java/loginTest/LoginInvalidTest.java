package loginTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class LoginInvalidTest {
    WebDriver webDriver;
    @Test
    public void invValidLogin(){
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);

        webDriver.get("https://qa-complex-app-for-testing.herokuapp.com/");
        System.out.println("Site was open");

        webDriver.findElement(By.xpath(".//input[@name='username' and @placeholder='Username']")).clear();
        webDriver.findElement(By.xpath(".//input[@name='username' and @placeholder='Username']")).sendKeys("qaauto");
        System.out.println("Login is entered");

        webDriver.findElement(By.xpath(".//input[@type= 'password' and @placeholder='Password']")).clear();
        webDriver.findElement(By.xpath(".//input[@placeholder='Password']")).sendKeys("123456qwerty12341");
        System.out.println("Password is entered");

        webDriver.findElement(By.xpath(".//button[text()='Sign In']")).click();
        System.out.println("Button was clicked");

        Assert.assertTrue("Button Sign In displayed", isButtonSignInDisplayed());



        webDriver.quit();
        System.out.println("browser was closed");

    }
    private boolean isButtonSignInDisplayed(){
        try {
            return webDriver.findElement(By.xpath(".//button[text()='Sign In']")).isDisplayed();
        }catch (Exception e){
            return false;
        }
    }
}
