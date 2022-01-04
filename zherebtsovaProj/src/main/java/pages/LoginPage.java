package pages;
import Libs.TestData;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public  class LoginPage extends ParentPage {
    @FindBy(xpath = ".//input[@name='username' and @placeholder='Username']")
    private WebElement SingInLoginPage;
    @FindBy(xpath = ".//input[@placeholder='Password']")
    private WebElement InputPasswordSingIn;
    @FindBy(xpath = ".//button[text()='Sign In']\"")
    private WebElement ButtonSubmit;

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void openLoginPage() {
        try {
            webDriver.get("https://qa-complex-app-for-testing.herokuapp.com/");
            logger.info("Login page was opened");
        } catch (Exception e) {
            logger.error("Can not open Login Page" + e);
            Assert.fail("Can not open Login Page" + e);
        }
    }

    public void enterLoginIntoInputLogin(String login) {
            EnterTextInToEveryElement(SingInLoginPage, login);
        }

    public void enterPassWordIntoInputPassWord(String passWord) {
        EnterTextInToEveryElement(InputPasswordSingIn, passWord);
    }

    public void clickOnButtonSingIn(){
        clickOnElement(ButtonSubmit);
    }
    public HomePage LoginWithValidCredits(){
        openLoginPage();
        enterLoginIntoInputLogin(TestData.VALID_LOGIN);
        enterPassWordIntoInputPassWord(TestData.VALID_PASSWORD);
        clickOnButtonSingIn();
        return new HomePage(WebDriver webDriver);

    }


    private void printErrorAndStopTest(Exception e) {
        logger.error("Can not work with element" + e);
        Assert.fail("Can not work with element" + e);
    }
}
