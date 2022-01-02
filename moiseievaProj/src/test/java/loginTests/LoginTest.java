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
    public void validLogin(){
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);

        webDriver.get("https://qa-complex-app-for-testing.herokuapp.com/");
        System.out.println("Site was opened");

        webDriver.findElement(By.xpath("//header//input[@name='username']")).clear();
        webDriver.findElement(By.xpath("//header//input[@name='username']")).sendKeys("qaauto");
        System.out.println("Login was entered");
        webDriver.findElement(By.xpath("//header//input[@name='password']")).clear();
        webDriver.findElement(By.xpath("//header//input[@name='password']")).sendKeys("123456qwerty");
        System.out.println("Pass was entered");
        webDriver.findElement(By.xpath("//header//button")).click();
        System.out.println("Button was clicked");
        Assert.assertTrue("Button SignOut is not displayed",isButtonSignOutDisplayed());

        webDriver.quit();
        System.out.println("Browser was closed");

    }

    @Test
    public void invalidLogin(){
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);

        webDriver.get("https://qa-complex-app-for-testing.herokuapp.com/");
        System.out.println("Site was opened");

        webDriver.findElement(By.xpath("//header//input[@name='username']")).clear();
        webDriver.findElement(By.xpath("//header//input[@name='username']")).sendKeys("qa-auto");
        System.out.println("Login was entered");
        webDriver.findElement(By.xpath("//header//input[@name='password']")).clear();
        webDriver.findElement(By.xpath("//header//input[@name='password']")).sendKeys("123456qwerty");
        System.out.println("Pass was entered");
        webDriver.findElement(By.xpath("//header//button")).click();
        System.out.println("Button was clicked");
        Assert.assertEquals("Error", getTextFromElement());
        Assert.assertTrue("Button Sign In is displayed", isButtonSignInDisplayed());

        webDriver.quit();
        System.out.println("Browser was closed");
    }

    private boolean isButtonSignOutDisplayed(){
        try {
            return webDriver.findElement(By.xpath("//button[text()='Sign Out']")).isDisplayed();
        }catch (Exception e){
            return false;
        }
    }
    private boolean isButtonSignInDisplayed(){
        try {
            return webDriver.findElement(By.xpath("//header//button")).isDisplayed();
        }catch (Exception e){
            return false;
        }
    }
    private String getTextFromElement(){
        try {
            return webDriver.findElement(By.xpath("//div[@class='alert alert-danger text-center']")).getText();
        }catch (Exception e){
            return "Text was not found";
        }
    }

}
