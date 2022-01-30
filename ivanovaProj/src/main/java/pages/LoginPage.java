package pages;

import libs.TestData;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

//Находятся все действия со страницей LoginPage
public class LoginPage extends ParentPage {
    //выносим элементы страницы для предотвращения дублирования
    @FindBy(xpath =".//input[@name='username' and @placeholder='Username']")
    private WebElement inputLoginSignIn;

    @FindBy(xpath = ".//input[@placeholder='Password']")
    private  WebElement inputPasswordSignIn;

    @FindBy(xpath = ".//button[text()='Sign In']")
    private WebElement buttonSignIn;

    @FindBy(xpath = ".//div[text()='Invalid username / password']")
    private WebElement displayMessageAboutInvalidLogin;


    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void openLoginPage() {
        try {
            webDriver.get("https://qa-complex-app-for-testing.herokuapp.com/");//Открывает сайт в браузере
            logger.info("Login page was opened");
        } catch (Exception e) { // обрабатывает ситуацию, когда страница не открывается
            logger.error("Can not open Login Page" + e);//выводится в консоль и в лог
            Assert.fail("Can not open Login Page" + e);// выводится в отчет
        }
    }

    public void enterLoginIntoInputLogin(String login) {
//        try {
//        inputLoginSignIn.clear();//очистить поле
//        inputLoginSignIn.sendKeys(login);
//        logger.info(login + " was input into Login");
//        } catch (Exception e){
//            printErrorandStopTest(e);
//        }
        enterTextInToElement(inputLoginSignIn,login);
    }

    public void enterPasswordInputPassword(String password){
//        try{
//           inputPasswordSignIn.clear();
//           inputPasswordSignIn.sendKeys(password);
//            logger.info(password + " was input");
//
//        } catch (Exception e){
//            printErrorandStopTest(e);
//        }
        enterTextInToElement(inputPasswordSignIn,password);
    }

    public void clickOnButtonSignIn(){
//        try {
//            buttonSignIn.click();
//            logger.info("Button Sign in was ckicked");
//        } catch (Exception e ){
//            printErrorandStopTest(e);
//        }
        clickOnElement(buttonSignIn);
    }


    private void printErrorandStopTest(Exception e) {
        logger.error("Can not work with element" + e);
        Assert.fail("Can not work with element"+ e);
    }

    public boolean errorInvalidUsernamePasswordIsDisplayed() {
        try {
            return displayMessageAboutInvalidLogin.isDisplayed();

        } catch (Exception e) {
            return false;
        }
    }

    public HomePage loginWithValidCred(){
        openLoginPage();
        enterLoginIntoInputLogin(TestData.VALID_USERNAME);
        enterPasswordInputPassword(TestData.VALID_PASS);
        clickOnButtonSignIn();
        return new HomePage(webDriver);
    }
}
