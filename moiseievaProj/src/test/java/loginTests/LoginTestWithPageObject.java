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

import static pages.ParentPage.*;
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
    // @Ignore
    @Category(SmokeTestFilter.class)
    public void validLoginTest(){
        loginPage.openLoginPage();
        loginPage.enterLoginIntoInputLogin("qaauto");
        loginPage.enterPasswordIntoInputLogin("123456qwerty");
        loginPage.clickOnButtonSignIn();
        checkExpectedResult("Button Sign Out is displayed", homePage.isButtonSignOutDisplayed());
    }

    @Test
    public void validLoginExcelTest() throws IOException {
        Map<String, String> dataFromValidLogin = ExcelDriver.getData(configProperties.DATA_FILE(), "validLogOn");
        loginPage.openLoginPage();
        loginPage.enterLoginIntoInputLogin(dataFromValidLogin.get("login"));
        loginPage.enterPasswordIntoInputLogin(dataFromValidLogin.get("pass"));
        loginPage.clickOnButtonSignIn();
        Assert.assertTrue("Button Sign Out is displayed", homePage.isButtonSignOutDisplayed());
    }

    @Test
    public void invalidLoginTest(){
        loginPage.openLoginPage();
        loginPage.enterLoginIntoInputLogin("qa-auto");
        loginPage.enterPasswordIntoInputLogin("123456qwerty");
        loginPage.clickOnButtonSignIn();
        Assert.assertEquals("Invalid username / password", loginPage.getTextFromAllert());
        Assert.assertFalse("Button Sign Out is not displayed",  homePage.isButtonSignOutDisplayed());
    }

}
