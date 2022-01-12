package LoginTest;

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
    public void validLogIn() {
        WebDriverManager.chromedriver().setup();//хотим запустить тести в хроме - запуск тестов в хроме
        webDriver = new ChromeDriver();// запустить в хроме
        webDriver.manage().window().maximize();//вебдрайвер у тебя есть менеджер , окно, окно на весь экран
        // установление дефолтного ожилания
        webDriver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);// 7 секунд будет ожидания после открытия окна
        // основыные натсройки
        //открывает УРЛ
        webDriver.get("https://qa-complex-app-for-testing.herokuapp.com/");//открой то что мы вставили в ""
        System.out.println("Site was opened");

        webDriver.findElement(By.xpath(".//input[@name='username' and @placeholder='Username']")).clear();//ВЕБДРАЙВЕР найди мне елемент по Хпаз, но сначало почисти поле ЮсерИмя
        webDriver.findElement(By.xpath(".//input[@name='username' and @placeholder='Username']")).sendKeys("qaauto");//введи в логин то что мы ему сказали
        System.out.println("Login was entered");

        webDriver.findElement(By.xpath(".//input[@placeholder='Password']")).clear();//ВЕБДРАЙВЕР найди мне елемент по Хпаз, но сначало почисти поле Пароль
        webDriver.findElement(By.xpath(".//input[@placeholder='Password']")).sendKeys("123456qwerty");//введи в пароль то что мы ему сказали
        System.out.println("password was entered ");

        webDriver.findElement(By.xpath(".//button[text()='Sign In']")).click();//найди елемент Вход и нажми на него
        System.out.println("Button was clicked");

        //делаем проверку после входа на кнопку Выход ,видим ли мы ее ! Вынесли проверку в отдельный мотод НИЖЕ (isButtonSingOutDisplayed)!!!
        Assert.assertTrue("Button SignOut is not displayed", isButtonSingOutDisplayed()); //сообщение покажеться если тест провалился
        //проверяет выполнилась ли проверка которая проходит ,
        // если не выполнилась остановить тест ругатся

        //закрой браузер
        webDriver.quit();
        System.out.println("browser was closed");
    }

    @Test
    public void unValidLogin() {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);

        webDriver.get("https://qa-complex-app-for-testing.herokuapp.com/");
        System.out.println("Site was opened");

        webDriver.findElement(By.xpath(".//input[@name='username' and @placeholder='Username']")).clear();
        webDriver.findElement(By.xpath(".//input[@name='username' and @placeholder='Username']")).sendKeys("qaautoO");
        System.out.println("Login was opened");

        webDriver.findElement(By.xpath(".//input[@placeholder='Password']")).clear();
        webDriver.findElement(By.xpath(".//input[@placeholder='Password']")).sendKeys("123456qwerty");
        System.out.println("password was entered");

        webDriver.findElement(By.xpath(".//button[text()='Sign In']")).click();
        System.out.println("Button was clicked");

        Assert.assertFalse("Button SignOut is not displayed",isButtonSingOutDisplayed());
        Assert.assertTrue("Error Field is not Displayed", isErrorFieldDisplayed());


        webDriver.quit();
        System.out.println("browsed was closed");


    }

    private boolean isButtonSingOutDisplayed() {
        try {
            return webDriver.findElement(By.xpath(".//button[text()='Sign Out']")).isDisplayed();
        } catch (Exception e) {
            return false;
        }

    }
    private boolean isErrorFieldDisplayed() {
        try {
            return webDriver.findElement(By.xpath(".//div[@class='alert alert-danger text-center']")).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}

















