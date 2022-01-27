package pages;

import libs.TestData;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends ParentPage{
    @FindBy(xpath = ".//input[@name='username' and @placeholder='Username']")  // объявление элемента
    private WebElement inputLoginSingIn;  //обязательно указывать тип элемента, название

    @FindBy(xpath = ".//input[@placeholder='Password']")
    private WebElement inputPassWordSingIn;

    @FindBy(xpath = ".//button[text()='Sign In']")
    private WebElement buttonSingIn;


    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/";
    }

    public void openLoginPage(){
        try{
            webDriver.get(baseUrl + "/");
            logger.info("Login page was opened");
        }catch (Exception e){
            logger.error("Can not open Login Page" + e);
            Assert.fail("Can not open Login Page" + e);
        }
    }

    public void enterLoginIntoInputLogin(String login) {
//        try{                                           // вынесли в ParentPage
//            inputLoginSingIn.clear();
//            inputLoginSingIn.sendKeys(login);
//            logger.info(login + " was inputted into Input Login");
//
//        }catch (Exception e){
//            printErrorAndStopTest(e);
//        }
        enterTextInToElement(inputLoginSingIn, login);
    }

    public void enterPassWordIntoInputPassWord(String passWord){
//        try {                                           // вынесли в ParentPage
//            inputPassWord.clear();
//            inputPassWord.sendKeys(passWord);
//            logger.info(passWord + " was inputted into Input PassWord");
//        }catch (Exception e){
//            printErrorAndStopTest(e);
//        }
        enterTextInToElement(inputPassWordSingIn, passWord);
    }

    public void clickOnButtonSignIn(){
//        try {                                           // вынесли в ParentPage
//            buttonSingIn.click();
//            logger.info("Button Sing In was clicked");
//        }catch (Exception e){
//            printErrorAndStopTest(e);
//        }
        clickOnElement(buttonSingIn);
    }

    public HomePage loginWithValidCred(){   // переход на HomePage из логина
        openLoginPage();
        enterLoginIntoInputLogin(TestData.VALID_LOGIN);
        enterPassWordIntoInputPassWord(TestData.VALID_PASS);
        clickOnButtonSignIn();
        return new HomePage(webDriver);
    }

}
