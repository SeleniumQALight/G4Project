package loginTest;

import baseTest.BaseTest;
import org.junit.Assert;
import org.junit.Test;

public class LoginTestWithPageObject extends BaseTest {
    @Test
    public void validLogin(){
        loginPage.openLoginPage();
        loginPage.enterLoginIntoInputLogin("qaauto");
        loginPage.enterPasswordIntoInputPassword("123456qwerty");
        loginPage.clickOnSignIn();

        Assert.assertTrue("Button Sign Out went for wecetion", homePage.dispayedButtonSignOut());
    }
    @Test
    public void notValidLogin(){
        loginPage.openLoginPage();
        loginPage.enterLoginIntoInputLogin("abc");
        loginPage.enterPasswordIntoInputPassword("abcdefgh");
        loginPage.clickOnSignIn();

        Assert.assertTrue("Error text was not displayed", loginPage.displayedMessageError());
        Assert.assertTrue("Button 'Sign up for OurApp' not displayed", loginPage.buttonSingUpIsDisplayed());
    }
}
