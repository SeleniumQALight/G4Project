package BaseTest;
import LoginTest.CreatePostTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.CreatePostPage;
import pages.HomePage;
import pages.LoginPage;

import java.util.concurrent.TimeUnit;

public class BaseTest {
    WebDriver webDriver;
    Logger logger = Logger.getLogger(getClass());
    protected LoginPage loginPage;
    protected HomePage homePage;
    protected CreatePostPage createPostPage;

    @Before
    public void SetUp() {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
        logger.info ("Browser is opened");
        loginPage = new LoginPage(webDriver);
        homePage = new HomePage(webDriver);
        createPostPage = new CreatePostPage(webDriver);
    }
    @After
    public void tearDown() {
        webDriver.quit();
        logger.info ("Browser is closed");
    }
}