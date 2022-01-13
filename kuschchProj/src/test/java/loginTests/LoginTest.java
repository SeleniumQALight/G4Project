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

        webDriver.findElement(By.xpath(".//input[@name='username' and @placeholder='Username']")).clear();
        webDriver.findElement(By.xpath(".//input[@name='username' and @placeholder='Username']")).sendKeys("" +
                "qaauto");
        System.out.println("login was entered");

        webDriver.findElement(By.xpath(".//input[@placeholder='Password']")).clear();
        webDriver.findElement(By.xpath(".//input[@placeholder='Password']")).sendKeys("123456qwerty");
        System.out.println("pass was entered");

        webDriver.findElement(By.xpath(".//button[text()='Sign In']")).click();
        System.out.println("btn was clicked");

        //Assert.assertTrue("Button SignOut is not displayed", webDriver.findElement(By.xpath(".//button[text()='Sign Out']")).isDisplayed());
        Assert.assertTrue("Button signOut is not displayed", isButtonSignOutDisplayed() );

        webDriver.quit();
        System.out.println("browsser was closed");

        //--------------------------logOut----------------------------//

        webDriver.findElement(By.xpath(".//button[@Class = 'btn btn-sm btn-secondary']")).click();
        System.out.println("log Out");

        //--------------------------Negative test----------------------------//
        webDriver.findElement(By.xpath (".//input[@name='username' and @placeholder='Username']")).clear();
        webDriver.findElement(By.xpath (".//input[@name='username' and @placeholder='Username']")).sendKeys("incorrect");
        System.out.println("login incorrect");

        webDriver.findElement(By.xpath(".//input[@placeholder='Password']")).clear();
        webDriver.findElement(By.xpath(".//input[@placeholder='Password']")).sendKeys("incorrect");
        System.out.println("pass incorrect");

        webDriver.findElement(By.xpath(".//button[text()='Sign In']")).click();
        System.out.println("btn was clicked");

        Assert.assertTrue("\n" +
                "error message was not displayed", errorMessageMainPage());

        webDriver.quit();
        System.out.println("browsser was closed");

    }

    private boolean isButtonSignOutDisplayed(){
        try {
            return webDriver.findElement(By.xpath(".//button[text()='Sign Out']")).isDisplayed();
        }catch (Exception e) {
            return false;
        }
    }
    private boolean errorMessageMainPage(){
        try {
            return webDriver.findElement(By.xpath(".//div[@Class = 'alert alert-danger text-center']")).isDisplayed();
        }catch (Exception e){
            return false;
        }
    }

}