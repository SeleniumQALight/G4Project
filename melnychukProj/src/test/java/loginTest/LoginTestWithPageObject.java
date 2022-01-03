package loginTest;


import baseTest.BaseTest;
import org.junit.Assert;
import org.junit.Test;

public class LoginTestWithPageObject extends BaseTest {
    @Test
    public void validLoginTest(){
        loginPage.openLoginPage();
        loginPage.enterLoginIntoInputLogin("qaauto");
        loginPage.enterPasswordIntoInputPassword("123456qwerty");
        loginPage.clickOnButtonSignIn();

        Assert.assertTrue("Button signOut is displayed ", homePage.isButtonSignOutDisplayed());
    }
    @Test
    public void inValidLoginTest(){
        loginPage.openLoginPage();
        loginPage.enterLoginIntoInputLogin("qaauto");
        loginPage.enterPasswordIntoInputPassword("123456qwerty123");
        loginPage.clickOnButtonSignIn();

        Assert.assertTrue("Button signOut is NOT displayed, signIn displayed ", homePage.isButtonSignInDisplayed());

    }
}
