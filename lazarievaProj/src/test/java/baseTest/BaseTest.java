package baseTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.CreatePostPage;
import pages.HomePage;
import pages.LoginPage;

import java.util.concurrent.TimeUnit;

public class BaseTest {
    WebDriver webDriver;
    Logger logger = Logger.getLogger(getClass());
    //оголошуємо змінну loginPage/homepage
    protected LoginPage loginPage;
    protected HomePage homePage;
    protected CreatePostPage createPostPage;


    @Before
    public void setUp() {
        logger.info("---");
        logger.info("-------  " +testName.getMethodName()+ "  was started --------\n");
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
        logger.info("Browser was opened");
        //create new page object and will work with webDriver(chrome)
        loginPage = new LoginPage(webDriver);
        homePage = new HomePage(webDriver);
        createPostPage = new CreatePostPage(webDriver);

    }

    @After
    public void tearDown() {
        webDriver.close();
        logger.info("Browser was closed");
        logger.info("-------  " +testName.getMethodName()+ "  was finished --------\n");
    }

    @Rule
    public TestName testName = new TestName();

}
