package loginTest;

import baseTest.BaseTest;
import categories.SmokeTestFilter;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class LoginTestWithPageObject extends BaseTest {
    @Test
    @Category(SmokeTestFilter.class)
    public void validLoginTest(){
    loginPage.openLoginPage();
    loginPage.enterLoginIntoInputLogin("qaauto");
    loginPage.enterPassWordIntoInputPassWord("123456qwerty");
    loginPage.clickOnButtonSignIn();

    Assert.assertTrue("Button SingOut is not displayed"  // отработает только тогда, когда появится кнопка
            , homePage.isButtonSignOutDisplayed());
    }
}
