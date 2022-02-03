package loginTests;

import baseTest.BaseTest;
import libs.ExcelDriver;
import org.junit.Test;

import java.io.IOException;
import java.util.Map;

import static pages.ParentPage.configProperties;

public class LoginTestWithPageObject extends BaseTest {
    @Test
    public void validLoginTest() {
        loginPage.openLoginPage();
        loginPage.enterLoginIntoInputLogin("qaauto");
        loginPage.enterLoginIntoInputPassword("123456qwerty");
        loginPage.clickOnButtonSignIn();

        homePage.checkIsButtonSignOutDisplayed();
    }

    @Test
    public void validLoginTestWithExcel() throws IOException {
        Map<String, String> dataValidLogin = ExcelDriver.getData(configProperties.DATA_FILE(), "validLogOn");
        loginPage.openLoginPage();
        loginPage.enterLoginIntoInputLogin(dataValidLogin.get("login"));
        loginPage.enterLoginIntoInputPassword(dataValidLogin.get("pass"));
        loginPage.clickOnButtonSignIn();

        homePage.checkIsButtonSignOutDisplayed();
    }

    @Test
    public void invalidLoginTest() {
        loginPage.openLoginPage();
        loginPage.enterLoginIntoInputLogin("qa");
        loginPage.enterLoginIntoInputPassword("123456qwerty");
        loginPage.clickOnButtonSignIn();
        loginPage.checkIsErrorMessageSignInDisplayed();
    }


    @Test
    public void invalidLoginTestSecondVersion() {
        loginPage.loginWithInvalidCredentials();
        loginPage.checkIsErrorMessageSignInDisplayed();
    }
}
