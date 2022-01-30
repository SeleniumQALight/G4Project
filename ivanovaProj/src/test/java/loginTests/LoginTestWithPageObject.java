package loginTests;

import baseTest.BaseTest;
import org.junit.Assert;
import org.junit.Test;

public class LoginTestWithPageObject extends BaseTest {
    @Test
    public void validLoginTest(){
    loginPage.openLoginPage();
    loginPage.enterLoginIntoInputLogin("qaauto");
    loginPage.enterPasswordInputPassword("123456qwerty");
    loginPage.clickOnButtonSignIn();


        Assert.assertTrue("Button SignOut is not displayed"
        , homePage.isButtonSignOutDisplayed());


    }

    //HomeWork #2 invalid Login with PageObject

    @Test
    public void invalidLogin(){
        loginPage.openLoginPage();
        loginPage.enterLoginIntoInputLogin("qaautoy");
        loginPage.enterPasswordInputPassword("123456qwerty");
        loginPage.clickOnButtonSignIn();



        Assert.assertTrue("Invalid username / password"
                , loginPage.errorInvalidUsernamePasswordIsDisplayed());
    }



}
