package baseTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.*;

import java.util.concurrent.TimeUnit;

public class BaseTest {
    WebDriver webDriver;
    Logger logger = Logger.getLogger(getClass());
    protected LoginPage loginPage;
    protected HomePage homePage;
    protected ProfilePage profilePage;
    protected CreatePostPage createPostPage;

    @Before
    public void setUp() {
        logger.info("-------"+testName.getMethodName()+" was started-----");
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
        logger.info("Browser has been opened");
        loginPage = new LoginPage(webDriver);
        homePage = new HomePage(webDriver);
        profilePage = new ProfilePage(webDriver);
        createPostPage = new CreatePostPage(webDriver);
    }

    @After
    public void tearDown() {
        webDriver.quit();
        logger.info("Browser has been closed");
        logger.info("--------"+testName.getMethodName()+" was ended---");
    }

    @Rule
    public TestName testName = new TestName();

}
