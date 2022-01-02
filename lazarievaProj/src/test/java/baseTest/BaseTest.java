package baseTest;

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
    //оголошуємо змінну loginPage/homepage
    protected LoginPage loginPage;
    protected HomePage homePage;

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
        logger.info("Browser was opened");
        //create new page object and wlii work with webDriver(chrome)
        loginPage = new LoginPage(webDriver);
        homePage = new HomePage(webDriver);

    }

    @After
    public void tearDown() {
        webDriver.close();
        logger.info("Browser was closed");
    }

}
