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
        loginPage.clickOnButtonSingIn();

        Assert.assertTrue("Button Sign Out is not displayed"
                , homePage.isButtonSignOutDisplayed());
    }

    @Test
    public void notМalidLogintest(){
        loginPage.openLoginPage();
        loginPage.enterLoginIntoInputLogin("qaauto");
        loginPage.enterPassWordIntoInputPassWord("12345qwerty");
        loginPage.clickOnButtonSingIn();

        Assert.assertTrue("Error messages is not displayed"
                , loginPage.errorMessageMainPage());
    }

}
