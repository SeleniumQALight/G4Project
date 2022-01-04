package LoginTest;

import BaseTest.BaseTest;
import org.junit.Assert;
import org.junit.Test;

public class LoginTestWithpageObject extends BaseTest {
    @Test
    public void validLogin(){
        loginPage.openLoginPage();
        loginPage.enterLoginIntoInputLogin("qaauto");
        loginPage.enterPassWordIntoInputPassWord("123456qwerty");
        loginPage.clickOnButtonSingIn();

        Assert.assertTrue("Button SignOut is not displayed"
                , homePage.isButtonSignOutDisplayed());
    }


}
