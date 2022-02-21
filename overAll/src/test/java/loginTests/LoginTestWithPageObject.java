package loginTests;

import static pages.ParentPage.configProperties;

import java.io.IOException;
import java.util.Map;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import baseTest.BaseTest;
import categories.SmokeTestFilter;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Issue;
import io.qameta.allure.Link;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import libs.ExcelDriver;
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
    public void validLoginTest(){
        loginPage.openLoginPage();
        loginPage.enterLoginIntoInputLogin("qaaut");
        loginPage.enterPassWordIntoInputPassWord("123456qwerty");
        loginPage.clickOnButtonSingIn();

        checkER("Button SignOut is not displayed"
                , homePage.isButtonSignOutDisplayed());
    }
    @Test
    public void validLoginTestWithExcel() throws IOException {
        Map<String,String> dataForValidLogin = ExcelDriver.getData(configProperties.DATA_FILE(),"validLogOn");
        loginPage.openLoginPage();
        loginPage.enterLoginIntoInputLogin(dataForValidLogin.get("login"));
        loginPage.enterPassWordIntoInputPassWord(dataForValidLogin.get("pass"));
        loginPage.clickOnButtonSingIn();

        checkER("Button SignOut is not displayed"
                , homePage.isButtonSignOutDisplayed());
    }
    @Test
    public void unvalidLoginTest(){
        loginPage.openLoginPage();
        loginPage.enterLoginIntoInputLogin("qaautoasdf");
        loginPage.enterPassWordIntoInputPassWord("123456qwerty");
        loginPage.clickOnButtonSingIn();

        Assert.assertFalse("Button SignOut is not displayed"
                , homePage.isButtonSignOutDisplayed());
    }

}
