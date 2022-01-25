package loginTestsHW;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class InvalidLoginTest {
    WebDriver webDriver; // created variable for web driver
    @Test
    public void inValidLogIn(){
        WebDriverManager.chromedriver().setup(); // add possibility to work with chrom
        webDriver = new ChromeDriver(); // write needed browser
        webDriver.manage().window().maximize(); // open browser in a full view
        webDriver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS); // wait till all components will be downloaded

        webDriver.get("https://qa-complex-app-for-testing.herokuapp.com/"); // open website
        System.out.println("Site was opened");

        webDriver.findElement(By.xpath(".//input[@name='username' and @placeholder = 'Username']")).clear();
        webDriver.findElement(By.xpath(".//input[@name='username' and @placeholder = 'Username']"))
                .sendKeys("qa");
        System.out.println("Invalid login was entered");

        webDriver.findElement(By.xpath(".//input[@placeholder='Password']")).clear();
        webDriver.findElement(By.xpath(".//input[@placeholder='Password']")).sendKeys("123456qwerty");
        System.out.println("Valid Password was entered");

        webDriver.findElement(By.xpath(".//button[text()]='Sign In'")).click(); // like mouseclick not enter
        System.out.println("Button was clicked");

        //Check that with invalid password not possible to sign in

        //Assert checks test

        Assert.assertTrue("Sign in was possible", // this text will be in reports
               isButtonSignInDisplayed());



        webDriver.quit(); // Close browser (webdriver.close() - close tab
        System.out.println("Browser was closed");


    }
    private boolean isButtonSignInDisplayed(){
        try{
            return webDriver.findElement(By.xpath(".//button[text()]='Sign In'")).isDisplayed();

        }catch (Exception e){
            return false;
        }
    }
}
