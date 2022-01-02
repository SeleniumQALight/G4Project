package loginTests;

import baseTest.BaseTest;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class LoginTestWithPageObject extends BaseTest {
    @Test
    public void validLoginTest() {
        loginPage.openLoginPage();
        loginPage.enterLoginIntoInputLogin("qaauto");
        loginPage.enterPassWordIntoInputPassWord("123456qwerty");
        loginPage.clickOnButtonSingIn();

        assertTrue("Button SignOut is not displayed", homePage.isButtonSignOutDisplayed());
    }
}
