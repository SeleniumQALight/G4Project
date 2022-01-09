package LoginTest;

import baseTest.BaseTest;
import org.junit.Assert;
import org.junit.Test;

public class LoginTestWithPageObject extends BaseTest {
    @Test
    public void validLoginTest() {
        loginPage.openLoginPage();
        loginPage.enterLoginIntoInputLogin("qaauto");
        loginPage.enterPasswordIntoInputPassword("123456qwerty");
        loginPage.clickOnButton();

        Assert.assertTrue("Button Sign out is not displayed", homePage.isButtonSignOutDisplayed());
    }

    //Homework2
    @Test
    public void invalidLogInTest(){
        loginPage.openLoginPage();
        loginPage.enterLoginIntoInputLogin("QW");
        loginPage.enterPasswordIntoInputPassword("12");
        loginPage.clickOnButton();

        Assert.assertTrue("The Sign In button isn't displayed. User isn't on the Log in page", loginPage.signInButtonIsVisible());
    }

    //Homework1
    @Test
    public void invalidLogInWithEmptyDataTest(){
        loginPage.openLoginPage();
        loginPage.enterLoginIntoInputLogin("");
        loginPage.enterPasswordIntoInputPassword("");
        loginPage.clickOnButton();

        Assert.assertTrue("The Sign In button isn't displayed. User isn't on the Log in page", loginPage.signInButtonIsVisible());
    }
}
