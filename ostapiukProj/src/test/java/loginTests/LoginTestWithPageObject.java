package loginTests;

import baseTest.BaseTest;
import categories.SmokeTestFilter;
import libs.ExcelDriver;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;
//import pages.ParentPage;

import java.io.IOException;
import java.util.Map;

import static pages.ParentPage.configProperties;

public class LoginTestWithPageObject extends BaseTest {
    @Test
    @Category(SmokeTestFilter.class)
    public void validLoginTest(){
        loginPage.openLoginPage();
        loginPage.enterLoginIntoInputLogin("qaauto");
        loginPage.enterPassWordIntoPassWord("123456qwerty");
        loginPage.clickOnButtonSignIn();

        Assert.assertTrue("Button Sign Out is not displayed", homePage.isButtonSignOutDisplayed());
    }

    @Test
    @Ignore
    public void validLoginTestWithExcel() throws IOException {
        Map<String,String> dataForValidLogin = ExcelDriver.getData(configProperties.DATA_FILE(), "validLogOn");
        loginPage.openLoginPage();
        loginPage.enterLoginIntoInputLogin(dataForValidLogin.get("login"));
        loginPage.enterPassWordIntoPassWord(dataForValidLogin.get("pass"));
        loginPage.clickOnButtonSignIn();

        Assert.assertTrue("Button Sign Out is not displayed", homePage.isButtonSignOutDisplayed());
    }

    @Test
    public void unValidLoginTest(){
        loginPage.openLoginPage();
        loginPage.enterLoginIntoInputLogin("qaaut");
        loginPage.enterPassWordIntoPassWord("123456qwert");
        loginPage.clickOnButtonSignIn();

        Assert.assertTrue("Button Sign Out is not displayed", homePage.isButtonSignInDisplayed());
    }

}
