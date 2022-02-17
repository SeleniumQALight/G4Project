package loginTest;

import baseTest.BaseTest;
import categories.SmokeTestFilter;
import io.qameta.allure.*;
import libs.ExcelDriver;
import org.junit.Assert;
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
    public void validLoginWithExcel() throws IOException {
        Map<String, String> dataForValidLogin = ExcelDriver.getData(configProperties.DATA_FILE(), "validLogOn");
        loginPage.openLoginPage();
        loginPage.enterLoginIntoInputLogin(dataForValidLogin.get("login"));
        loginPage.enterPasswordIntoInputPassword(dataForValidLogin.get("pass"));
        loginPage.clickOnSignIn();

       checkER("Button Sign Out went for vacation", homePage.isDispayedButtonSignOut());
    }
    @Test
    public void notValidLogin(){
        loginPage.openLoginPage();
        loginPage.enterLoginIntoInputLogin("abc");
        loginPage.enterPasswordIntoInputPassword("abcdefgh");
        loginPage.clickOnSignIn();

        Assert.assertTrue("Error text was not displayed", loginPage.displayedMessageError());
        Assert.assertTrue("Button 'Sign up for OurApp' not displayed", loginPage.buttonSingUpIsDisplayed());
    }
}
