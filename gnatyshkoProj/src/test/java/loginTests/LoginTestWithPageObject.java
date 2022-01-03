package loginTests;

import baseTest.BaseTest;
import org.junit.Assert;
import org.junit.Test;

public class LoginTestWithPageObject extends BaseTest {
    @Test
    public void validLoginTest() {
        loginPage.openLoginPage();
        loginPage.enterLoginIntoInputLogin("qaauto");
        loginPage.enterLoginIntoInputPassword("123456qwerty");
        loginPage.clickOnButtonSignIn();

        Assert.assertTrue("Button SignOut is not displayed"
                , homePage.isButtonSignOutDisplayed());
    }

    @Test
    public void invalidLoginTest() {
        loginPage.openLoginPage();
        loginPage.enterLoginIntoInputLogin("qa");
        loginPage.enterLoginIntoInputPassword("123456qwerty");
        loginPage.clickOnButtonSignIn();

        Assert.assertFalse("Button SignOut is displayed"
                , homePage.isButtonSignOutDisplayed());

        Assert.assertTrue("Error message is not displayed"
                , loginPage.isErrorMessageDisplayed());
    }
}
