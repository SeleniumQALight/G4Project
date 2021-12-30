package loginTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class LoginTest {
    WebDriver driver;

    @Test
    public void validLogIn(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);

        driver.get("https://qa-complex-app-for-testing.herokuapp.com/");
        System.out.println("Site was opened");

        driver.findElement(By.xpath(".//input[@name='username' and @placeholder='Username']")).clear();
        driver.findElement(By.xpath(".//input[@name='username' and @placeholder='Username']")).sendKeys("qaauto");
        System.out.println("Login is entered");

        driver.findElement(By.xpath(".//input[@type= 'password' and @placeholder='Password']")).clear();
        driver.findElement(By.xpath(".//input[@placeholder='Password']")).sendKeys("123456qwerty");
        System.out.println("Password is entered");

        driver.findElement(By.xpath(".//button[text()='Sign In']")).click();
        System.out.println("Button was clicked");

        Assert.assertTrue("Button Sign Out not displayed", dispayedButtonSignOut());

        driver.findElement(By.xpath(".//button[text()='Sign Out']")).click();
        Assert.assertTrue("Button 'Sign up for OurApp' not displayed",buttonSignUpDisplayed());
        System.out.println("Log out and enter for main page");


        driver.findElement(By.xpath(".//input[@name='username' and @placeholder='Username']")).clear();
        driver.findElement(By.xpath(".//input[@name='username' and @placeholder='Username']")).sendKeys("abcdefg");
        System.out.println("Login was entered");

        driver.findElement(By.xpath(".//input[@placeholder='Password']")).clear();
        driver.findElement(By.xpath(".//input[@placeholder='Password']")).sendKeys("123456qwert");
        System.out.println("Password was entered");

        driver.findElement(By.xpath(".//button[text()='Sign In']")).click();
        System.out.println("Sign in button was clicked");

        Assert.assertTrue("Button 'Sign up for OurApp' not displayed",buttonSignUpDisplayed());
        System.out.println("Button 'Sign up for OurApp' is displayed");
        Assert.assertTrue("Wrong text is displayed",textErrorIsDisplayed());
        System.out.println("Text is right");

        driver.quit();
        System.out.println("Browser closed");

    }

    private boolean dispayedButtonSignOut(){
        try {
            return driver.findElement(By.xpath(".//button[text()='Sign Out']")).isDisplayed();
        }catch (Exception e){
            return false;
        }
    }

    //proverka vidimosti knopki registracii
    private boolean buttonSignUpDisplayed(){
        try {
            return driver.findElement(By.xpath(".//button[@type='submit']")).isDisplayed();
        }catch (Exception e){
            return false;
        }
    }
    //proverka vivedennogo texta posle ne korrektnogo logina
    private boolean textErrorIsDisplayed(){
        try {
            return driver.findElement(By.xpath(".//div[@class='alert alert-danger text-center']")).getText().equalsIgnoreCase("error");
        }catch (Exception e){
            return false;
        }
    }
}
