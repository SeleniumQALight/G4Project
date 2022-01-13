package LoginTest;

import baseTest.BaseTest;
import org.junit.Assert;
import org.junit.Test;

public class LoginTestWithPageObject extends BaseTest {
    @Test
    public void validLoginTest() {
        loginPage.openLoginPage();
        loginPage.enterLoginIntoInputLogin("qaauto");
        loginPage.enterPassWordIntoInputPassWord("123456qwerty");
        loginPage.clickOnButtonSignIn();

        Assert.assertTrue("Button SignOut is not displayed", homePage.isButtonSingOutDisplayed());
    }


    @Test
    public void unValidLoginTest(){
        loginPage.openLoginPage();
        loginPage.enterLoginIntoInputLogin("qaautoO");
        loginPage.enterPassWordIntoInputPassWord("123456qwertyY");
        loginPage.clickOnButtonSignIn();

        Assert.assertFalse("Button SignOut is not displayed", homePage.isButtonSingOutDisplayed());
        Assert.assertTrue("Error Field is not Displayed", loginPage.isErrorFieldDisplayed());

    }
}
