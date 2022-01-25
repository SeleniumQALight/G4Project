package pages;

import libs.TestData;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends ParentPageWithHeader {
    // тут будут описаны все елементы
    @FindBy(xpath = ".//input[@name='username' and @placeholder='Username']")
// указываем FindBy далее xpath- елемент будет найден по этому xpath
    private WebElement inputLoginSingIn;// ВСЕГДА обьявление елемента, WebElement-описание + название "договорится как называть+ указание Типа"

    @FindBy(xpath = ".//input[@placeholder='Password']")
    private WebElement inputPasswordSignIn;

    @FindBy(xpath = ".//button[text()='Sign In']")
    private WebElement buttonSingIn;


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
//        try {
//            //обьявили Елемент выше, и уже не дублируем код , а обращаемся к этому елементу !
//            inputLoginSingIn.clear();
//            inputLoginSingIn.sendKeys(login);
//            logger.info(login+" was inputted into Input Login");
//        }catch (Exception e){
//            printErrorAndStopTest(e);
//        }
        enterTextInToElement(inputLoginSingIn, login);
    }

    public void enterPassWordIntoInputPassWord(String passWord) {
//        try {
//            inputPasswordSignIn.clear();
//            inputPasswordSignIn.sendKeys(passWord);
//            logger.info(passWord + " was inputted ");
//        } catch (Exception e) {
//            printErrorAndStopTest(e);
//        }
        enterTextInToElement(inputPasswordSignIn, passWord);
    }

    public void clickOnButtonSignIn() {
//        try {
//            buttonSingIn.click();
//            logger.info("Button Sing In was clicked");
//
//        } catch (Exception e) {
//            printErrorAndStopTest(e);
//        }
        clickElement(buttonSingIn);
    }

//    private void printErrorAndStopTest(Exception e) {
//        logger.error("CanT work with element" + e);
//        Assert.fail("CanT work with element" + e);
//    }


    public HomePage loginWithValidCred(){
        openLoginPage();
        enterLoginIntoInputLogin(TestData.VALID_LOGIN);
        enterPassWordIntoInputPassWord(TestData.VALID_PASS);
        clickOnButtonSignIn();
    return new HomePage(webDriver);
    }





    public void printErrorAndStopTest(Exception e) {
        logger.error("CanT work with element"+ e);
        Assert.fail("CanT work with element"+ e);
    }
    public boolean isErrorFieldDisplayed(){
        try {
            return webDriver.findElement(By.xpath(".//div[@class='alert alert-danger text-center']")).isDisplayed();
        }catch (Exception e){
            return false;
        }
    }

}





















