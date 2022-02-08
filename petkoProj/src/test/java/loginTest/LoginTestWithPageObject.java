package loginTest;

import baseTest.BaseTest;
import libs.ExcelDriver;
import org.junit.Assert;
import org.junit.Test;


import java.io.IOException;
import java.util.Map;

import static pages.ParentPage.configProperties;

public class LoginTestWithPageObject extends BaseTest {

    @Test
    public void validLoginTest(){
        loginPage.openLoginPage();
        loginPage.enterLoginIntoInputLogin("qaauto");
        loginPage.enterPassWordIntoInputPassWord("123456qwerty");
        loginPage.clickOnButtonSignIn();

        Assert.assertTrue("Button Sign Out is not displayed", homePage.isButtonSignOutDisplayed());


    }

    @Test
    public void validLoginTestWithExcel() throws IOException {
        Map<String,String> dataForValidLogin = ExcelDriver.getData(configProperties.DATA_FILE(),"validLogOn");
        loginPage.openLoginPage();
        loginPage.enterLoginIntoInputLogin(dataForValidLogin.get("login"));
        loginPage.enterPassWordIntoInputPassWord(dataForValidLogin.get("pass"));
        loginPage.clickOnButtonSignIn();

        Assert.assertTrue("Button Sign Out is not displayed", homePage.isButtonSignOutDisplayed());


    }

    @Test
    public void invalidLoginTest(){
        loginPage.openLoginPage();
        loginPage.enterLoginIntoInputLogin("qaautoZ");
        loginPage.enterPassWordIntoInputPassWord("123456qwerty");
        loginPage.clickOnButtonSignIn();

        Assert.assertTrue("Button Sign In is not displayed", loginPage.isButtonSignInDisplayed());


    }

}
