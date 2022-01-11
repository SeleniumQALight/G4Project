package pages;

import libs.TestData;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends ParentPage {
    @FindBy(xpath = ".//input[@name='username' and @placeholder='Username']")
    private WebElement inputLoginSignIn;

    @FindBy(xpath = ".//input[@placeholder='Password']")
    private WebElement inputPassWordSignIn;

    @FindBy(xpath = ".//button[text()='Sign In']")
    private WebElement buttonSignIn;

    @FindBy(xpath = ".//*[@id='username-register']")
    private WebElement inputUsernameSignUp;

    @FindBy(xpath = ".//*[@id='email-register']")
    private WebElement inputEmailSignUp;

    @FindBy(xpath = ".//*[@id='password-register']")
    private WebElement inputPasswordSignUp;

    @FindBy(xpath = ".//*[@type='submit']")
    private WebElement buttonSignUpForOutApp;

    @FindBy(xpath = ".//div[text()='Username must be at least 3 characters.']")
    private WebElement messageForUsernameSignUpField;

    @FindBy(xpath = ".//div[text()='You must provide a valid email address.']")
    private WebElement messageForEmailSignUpField;

    @FindBy(xpath = ".//div[text()='Password must be at least 12 characters.']")
    private WebElement messageForPasswordSignUpFiled;

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
//        try{
//            inputLoginSignIn.clear();
//            inputLoginSignIn.sendKeys(login);
//            logger.info(login + " was inputted into Input Login");
//        }catch (Exception e){
//            printErrorAndStopTest(e);
//        }
        enterTextIntoElement(inputLoginSignIn, login);
    }

    public void enterPasswordIntoInputPassword(String passWord) {
//        try{
//            inputPassWordSignIn.clear();
//            inputPassWordSignIn.sendKeys(passWord);
//            logger.info(passWord + " was inputted");
//    }catch (Exception e){
//            printErrorAndStopTest(e);
//        }
        enterTextIntoElement(inputPassWordSignIn, passWord);
    }

    public void clickOnButtonSignIn() {
//        try {
//            buttonSignIn.click();
//            logger.info("Button Sign In was clicked");
//        } catch (Exception e) {
//            printErrorAndStopTest(e);
//        }
        clickOnElement(buttonSignIn);
    }

    public HomePage loginWithValidCred() {
        openLoginPage();
        enterLoginIntoInputLogin(TestData.VALID_LOGIN);
        enterPasswordIntoInputPassword(TestData.VALID_PASS);
        clickOnButtonSignIn();
        return new HomePage(webDriver);
    }

    public void enterUsernameIntoInputUsernameSignUp(String username) {
        enterTextIntoElement(inputUsernameSignUp, username);
    }

    public void enterEmailIntoInputEmailSignUp(String email) {
        enterTextIntoElement(inputEmailSignUp, email);

    }

    public void enterPasswordIntoInputPasswordSignUp(String password) {
        enterTextIntoElement(inputPasswordSignUp, password);
    }

    public void clickOnButtonSignUpForOurApp() {
        clickOnElement(buttonSignUpForOutApp);
    }

   // public boolean isMessageForUsernameSignUpFieldDisplayed(){
   //     try {
  //          return messageForUsernameSignUpField.isDisplayed();
  //      }catch (Exception e){
  //          return false;
  //      }
 //   }

 //   public LoginPage checkIsMessageForUsernameSignUpFieldDisplayed(){
 //       Assert.assertTrue("Message for Username field is not displayed", isMessageForUsernameSignUpFieldDisplayed());
 //       return this;
 //   }

 //   public boolean isMessageForEmailSignUpFieldDisplayed(){
 //       try {
  //          return messageForEmailSignUpField.isDisplayed();
  //      }catch (Exception e){
 //           return false;
 //       }
 //   }

 //   public LoginPage checkIsMessageForEmailSignUpFieldDisplayed(){
  //      Assert.assertTrue("Message for Email field is not displayed", isMessageForEmailSignUpFieldDisplayed());
 //       return this;
//    }

  //  public boolean isMessageForPasswordSignUpFieldDisplayed(){
 //       try {
 //           return messageForPasswordSignUpFiled.isDisplayed();
  //      }catch (Exception e){
  //          return false;
  //      }
 //   }

//    public LoginPage checkIsMessageForPasswordSignUpFieldDisplayed(){
 //       Assert.assertTrue("Message for Password field is not displayed", isMessageForPasswordSignUpFieldDisplayed());
//        return this;
//    }

    public boolean isMessageForUsernameSignUpFieldDisplayed(){
        return isMessageForFieldDisplayed(messageForUsernameSignUpField);
    }
   public LoginPage checkIsMessageForUsernameSignUpFieldDisplayed(){
      Assert.assertTrue("Message for Username field is not displayed", isMessageForUsernameSignUpFieldDisplayed());
      return this;}

    public boolean isMessageForEmailSignUpFieldDisplayed(){
        return isMessageForFieldDisplayed(messageForEmailSignUpField);
    }
    public LoginPage checkIsMessageForEmailSignUpFieldDisplayed(){
      Assert.assertTrue("Message for Email field is not displayed", isMessageForEmailSignUpFieldDisplayed());
      return this;
   }

    public boolean isMessageForPasswordSignUpFieldDisplayed(){
        return isMessageForFieldDisplayed(messageForPasswordSignUpFiled);
    }
   public LoginPage checkIsMessageForPasswordSignUpFieldDisplayed(){
      Assert.assertTrue("Message for Password field is not displayed", isMessageForPasswordSignUpFieldDisplayed());
      return this;
    }
}




