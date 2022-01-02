package loginTests;

import baseTest.BaseTest;
import org.junit.Assert;
import org.junit.Test;

public class LoginTestWithPageObject extends BaseTest {

    @Test
    public void validLoginTest(){
        loginPage.openLoginPage();
        loginPage.enterLoginIntoInputLogin("qaauto");
        loginPage.enterPasswordIntoInputLogin("123456qwerty");
        loginPage.clickOnButtonSignIn();
        Assert.assertTrue("Button Sign Out is not displayed", homePage.isButtonSignOutDisplayed());
    }


}