package loginTest;

import baseTest.BaseTest;
import org.junit.Assert;
import org.junit.Test;

public class LoginTestWithPageObject extends BaseTest {

    @Test
    public void validLoginTest() {
        loginPage.openLoginPage();
        loginPage.enterLoginIntoInputLogin("qaauto");
        loginPage.enterPassWordIntoInputPassWord("123456qwerty");
        loginPage.clickOnButtonSingIn();

        Assert.assertTrue("Button SingOut is not displayed", homePage.isButtonSingOutDisplayed());
    }

    @Test
    public void incorrectLoginTest() {
        loginPage.openLoginPage();
        loginPage.enterLoginIntoInputLogin("456123");
        loginPage.enterPassWordIntoInputPassWord("123456qwerty");
        loginPage.clickOnButtonSingIn();

        Assert.assertTrue("Button SingIn is not displayed", loginPage.isButtonSingInDisplayed());

    }
}
