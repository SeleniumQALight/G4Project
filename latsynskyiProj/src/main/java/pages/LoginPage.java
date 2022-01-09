package pages;
import libs.TestData;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends ParentPage{

    @FindBy(xpath = ".//input[@name='username' and @placeholder='Username']")
    private WebElement inputLoginSingIn;

    @FindBy(xpath = ".//input[@placeholder='Password']")
    private WebElement inputPassWordSingIn;

    @FindBy(xpath = ".//button[text()='Sign In']")
    private WebElement ButtonSingIn;

    @FindBy(xpath =".//div[text()='Invalid username / password']" )
    private WebElement Errormsg;


    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }
    public void openLoginPage() {
        try {
            webDriver.get("https://qa-complex-app-for-testing.herokuapp.com/");
            logger.info("Login page was opened");
        } catch (Exception e) {
            logger.error("Can not open Login Page"+e);
            Assert.fail("Can not open Login Page"+e);
        }
        }

    public void enterLoginIntoInputLogin(String login) {
//        try{
//            inputLoginSingIn.clear();
//            inputLoginSingIn.sendKeys(login);
//            logger.info(login+ "was inputted into input login");
//
//        }catch (Exception e){
//            printErrorAndStopTest(e);
//        }
        enterTextInToElement(inputLoginSingIn,login);
    }

    public void enterPassWordIntoInputPassWord(String passWord) {
//        try {
//            inputPassWordSingIn.clear();
//            inputPassWordSingIn.sendKeys(passWord);
//            logger.info(passWord + "was inputted");
//
//        } catch (Exception e) {
//            printErrorAndStopTest(e);
//        }
        enterTextInToElement(inputPassWordSingIn,passWord);
    }
    public void clickOnButtonSingIn(){
//        try{
//            ButtonSingIn.click();
//            logger.info("Button Sign in was clicked ");
//        }catch (Exception e){
//            printErrorAndStopTest(e);
//        }
        clickOnElement(ButtonSingIn);
    }


    private void printErrorAndStopTest(Exception e) {
        logger.error("Can not work with element"+e);
        Assert.fail("Can not work with element"+e);
    }
    public HomePage loginWithValidCred(){
        openLoginPage();
        enterLoginIntoInputLogin(TestData.VALID_LOGIN);
        enterPassWordIntoInputPassWord(TestData.VALID_PASS);
        clickOnButtonSingIn();
          return new HomePage(webDriver);
    }
    public boolean errormsgIsDisplayed (){
        try{
            return  Errormsg.isDisplayed();
        }catch (Exception e){
            return false;
        }
    }

}
