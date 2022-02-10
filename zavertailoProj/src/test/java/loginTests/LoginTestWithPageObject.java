package loginTests;

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
    public void validLoginTest() {
        loginPage.openLoginPage();//обращение к логин пейдж и открытие ее
        loginPage.enterLoginIntoInputLogin("qaauto"); //alt + entr выбрать создать и переносит в логин
        loginPage.enterPassWordIntoInputPassWord("123456qwerty");
        loginPage.clickOnButtonSignIn();

        Assert.assertTrue("ButtonSignOut is not displayed", homePage.isButtonSignOutDisplayed());
    }

    @Test
    public void validLoginTestWithExcel() throws IOException {
        Map<String,String> dataForValidLogin = ExcelDriver.getData(configProperties.DATA_FILE(),"validLogOn");
        loginPage.openLoginPage();
        loginPage.enterLoginIntoInputLogin(dataForValidLogin.get("login"));
        loginPage.enterPassWordIntoInputPassWord(dataForValidLogin.get("pass"));
        loginPage.clickOnButtonSignIn();

        Assert.assertTrue("ButtonSignOut is not displayed", homePage.isButtonSignOutDisplayed());
    }
    @Test
    public void notValidLoginTest() {
        loginPage.openLoginPage();
        loginPage.enterLoginIntoInputLogin("qaaut");
        loginPage.enterPassWordIntoInputPassWord("123456qwerty");
        loginPage.clickOnButtonSignIn();

        Assert.assertTrue("Button SignUpForOurApp is not displayed. Not valid Login", loginPage.IsButtonSignUpForOurAppDisplayed());
    }
    @Test
    public void notValidPasswordTest() {
        loginPage.openLoginPage();
        loginPage.enterLoginIntoInputLogin("qaauto");
        loginPage.enterPassWordIntoInputPassWord("123456qwert");
        loginPage.clickOnButtonSignIn();

        Assert.assertTrue("Button SignUpForOurApp is not displayed. Not valid Password", loginPage.IsButtonSignUpForOurAppDisplayed());
    }
    @Test
    public void loginNotEntered() {
        loginPage.openLoginPage();
        loginPage.enterLoginIntoInputLogin("");
        loginPage.enterPassWordIntoInputPassWord("123456qwerty");
        loginPage.clickOnButtonSignIn();

        Assert.assertTrue("Button SignUpForOurApp is not displayed. Login not entered", loginPage.IsButtonErrorForOurAppDisplayed());
    }
    @Test
    public void PasswordNotEntered() {
        loginPage.openLoginPage();
        loginPage.enterLoginIntoInputLogin("qaauto");
        loginPage.enterPassWordIntoInputPassWord("");
        loginPage.clickOnButtonSignIn();

        Assert.assertTrue("Button SignUpForOurApp is not displayed. Password not entered",
                loginPage.IsButtonErrorForOurAppDisplayed());
    }
}
