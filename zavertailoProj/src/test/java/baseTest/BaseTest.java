package baseTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.HomePage;
import pages.LoginPage;
import pages.CreatePostPage;

import java.util.concurrent.TimeUnit;

public class BaseTest {
    WebDriver webDriver;
    Logger logger = Logger.getLogger(getClass()); //логирование
   protected LoginPage loginPage; //объявили переменную
    protected HomePage homePage;
    protected CreatePostPage createPostPage;
    @Before
    public  void setUp(){
        logger.info("-------- " + testName.getMethodName()+" was started -----------");
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
        logger.info("Browser was opened");
        loginPage = new LoginPage(webDriver);
        homePage = new HomePage(webDriver);
        createPostPage = new CreatePostPage(webDriver);

    }
    @After
    public void tearDown(){
        webDriver.quit();//закрыть браузер
        logger.info("Browser was closed");
        logger.info("--------- " + testName.getMethodName() + " was ended ---------" );

    }

    @Rule
    public TestName testName = new TestName();
}
