package pages;

import libs.TestData;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends ParentPage {
    //postoyanno ispolyzuemie elementi
    @FindBy(xpath = ".//input[@name='username' and @placeholder='Username']")
    private WebElement inputLoginSingIn;

    @FindBy(xpath = ".//input[@type= 'password' and @placeholder='Password']")
    private WebElement inputPasswordSingIn;

    @FindBy(xpath = ".//button[text()='Sign In']")
    private WebElement clickButtonSingIn;


    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void openLoginPage(){
        try {
            driver.get("https://qa-complex-app-for-testing.herokuapp.com/");
            logger.info("Login page was opened");
        }catch (Exception e){
            logger.error("Can't open login page" + e);
            Assert.fail("Can't open login page" + e);
        }
    }

    public void enterLoginIntoInputLogin(String login) {
//        try {
//            inputLoginSingIn.clear();
//            inputLoginSingIn.sendKeys(login);
//            logger.info(login + " was entered into login field");
//        }catch (Exception e){
//            printErrorAndStopTest(e);
//        }
        enterTextIntoElement(inputLoginSingIn, login);
    }
    public void enterPasswordIntoInputPassword(String pass){
//        try {
//            inputPasswordSingIn.clear();
//            inputPasswordSingIn.sendKeys(pass);
//            logger.info((pass + " was entered into password field"));
//        }catch (Exception e){
//            printErrorAndStopTest(e);
//        }
        enterTextIntoElement(inputPasswordSingIn,pass);
    }

    public void clickOnSignIn(){
//        try {
//            clickButtonSingIn.click();
//            logger.info("Button Sign In was clicked");
//        }catch (Exception e){
//            printErrorAndStopTest(e);
//        }
        clickOnElement(clickButtonSingIn);
    }

    public HomePage logedInHomepage(){
        openLoginPage();
        enterLoginIntoInputLogin(TestData.VALID_LOGIN);
        enterPasswordIntoInputPassword(TestData.VALID_PASS);
        clickOnSignIn();
        return new  HomePage(driver);
    }

    public boolean displayedMessageError(){
        try {
            return driver.findElement(By.xpath(".//div[@class='alert alert-danger text-center']")).getText().equalsIgnoreCase("error");

        }catch (Exception e){
            printErrorAndStopTest(e);
            return false;
        }
    }

    public boolean buttonSingUpIsDisplayed(){
        try {
            return driver.findElement(By.xpath(".//button[@type='submit']")).isDisplayed();
        }catch (Exception e){
            printErrorAndStopTest(e);
            return false;
        }
    }

    private void printErrorAndStopTest(Exception e) {
        logger.error("Can't work with element" + e);
        Assert.fail("Can't work with element" + e);
    }
}
