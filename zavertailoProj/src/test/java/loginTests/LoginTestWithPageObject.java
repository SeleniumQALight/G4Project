package loginTests;

import baseTest.BaseTest;
import org.junit.Assert;
import org.junit.Test;

public class LoginTestWithPageObject extends BaseTest {
    @Test
    public void validLoginTest() {
        loginPage.openLoginPage();//обращение к логин пейдж и открытие ее
        loginPage.enterLoginIntoInputLogin("qaauto"); //alt + entr выбрать создать и переносит в логин
        loginPage.enterPassWordIntoInputPassWord("123456qwerty");
        loginPage.clickOnButtonSignIn();

        Assert.assertTrue("ButtonSignOut is not displayed", homePage.isButtonSignOutDisplayed());
    }
}