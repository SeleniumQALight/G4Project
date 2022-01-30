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

        homePage.checkIsButtonSignOutDisplayed();
    }

    @Test
    public void invalidLoginTest() {
        loginPage.openLoginPage();
        loginPage.enterLoginIntoInputLogin("qa");
        loginPage.enterLoginIntoInputPassword("123456qwerty");
        loginPage.clickOnButtonSignIn();
        loginPage.checkIsErrorMessageSignInDisplayed();
    }


    @Test
    public void invalidLoginTestSecondVersion() {
        loginPage.loginWithInvalidCredentials();
        loginPage.checkIsErrorMessageSignInDisplayed();
    }
}
