package loginTests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class LoginTests {
    WebDriver webDriver;
    @Test
public void validLogin(){
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
            System.out.println("button was clicked ");
            Assert.assertTrue("button SignOut is not didplayed"
                    ,isButtonSignOutDisplayed());


        webDriver.quit();
        System.out.println("browser was closed");
    }

    @Test
    public void inValidLogin (){
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(7,TimeUnit.SECONDS);
        webDriver.get("https://qa-complex-app-for-testing.herokuapp.com/");
        System.out.println("Site was opened");

        webDriver.findElement(By.xpath(".//input[@name='username' and @placeholder='Username']")).clear();
        webDriver.findElement(By.xpath(".//input[@name='username' and @placeholder='Username']")).sendKeys("qa-auto");
        System.out.println("login was entered");

        webDriver.findElement(By.xpath(".//input[@placeholder='Password']")).clear();
        webDriver.findElement(By.xpath(".//input[@placeholder='Password']")).sendKeys("123456qwerty");
        System.out.println("password was entered");

        webDriver.findElement(By.xpath(".//button[text()='Sign In']")).click();
        System.out.println("button was clicked ");

        Assert.assertTrue("Negative test failed"
                , isErrorMsgDisplayed());
        System.out.println("Negative test passed");


        webDriver.quit();
        System.out.println("browser was closed");



    }
    private boolean isButtonSignOutDisplayed(){
            try{
                    return  webDriver.findElement(By.xpath(".//button[text()='Sign Out']")).isDisplayed();
            }catch (Exception e){
                    return false;
            }


            }

            private boolean isErrorMsgDisplayed(){
        try{
            return  webDriver.findElement(By.xpath(".//div[text()='Invalid username / password']")).isDisplayed();
        }catch (Exception e){
            return false;
        }
            }
}


