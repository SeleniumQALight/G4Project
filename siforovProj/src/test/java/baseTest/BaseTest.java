package baseTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
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
        webDriver = initDriver();
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

    private WebDriver initDriver(){
        String browser = System.getProperty("browser");
        if((browser == null) || browser.equalsIgnoreCase("chrome")){
            WebDriverManager.chromedriver().setup();
            webDriver=  new ChromeDriver();
        }else if(browser.equalsIgnoreCase("firefox")){
            WebDriverManager.firefoxdriver().setup();
            webDriver = new FirefoxDriver();
        }else if(browser.equalsIgnoreCase("opera")){
            WebDriverManager.operadriver().setup();
            webDriver = new OperaDriver();
        }
       return webDriver;
    }

}
