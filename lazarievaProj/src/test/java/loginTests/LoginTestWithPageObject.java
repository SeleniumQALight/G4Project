package loginTests;

import baseTest.BaseTest;
import org.junit.Assert;
import org.junit.Test;

public class LoginTestWithPageObject extends BaseTest {

 @Test
    public  void validLogin(){
     loginPage.openLoginPage();
     loginPage.enterLoginIntoInputLogin("qaauto");
     loginPage.enterLoginIntoInputPassword("123456qwerty");
     loginPage.clickOnButtonSignIn();

     Assert.assertTrue("Button SignOut is not displayed",
             homePage.isButtonSignOutDisplayed());

 }

 @Test
 public  void invalidLogin(){
  loginPage.openLoginPage();
  loginPage.enterLoginIntoInputLogin("test555");
  loginPage.enterLoginIntoInputPassword("12qwerty");
  loginPage.clickOnButtonSignIn();

  Assert.assertFalse("Button SignOut is not displayed",
          homePage.isButtonSignOutDisplayed());
  Assert.assertTrue("The the SignUP Button isn`t displayed",
          loginPage.verifyIfTheSignUPButtonIsDisplayed());


 }


}
