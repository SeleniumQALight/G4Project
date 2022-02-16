package loginTests;

import baseTest.BaseTest;
import categories.SmokeTestFilter;
import libs.ExcelDriver;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.io.IOException;
import java.util.Map;

import static pages.ParentPage.configProperties;

public class LoginTestWithPageObject extends BaseTest {
    @Test
    @Category(SmokeTestFilter.class)
    public void validLoginTest() {
        loginPage.openLoginPage();
        loginPage.enterLoginIntoInputLogin("qaauto");
        loginPage.enterPasswordIntoInputPassword("123456qwerty");
        loginPage.clickOnButtonSignIn();

        Assert.assertTrue("Button SignOut is not displayed", homePage.isButtonSignOutDisplayed());

    }

    @Test
    public void validLoginTestWithExcel() throws IOException {
        Map<String, String> dataForValidLogin = ExcelDriver.getData(configProperties.DATA_FILE(), "validLogOn");
        loginPage.openLoginPage();
        loginPage.enterLoginIntoInputLogin(dataForValidLogin.get("login"));
        loginPage.enterPasswordIntoInputPassword(dataForValidLogin.get("pass"));
        loginPage.clickOnButtonSignIn();

        Assert.assertTrue("Button SignOut is not displayed", homePage.isButtonSignOutDisplayed());


    }

    @Test
    public void inValidLoginTest() {
        loginPage.openLoginPage();
        loginPage.enterLoginIntoInputLogin("qaaut");
        loginPage.enterPasswordIntoInputPassword("12345");
        loginPage.clickOnButtonSignIn();

        Assert.assertFalse("Button SignOut is not displayed", homePage.isButtonSignOutDisplayed());

    }
}
