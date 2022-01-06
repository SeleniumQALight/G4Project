package loginTests;

import baseTest.BaseTest;
import org.junit.Assert;
import org.junit.Test;

public class LoginTestWithPageObject extends BaseTest {
    @Test
    public void validLoginTest(){
        loginPage.openLoginPage();
        loginPage.enterLoginIntoInputLogin("qaauto");
        loginPage.enterPassWordIntoPassWord("123456qwerty");
        loginPage.clickOnButtonSignIn();

        Assert.assertTrue("Button Sign Out is not displayed", homePage.isButtonSignOutDisplayed());
    }

    @Test
    public void unValidLoginTest(){
        loginPage.openLoginPage();
        loginPage.enterLoginIntoInputLogin("qaaut");
        loginPage.enterPassWordIntoPassWord("123456qwert");
        loginPage.clickOnButtonSignIn();

        Assert.assertTrue("Button Sign Out is not displayed", homePage.isButtonSignInDisplayed());
    }

}
