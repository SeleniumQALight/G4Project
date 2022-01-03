package loginTests;

import baseTest.BaseTest;
import org.junit.Assert;
import org.junit.Test;

public class LoginTestWithPageObject extends BaseTest {
    @Test
    public void validLoginTest(){
        loginPage.openLoginPage();
        loginPage.enterLoginIntoInputLogin("qaauto");
        loginPage.enterPassWordIntoInputPassWord("123456qwerty");
        loginPage.clickOnButtonSignIn();

        Assert.assertTrue("Button SignOut is not displayed"
                , homePage.isButtonSignOutDisplayed());

    }

    @Test
    public void inValidLoginTest(){
        loginPage.openLoginPage();
        loginPage.enterLoginIntoInputLogin("qaautoX");
        loginPage.enterPassWordIntoInputPassWord("123456qwertyX");
        loginPage.clickOnButtonSignIn();

        Assert.assertTrue("Button SignIn is not displayed"
                , loginPage.isButtonSignInDisplayed());
        Assert.assertTrue("Error Alert is not displayed"
                , loginPage.isErrorAlertDisplayed());

    }

}
