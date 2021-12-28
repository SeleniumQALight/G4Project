package loginTests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class LoginTest<webDriver> {
    WebDriver webDriver;
    @Test
    public void validLogin(){
        WebDriverManager.chromedriver().setup();// соответствие драйвера с хромом
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();// азвернуть на весь экран
        webDriver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS); //установить выполнение команды с таймером до 7 секунд каждые 0,5 секунд. Есл по истечению нет элемента тогда ошибка
        webDriver.get("https://qa-complex-app-for-testing.herokuapp.com/"); //обращение к урлу
        System.out.println("Site was opened");

        webDriver.findElement(By.xpath(".//input[@name='username' and @placeholder='Username']"));
        webDriver.findElement(By.xpath(".//input[@name='username' and @placeholder='Username']")).sendKeys("qaauto");
        System.out.println("login was entered");

        webDriver.findElement(By.xpath(".//input[@placeholder='Password']"));
        webDriver.findElement(By.xpath(".//input[@placeholder='Password']")).sendKeys("123456qwerty");
        System.out.println("password was entered");

        webDriver.findElement(By.xpath(".//button[text()='Sign In']")).click();
        System.out.println("click in Sign In");

        Assert.assertTrue("Button SignOut is not displayed", webDriver.findElement(By.xpath(".//button[text()='Sign Out']")).isDisplayed());// если нет кнопли будет ошибка



        webDriver.quit(); //закрыть браузер
        System.out.println("browser was closed");

    }

    private boolean isButtonSignOutDisplayed(){
        try{
            return  webDriver.findElement(By.xpath(".//button[text()='Sign Out']")).isDisplayed();
        }catch (Exception e){
            return false;
        }
    }

}
