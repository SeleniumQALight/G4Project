package pajes;

import libs.TestData;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends ParentPage {
    @FindBy(xpath = ".//input[@name='username' and @placeholder='Username']")
    private WebElement inputLoginSingIn;

    @FindBy(xpath = ".//input[@placeholder='Password']")
    private WebElement inputPasswordSingIn;

    @FindBy(xpath = ".//button[text()='Sign In']")
    private WebElement buttonSingIn;

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void openLoginPage() {
        try {
            webDriver.get("https://qa-complex-app-for-testing.herokuapp.com/");
            logger.info("Login page was open");

        } catch (Exception e) {
            logger.error("Can not open Login Page" + e);
            Assert.fail("Can not open Login Page" + e);
        }
    }
    public void enterLoginIntoInputLogin(String login) {
//        try {
//            inputLoginSingIn.clear();
//            inputLoginSingIn.sendKeys(login);
//            logger.info(login+"was inputted into Input Login");
//        }catch(Exception e){
//            printErrorAndStopTest(e);
//        }
        enterTextInToElement(inputLoginSingIn, login);
    }
     public  void enterPassWordIntoInputPassWord (String passWord){
//        try{
//            inputPasswordSingIn.clear();
//            inputPasswordSingIn.sendKeys(passWord);
//
//        }catch (Exception e){
//            printErrorAndStopTest(e);
//        }
         enterTextInToElement(inputPasswordSingIn, passWord);
     }

     public void clickOnButtonSingIn(){
//        try {
//            buttonSingIn.click();
//            logger.info("Button Sing In was clicked");
//        }catch(Exception e){
//            printErrorAndStopTest(e);
//        }
         clickOnElement(buttonSingIn);
     }

//    private void printErrorAndStopTest(Exception e) {
//        logger.error("Can not work with element" + e);
//        Assert.fail("Can not work with element"+ e);
//
//    }
    public HomePage loginWithValidCred(){
        openLoginPage();
        enterLoginIntoInputLogin(TestData.VALID_LOGIN);
        enterPassWordIntoInputPassWord(TestData.VALID_PASS);
        clickOnButtonSingIn();
        return new HomePage(webDriver);
    }
}

