package BaseTest;
import LoginTest.CreatePostTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
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
        logger.info("____"+testName.getMethodName()+"was started____");
        webDriver = initDriver();
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
        logger.info("____"+testName.getMethodName()+"was ended____\n");
        logger.info("");
    }
    @Rule
    public TestName testName =new TestName();
    private WebDriver initDriver(){
        String browser = System.getProperty("browser");
        if((browser==null) || browser.equalsIgnoreCase("Chrome")) {
            WebDriverManager.chromedriver().setup();
            webDriver = new ChromeDriver();
        }else if (browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            webDriver = new FirefoxDriver();
        }else if ("ie".equalsIgnoreCase(browser)) {
            //WebDriverManager.iedriver().setup();
// in most cases 32bit version is needed
            WebDriverManager.iedriver().arch32().setup();
            return new InternetExplorerDriver();
        }
        return webDriver;

        }
    }
