package pages;

import libs.TestData;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends ParentPage { //alt + entr создать конструктор
    @FindBy(xpath = ".//input[@name='username' and @placeholder='Username']")
    private WebElement inputLoginSingIn; //объект с вебелементом (объект с адрессом с которым можем выполнять указаные действия - почислить , нажать, провеить наличие)

    @FindBy(xpath = ".//input[@placeholder='Password']")
    private WebElement inputPassWordSingIn;

    @FindBy(xpath = ".//button[text()='Sign In']")
    private WebElement buttonSingIn;

    @FindBy(xpath = ".//input[@id='username-register']")
    private WebElement inputUserNameRegiste;

    @FindBy(xpath = ".//input[@id ='email-register']")
    private WebElement inputEmailRegister;

    @FindBy(xpath = ".//input[@id='password-register']")
    private WebElement inputPassWordRegister;

    @FindBy(xpath = ".//button[@type ='submit']")
    private WebElement ButtonSignUpForOurApp;

    @FindBy(xpath = ".//div[contains(text(), 'Username must be at least 3 characters.')]")
    private WebElement divInUsername;

    @FindBy(xpath = ".//div[contains(text(), 'You must provide a valid email address.')]")
    private WebElement divInEmail;

    @FindBy(xpath = ".//div[contains(text(), 'Password must be at least 12 characters.')]")
    private WebElement divInPassword;

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/";
    }

    public void openLoginPage() {
        try {
            webDriver.get(baseUrl + "/"); //открыть урл
            logger.info("Login page was opened");// сообщение в логе о открытии страницы в браузере

        } catch (Exception e) {
            logger.error("Can not open Login Page" + e); //вывод в консоль и файл
            Assert.fail("Can not open Login Page" + e); //попадает в отчет и останавливает тест
        }
    }

    public void enterLoginIntoInputLogin(String login) {
//        try {
//            inputLoginSingIn.clear();
//            inputLoginSingIn.sendKeys(login);
//            logger.info(login + " was inputted into Input Login ");
//        }catch (Exception e) {
//            printErrorAndStopTest(e);
//        }
        enterTextInToElement(inputLoginSingIn, login);
    }

    public void enterPassWordIntoInputPassWord(String passWord) {
//        try {
//            inputPassWordSingIn.clear();
//            inputPassWordSingIn.sendKeys(passWord);
//            logger.info(passWord + " was inputted ");
//        }catch (Exception e){
//            printErrorAndStopTest(e);
//        }
        enterTextInToElement(inputPassWordSingIn, passWord);
    }

    public void clickOnButtonSignIn() {
//        try {
//            buttonSingIn.click();
//            logger.info("Button Sing In was clicked");
//
//        }catch (Exception e){
//            printErrorAndStopTest(e);
//        }
        clickOnElement(buttonSingIn);

    }

    public void enterLoginInputIntoUserNameRegiste(String login) {
        enterTextInToElement(inputUserNameRegiste, login);
    }

    public void enterEmailIntoInputEmail(String email) {
        enterTextInToElement(inputEmailRegister, email);
    }

    public void enterPassWordIntoInputPassWordRegister(String pasword) {
        enterTextInToElement(inputPassWordRegister, pasword);
    }

    public void clickOnButtonSignUpForOurApp() {
        clickOnElement(ButtonSignUpForOurApp);

    }

//    private void printErrorAndStopTest(Exception e) {
//        logger.error("Can not work with element" + e);
//        Assert.fail("Can not work with element" + e);
//   }


    public HomePage loginWithValidCred() {
        openLoginPage();
        enterLoginIntoInputLogin(TestData.VALID_LOGIN);
        enterPassWordIntoInputPassWord(TestData.VALID_PASS);
        clickOnButtonSignIn();
        return new HomePage(webDriver);
    }

    public boolean IsButtonSignUpForOurAppDisplayed() {
        try {
            return webDriver.findElement(By.xpath(".//button[@type='submit']")).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean IsButtonErrorForOurAppDisplayed() {
        try {
            return webDriver.findElement(By.xpath(".//*[text()='Error']")).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

//    public boolean isDivTextErrorLoginDisplayed(){
//        try{
//            return  webDriver.findElement(By.xpath(".//div[contains(text(), 'Username must be at least 3 characters.')]")).isDisplayed();
//        }catch (Exception e){
//            return false;
//       }
//    }

    public boolean isDivTextErrorLoginDisplayed() {
        return isElementDispleid(divInUsername);
    }


    public boolean isDivTextErrorEmailDisplayed() {
        return isElementDispleid(divInEmail);
    }

    public boolean isDivTextErrorPasswordDisplayed() {
        return  isElementDispleid(divInPassword);
    }

}