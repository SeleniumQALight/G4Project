package loginTests;

import baseTest.BaseTest;
import org.junit.Assert;
import org.junit.Test;

public class LoginTestWithPageObject extends BaseTest {
    @Test
    public void validLoginTest() {
        loginPage.openLoginPage();
        loginPage.enterLoginIntoInputLogin("qaauto");
        loginPage.enterPassWordIntoInputPassword("123456qwerty");
        loginPage.clickOnButtonSingIn();

        Assert.assertTrue("Button SignOut is displayed", homePage.isButtonSignOutDisplayed());
    }

}
