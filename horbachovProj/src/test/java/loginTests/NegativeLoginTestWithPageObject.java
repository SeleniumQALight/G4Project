package loginTests;

import baseTest.BaseTest;
import org.junit.Assert;
import org.junit.Test;

public class NegativeLoginTestWithPageObject extends BaseTest {
    @Test
    public void invalidLoginTest() {
        loginPage.openLoginPage();
        loginPage.enterLoginIntoInputLogin("qaauto1");
        loginPage.enterPasswordIntoInputPassword("123456qwerty");
        loginPage.clickOnButtonSignIn();

        Assert.assertFalse("Button SignOut is displayed", homePage.isButtonSignOutDisplayed());
    }
}
