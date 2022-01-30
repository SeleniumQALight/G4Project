package baseTest;//родительский тест, кондишины будуь выполняться для всех тестов ,которые наследуются от BaseTest

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.HomePage;
import pages.LoginPage;

import java.util.concurrent.TimeUnit;

public class BaseTest {
    WebDriver webDriver;
    Logger logger = Logger.getLogger(getClass());
    protected LoginPage loginPage;
    protected HomePage homePage;
    @Before // будет запущена после каждой анатации Тест/ = PreCondition
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();// будем работать с Chrome
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
        logger.info("Browser was opened");
        loginPage =new LoginPage(webDriver);
        homePage =new HomePage(webDriver);

    }
    @After // будет запущена после каждого анатации Test/PostCondition
    public void tearDown() {
        webDriver.quit();
        logger.info("Browser was closed");
    }
}
