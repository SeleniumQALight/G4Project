package loginTests;

import baseTest.BaseTest;
import libs.ExcelDriver;
import org.junit.Assert;
import org.junit.Test;


import java.io.IOException;
import java.util.Map;

import static pages.ParentPage.configProperties;

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
    public void validLoginTestWithExcel() throws IOException {
        Map<String,String> dataForValidLogin = ExcelDriver.getData(configProperties.DATA_FILE(),"validLogOn");
        loginPage.openLoginPage();
        loginPage.enterLoginIntoInputLogin(dataForValidLogin.get("login"));
        loginPage.enterPassWordIntoInputPassWord(dataForValidLogin.get("pass"));
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
