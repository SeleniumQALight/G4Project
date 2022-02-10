package loginTests;

import baseTest.BaseTest;
import categories.SmokeTestFilter;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class LoginTestWithPageObject extends BaseTest {

    @Test
    @Category(SmokeTestFilter.class)
    public void validLogin() {
        loginPage.openLoginPage();
        loginPage.enterLoginIntoInputLogin("qaauto");
        loginPage.enterPasswordIntoInputPassword("123456qwerty");
        loginPage.clickOnButtonSignIn();

        Assert.assertTrue("Button SignOut is not displayed",
                homePage.isButtonSignOutDisplayed());

    }

    @Test
    public void invalidLogin() {
        loginPage.openLoginPage();
        loginPage.enterLoginIntoInputLogin("test555");
        loginPage.enterPasswordIntoInputPassword("12qwerty");
        loginPage.clickOnButtonSignIn();

        Assert.assertFalse("Button SignOut is not displayed",
                homePage.isButtonSignOutDisplayed());
        Assert.assertTrue("The the SignUP Button isn`t displayed",
                loginPage.verifyIfTheSignUPButtonIsDisplayed());


    }


}
