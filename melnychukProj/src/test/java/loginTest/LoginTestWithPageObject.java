package loginTest;


import baseTest.BaseTest;
import libs.ExcelDriver;
import org.junit.Assert;
import org.junit.Test;
import pages.ParentPage;

import java.io.IOException;
import java.util.Map;

import static pages.ParentPage.configProperties;

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
    public void validLoginTestWithExcel() throws IOException {
        Map<String, String> dataForValidLogin = ExcelDriver.getData(configProperties.DATA_FILE(), "validLogOn");

        loginPage.openLoginPage();
        loginPage.enterLoginIntoInputLogin(dataForValidLogin.get("login"));
        loginPage.enterPasswordIntoInputPassword(dataForValidLogin.get("pass"));
        loginPage.clickOnButtonSignIn();

        Assert.assertTrue("Button signOut is displayed ", homePage.isButtonSignOutDisplayed());
    }
    @Test
    public void inValidLoginTest(){
        loginPage.openLoginPage();
        loginPage.enterLoginIntoInputLogin("qaauto");
        loginPage.enterPasswordIntoInputPassword("123456qwerty123");
        loginPage.clickOnButtonSignIn();

        Assert.assertTrue("Button SingIn is NOT displayed ", homePage.isButtonSignInDisplayed());
    }

}
