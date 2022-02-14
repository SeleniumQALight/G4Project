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
        WebDriverManager.chromedriver().setup();//запустить тесты в Хроме
        webDriver = new ChromeDriver();//объявить браузер
        webDriver.manage().window().maximize();//развернуть на весь экран
        webDriver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);//Ожидание отклика страницы - упадет, если не найдет элемент

        webDriver.get("https://qa-complex-app-for-testing.herokuapp.com/");//Открывает сайт в браузере
        System.out.println("Site was opened");

        webDriver.findElement(By.xpath(".//input[@name='username' and @placeholder='Username']")).clear();//очистить поле
        webDriver.findElement(By.xpath(".//input[@name='username' and @placeholder='Username']"))
                .sendKeys("qaauto");// ввод логина
        System.out.println("Login was entered");

        webDriver.findElement(By.xpath(".//input[@placeholder='Password']")).clear();
        webDriver.findElement(By.xpath(".//input[@placeholder='Password']"))
                .sendKeys("123456qwerty");//ввод пароля
        System.out.println("password was entered");

        webDriver.findElement(By.xpath(".//button[text()='Sign In']")).click();//клик по кнопке
        System.out.println("Button was clicked");

        Assert.assertTrue("Button Sign OUT is not displayed"
                , isButtonSignOutDisplayed());

        webDriver.quit();//закрыть браузер
        System.out.println("Site was closed");

    }

    //Home work #2 - написать тест на невалидный Login
    @Test
    public void invalidLogin() {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);

        webDriver.get("https://qa-complex-app-for-testing.herokuapp.com/");//Открывает сайт в браузере
        System.out.println("Site was opened");

        webDriver.findElement(By.xpath(".//input[@name='username' and @placeholder='Username']")).clear();//очистить поле
        webDriver.findElement(By.xpath(".//input[@name='username' and @placeholder='Username']"))
                .sendKeys("qaauto2");// ввод логина -wrong password
        System.out.println("Login was entered");

        webDriver.findElement(By.xpath(".//input[@placeholder='Password']")).clear();
        webDriver.findElement(By.xpath(".//input[@placeholder='Password']"))
                .sendKeys("123456qwerty");//ввод пароля
        System.out.println("password was entered");

        webDriver.findElement(By.xpath(".//button[text()='Sign In']")).click();//клик по кнопке
        System.out.println("Button was clicked");

        Assert.assertTrue("Invalid Username"
                , errorInvalidUsernamePasswordIsDisplayed());

        webDriver.quit();//закрыть браузер
        System.out.println("Site was closed");


    }

    private boolean isButtonSignOutDisplayed() { //метод,который проверяет отображается ли кнопка
        try {
            return webDriver.findElement(By.xpath(".//button[text()='Sign Out']")).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    private boolean errorInvalidUsernamePasswordIsDisplayed() {
        try {
            return webDriver.findElement(By.xpath(".//div[text()='Invalid username / password']")).isDisplayed();

        } catch (Exception e) {
            return false;
        }
    }
}

