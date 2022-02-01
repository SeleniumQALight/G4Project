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
    protected LoginPage loginPage;
    protected HomePage homePage;
    protected CreatePostPage createPostPage;
    @Before // запущена перед каждой аннотацией Тест
    public void setUp(){
        logger.info("----"+testName.getMethodName()+"was started---");
        WebDriverManager.chromedriver().setup(); // установка нужной версии
        webDriver= new ChromeDriver(); //реализация интерфейса вебрайвера
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
        logger.info("browser was open");
        loginPage = new LoginPage(webDriver);
        homePage = new HomePage(webDriver);
        createPostPage = new CreatePostPage(webDriver);


    }

    @After // запущена после каждой аннотацией Тест
    public void tearDown(){
        webDriver.quit();
        logger.info("browser was closed");
        logger.info("----");
    }
    @Rule
    public  TestName testName=new TestName();

}
