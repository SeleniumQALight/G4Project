package loginTests;

import baseTest.BaseTest;
import categories.SmokeTestFilter;
import libs.ExcelDriver;
import org.junit.Assert;
import org.junit.Ignore;
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
        loginPage.enterPassWordIntoInputPassword("123456qwerty");
        loginPage.clickOnButtonSingIn();

        Assert.assertTrue("Button SignOut is not displayed", homePage.isButtonSignOutDisplayed());
    }

    @Test
    @Ignore
    public void validLoginTestWithExcel() throws IOException {
        Map<String,String> dataForValidLogin = ExcelDriver.getData(configProperties.DATA_FILE()
                ,"validLogOn");
        loginPage.openLoginPage();
        loginPage.enterLoginIntoInputLogin(dataForValidLogin.get("login"));
        loginPage.enterPassWordIntoInputPassword(dataForValidLogin.get("pass"));
        loginPage.clickOnButtonSingIn();

        Assert.assertTrue("Button SignOut is not displayed", homePage.isButtonSignOutDisplayed());
    }

    @Test
    public void unValidLoginTest() {
        loginPage.openLoginPage();
        loginPage.enterLoginIntoInputLogin("qaaut");
        loginPage.enterPassWordIntoInputPassword("123456qwerty");
        loginPage.clickOnButtonSingIn();

        Assert.assertFalse("Button SignOut is displayed", homePage.isButtonSignOutDisplayed());
    }
}
