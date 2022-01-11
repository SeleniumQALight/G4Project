package loginTests;

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

        Assert.assertTrue("Button SignOut is not displayed"
                , homePage.isButtonSignOutDisplayed());
    }
@Test
    public void inValidLoginTest () {
    loginPage.openLoginPage();
    loginPage.enterLoginIntoInputLogin("qa-auto");
    loginPage.enterPassWordIntoInputPassWord("123456qwerty");
    loginPage.clickOnButtonSingIn();

    Assert.assertTrue("Error message is not displaed Ntest failed"
            ,loginPage.errormsgIsDisplayed());

}
}
