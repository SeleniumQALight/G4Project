package loginTests;

import baseTest.BaseTest;
import categories.SmokeTestFilter;
import io.qameta.allure.*;
import libs.ExcelDriver;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import pages.ParentPage;

import java.io.IOException;
import java.util.Map;

import static pages.ParentPage.configProperties;
@Epic("Allure examples")
@Feature("Junit 4 support")


public class LoginTestWithPageObject extends BaseTest {
    @Description("Some detailed test description")
    @Link("https://example.org")
    @Link(name = "allure", type = "mylink")
    @Issue("123")
    @Issue("432")
    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Story("Base support for bdd annotations")

    @Category(SmokeTestFilter.class)
    public void validLoginTest() {
        loginPage.openLoginPage();//обращение к логин пейдж и открытие ее
        loginPage.enterLoginIntoInputLogin("qaauto"); //alt + entr выбрать создать и переносит в логин
        loginPage.enterPassWordIntoInputPassWord("123456qwerty");
        loginPage.clickOnButtonSignIn();

        checkER("ButtonSignOut is not displayed", homePage.isButtonSignOutDisplayed());
    }

    @Test
    public void validLoginTestWithExcel() throws IOException {
        Map<String,String> dataForValidLogin = ExcelDriver.getData(configProperties.DATA_FILE(),"validLogOn");
        loginPage.openLoginPage();
        loginPage.enterLoginIntoInputLogin(dataForValidLogin.get("login"));
        loginPage.enterPassWordIntoInputPassWord(dataForValidLogin.get("pass"));
        loginPage.clickOnButtonSignIn();

        checkER("ButtonSignOut is not displayed", homePage.isButtonSignOutDisplayed());
    }
    @Test
    public void notValidLoginTest() {
        loginPage.openLoginPage();
        loginPage.enterLoginIntoInputLogin("qaaut");
        loginPage.enterPassWordIntoInputPassWord("123456qwerty");
        loginPage.clickOnButtonSignIn();

        checkER("Button SignUpForOurApp is not displayed. Not valid Login", loginPage.IsButtonSignUpForOurAppDisplayed());
    }
    @Test
    public void notValidPasswordTest() {
        loginPage.openLoginPage();
        loginPage.enterLoginIntoInputLogin("qaauto");
        loginPage.enterPassWordIntoInputPassWord("123456qwert");
        loginPage.clickOnButtonSignIn();

        checkER("Button SignUpForOurApp is not displayed. Not valid Password", loginPage.IsButtonSignUpForOurAppDisplayed());
    }
    @Test
    public void loginNotEntered() {
        loginPage.openLoginPage();
        loginPage.enterLoginIntoInputLogin("");
        loginPage.enterPassWordIntoInputPassWord("123456qwerty");
        loginPage.clickOnButtonSignIn();

        checkER("Button SignUpForOurApp is not displayed. Login not entered", loginPage.IsButtonErrorForOurAppDisplayed());
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
