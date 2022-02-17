package loginTests;

import baseTest.BaseTest;
import categories.SmokeTestFilter;
import io.qameta.allure.*;
import libs.ExcelDriver;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;

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
    @Ignore
    public void invalidLoginTestSecondVersion() {
        loginPage.loginWithInvalidCredentials();
        loginPage.checkIsErrorMessageSignInDisplayed();
    }
}
