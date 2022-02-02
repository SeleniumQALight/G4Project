package pages;

import libs.TestData;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

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

    @FindBy(id = "username-register")
    private WebElement divInUsername;

    @FindBy(id = "email-register")
    private WebElement divInEmail;

    @FindBy(id = "password-register")
    private WebElement divInPassword;

    @FindBy(xpath = " .//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']") //работать со списками в локаторах
    private List<WebElement> listOfErrors;


    private String listErrorsLocator =" .//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']";

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

    public LoginPage enterLoginInputIntoUserNameRegiste(String login) {
        enterTextInToElement(inputUserNameRegiste, login);
        return this;
    }

    public LoginPage enterEmailIntoInputEmail(String email) {
        enterTextInToElement(inputEmailRegister, email);
        return this;
    }

    public LoginPage enterPassWordIntoInputPassWordRegister(String pasword) {
        enterTextInToElement(inputPassWordRegister, pasword);
        return this;
    }

    public LoginPage checkErrorMessages(String expectedErrors) { //сравнение списка ошибок
        String[] expectedErrorsArray = expectedErrors.split(";"); //split разделяет -- ожидаемый результат
        webDriverWait10
                .withMessage("Number of messages")
                .until(ExpectedConditions.numberOfElementsToBe(By.xpath(listErrorsLocator),expectedErrorsArray.length));//ожидание пока не придет к-во елементов указаных
        Assert.assertEquals("",expectedErrorsArray.length,listOfErrors.size());
        ArrayList<String> actualTextFromErrors = new ArrayList<>();
        for (WebElement element:listOfErrors) {actualTextFromErrors.add(element.getText());
        }
        SoftAssertions softAssertions = new SoftAssertions(); //срабатывает если явно указать
        for (int i = 0; i < expectedErrorsArray.length; i++) {
            softAssertions.assertThat(expectedErrorsArray[i]).isIn(actualTextFromErrors);
        }
        softAssertions.assertAll();

        return this;
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