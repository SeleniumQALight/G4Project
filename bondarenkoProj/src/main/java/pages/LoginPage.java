package pages;


import io.qameta.allure.Step;
import libs.TestData;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.TextInput;

import java.util.ArrayList;
import java.util.List;

public class LoginPage extends ParentPage {
    @FindBy(xpath = ".//input[@name='username' and @placeholder='Username']")
    @Name("Input Login")
    private TextInput inputLoginSignIn;

    @FindBy(xpath = ".//input[@placeholder='Password']")
    private TextInput inputPassWordSignIn;

    @FindBy(xpath = ".//button[text()='Sign In']")
    private Button buttonSignIn;

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

    @FindBy(xpath = ".//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']")
    private List<WebElement> listErrors;

    private String listErrorsLocator = ".//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']";

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/";
    }
@Step
    public void openLoginPage() {
        try {
            webDriver.get(baseUrl + "/");
            logger.info("Login page was opened'" + baseUrl + "/");
        } catch (Exception e) {
            logger.error("Can not open Login Page" + e);
            Assert.fail("Can not open Login Page" + e);
        }

    }
    @Step
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

    @Step
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

    @Step
    public void clickOnButtonSignIn() {
//        try {
//            buttonSignIn.click();
//            logger.info("Button Sign In was clicked");
//        } catch (Exception e) {
//            printErrorAndStopTest(e);
//        }
        clickOnElement(buttonSignIn);
    }

    @Step
    public HomePage loginWithValidCred() {
        openLoginPage();
        enterLoginIntoInputLogin(TestData.VALID_LOGIN);
        enterPasswordIntoInputPassword(TestData.VALID_PASS);
        clickOnButtonSignIn();
        return new HomePage(webDriver);
    }

    @Step
    public void enterUsernameIntoInputUsernameSignUp(String username) {
        enterTextIntoElement(inputUsernameSignUp, username);
    }

    @Step
    public void enterEmailIntoInputEmailSignUp(String email) {
        enterTextIntoElement(inputEmailSignUp, email);
    }

    @Step
    public void enterPasswordIntoInputPasswordSignUp(String password) {
        enterTextIntoElement(inputPasswordSignUp, password);
    }

    @Step
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
    @Step
    public boolean isMessageForUsernameSignUpFieldDisplayed(){
        return isMessageForFieldDisplayed(messageForUsernameSignUpField);
    }
   public LoginPage checkIsMessageForUsernameSignUpFieldDisplayed(){
      Assert.assertTrue("Message for Username field is not displayed", isMessageForUsernameSignUpFieldDisplayed());
      return this;}
    @Step
    public boolean isMessageForEmailSignUpFieldDisplayed(){
        return isMessageForFieldDisplayed(messageForEmailSignUpField);
    }

    @Step
    public LoginPage checkIsMessageForEmailSignUpFieldDisplayed(){
      Assert.assertTrue("Message for Email field is not displayed", isMessageForEmailSignUpFieldDisplayed());
      return this;
   }

    @Step
    public boolean isMessageForPasswordSignUpFieldDisplayed(){
        return isMessageForFieldDisplayed(messageForPasswordSignUpFiled);
    }

    @Step
   public LoginPage checkIsMessageForPasswordSignUpFieldDisplayed(){
      Assert.assertTrue("Message for Password field is not displayed", isMessageForPasswordSignUpFieldDisplayed());
      return this;
    }

    @Step
    public LoginPage checkErrorsMessages(String expectedErrors) {
        String[] expectedErrorsArray = expectedErrors.split(";");
        webDriverWait10.withMessage("Numbers of messages").until(ExpectedConditions.numberOfElementsToBe(By.xpath(listErrorsLocator), expectedErrorsArray.length));
        Assert.assertEquals("", expectedErrorsArray.length, listErrors.size());
        ArrayList<String> actualTextFromErrors = new ArrayList<>();
        for (WebElement element: listErrors){
            actualTextFromErrors.add(element.getText());
        }
        SoftAssertions softAssertions = new SoftAssertions();

        for (int e = 0; e < expectedErrorsArray.length; e++) {
            softAssertions.assertThat(expectedErrorsArray[e]).isIn(actualTextFromErrors);
        }
        softAssertions.assertAll();
        return this;
    }
}




