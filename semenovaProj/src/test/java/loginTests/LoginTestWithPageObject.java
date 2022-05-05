package loginTests;

import baseTest.BaseTest;
import categories.SmokeTestFilter;
import io.qameta.allure.*;
import libs.ExcelDriver;
import org.junit.Assert;
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
        loginPage.enterPassWordIntoInputPassword("123456qwerty");
        loginPage.clickOnButtonSingIn();

        checkER("Button SignOut is not displayed", homePage.isButtonSignOutDisplayed());
    }

    @Test
    @Ignore
    public void validLoginTestWithExcel() throws IOException {
        Map<String, String> dataForValidLogin = ExcelDriver.getData(configProperties.DATA_FILE()
                , "validLogOn");
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
