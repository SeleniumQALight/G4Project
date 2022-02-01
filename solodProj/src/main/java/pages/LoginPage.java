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

public class LoginPage extends ParentPageWithHeader {
    // тут будут описаны все елементы
    @FindBy(xpath = ".//input[@name='username' and @placeholder='Username']")
// указываем FindBy далее xpath- елемент будет найден по этому xpath
    private WebElement inputLoginSingIn;// ВСЕГДА обьявление елемента, WebElement-описание + название "договорится как называть+ указание Типа"

    @FindBy(xpath = ".//input[@placeholder='Password']")
    private WebElement inputPasswordSignIn;

    @FindBy(xpath = ".//button[text()='Sign In']")
    private WebElement buttonSingIn;
/////////////////////////////////////////////HW3
    @FindBy(xpath = ".//button[@type='submit']")
    private WebElement buttonSignupForOurApp;

    @FindBy(xpath = ".//input[@id='username-register']")
    private  WebElement inputUserNameForOurApp;

    @FindBy(xpath = ".//input[@id='email-register']")
    private  WebElement inputEmailRegisterForOurApp;

    @FindBy(xpath = ".//input[@id='password-register']")
    private WebElement inputPasswordRegisterForOurApp;

    @FindBy(xpath = ".//*[contains(text(),'Username must be at least 3 characters.')]")
    private WebElement errorUserNameForOurApp;

    @FindBy(xpath = ".//*[contains(text(),'You must provide a valid email address.')]")
    private WebElement errorEmailForOurApp;

    @FindBy(xpath = ".//*[contains(text(),'Password must be at least 12 characters.')]")
    private WebElement errorPasswordForOurApp;

    @FindBy(xpath = ".//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']")
    private List<WebElement> listOfErrors;


    private String listErrorsLocator=".//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']";


    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/";
    }

    public void openLoginPage() {
        try {
            webDriver.get(baseUrl+"/");
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
        clickOnElement(buttonSingIn);
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





    private void printErrorAndStopTest(Exception e) {
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

    public LoginPage enterUserNameForOurApp(String username){
        enterTextInToElement(inputUserNameForOurApp,username);
        return this;
    }
    public LoginPage enterEmailForOurApp(String email){
        enterTextInToElement(inputEmailRegisterForOurApp,email);
        return this;
    }
    public LoginPage enterPasswordForOurApp(String password){
        enterTextInToElement(inputPasswordRegisterForOurApp,password);
        return this;
    }

    public void clickButtonSignupForOurApp(){
        clickOnElement(buttonSignupForOurApp);
    }

    public void enterInvalidDataForOurApp(){
        openLoginPage();
        enterUserNameForOurApp("tr");
        waitOneSecForValidationTextUsername();
        enterEmailForOurApp("test.com");
        enterPasswordForOurApp("123");
        clickButtonSignupForOurApp();


    }
    public boolean isValidationTextUsernameDisplayed(){
        return isElementDisplayed(errorUserNameForOurApp);
    }
    public boolean isValidationTextEmailDisplayed(){
        return  isElementDisplayed(errorEmailForOurApp);
    }
    public boolean isValidationTextPasswordDisplayed(){
        return isElementDisplayed(errorPasswordForOurApp);
    }
    protected void waitOneSecForValidationTextUsername(){
        //TODO wait chat
        try {
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }



    public LoginPage checkErrorsMessages(String expectedErrors) {
        String[] expectedErrorsArray = expectedErrors.split(";");
        webDriverWait10.withMessage(" Number of messages ").until(ExpectedConditions.numberOfElementsToBe
                (By.xpath(listErrorsLocator),expectedErrorsArray.length));
        ArrayList<String> actualTextFromErrors = new ArrayList<>();
        for (WebElement element: listOfErrors){
            actualTextFromErrors.add(element.getText());
        }
        SoftAssertions softAssertions=new SoftAssertions();
        for (int i = 0; i < expectedErrorsArray.length; i++) {
            softAssertions.assertThat(expectedErrorsArray[i]).isIn(actualTextFromErrors);
        }
        
        
        softAssertions.assertAll();
        return this;
    }
}





















