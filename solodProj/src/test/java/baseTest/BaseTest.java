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
import pages.CreatePostPage;
import pages.HomePage;
import pages.LoginPage;

import java.util.concurrent.TimeUnit;

public class BaseTest {
    WebDriver webDriver;
    Logger logger = Logger.getLogger(getClass());//сюда будет передаватся красивый логи
    protected LoginPage loginPage;
    protected HomePage homePage;
    protected CreatePostPage createPostPage;

    @Before //будет запущена перед каждой аннотацией
    public void setUp() {
        logger.info("--- "+testName.getMethodName()+" was started ---");
        webDriver=initDriver();
//        WebDriverManager.chromedriver().setup();
//        webDriver = new ChromeDriver();//реализация интерфейса
//        WebDriverManager.firefoxdriver().setup();
//        webDriver = new FirefoxDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
        logger.info("Browser was opened");
        loginPage=new LoginPage(webDriver);
        homePage=new HomePage(webDriver);
        createPostPage=new CreatePostPage(webDriver);
    }

    @After//будет запущена после каждой аннотацией
    public void tearDown() {
        webDriver.quit();
        logger.info("Browser was closed");
        logger.info("----- "+testName.getMethodName()+" was ended---- \n");



    }
    @Rule
    public TestName testName=new TestName();

    private WebDriver initDriver(){
        String browser = System.getProperty("browser");
        if ((browser==null)||browser.equalsIgnoreCase("chrome")){
            WebDriverManager.chromedriver().setup();
            webDriver = new ChromeDriver();
        }else if (browser.equalsIgnoreCase("firefox")){
            WebDriverManager.firefoxdriver().setup();
            webDriver = new FirefoxDriver();
        }
        return  webDriver;
    }

}
