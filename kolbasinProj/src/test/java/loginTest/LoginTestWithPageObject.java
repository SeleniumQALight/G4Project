package loginTest;

import baseTest.BaseTest;
import org.junit.Assert;
import org.junit.Test;

public class LoginTestWithPageObject extends BaseTest {
    @Test
    public void validLoginTest(){
    loginPage.openLoginPage();
    loginPage.enterLoginIntoInputLogin("qaauto");
    loginPage.enterPassWordIntoInputPassWord("123456qwerty");
    loginPage.clickOnButtonSignIn();

    Assert.assertTrue("Button SingOut is not displayed"  // отработает только тогда, когда появится кнопка
            , homePage.isButtonSignOutDisplayed());
    }

}
