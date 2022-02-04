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
    public void validLoginWithExcel() throws IOException {
        Map<String, String> dataForValidLogin = ExcelDriver.getData(configProperties.DATA_FILE(), "validLogOn");
        loginPage.openLoginPage();
        loginPage.enterLoginIntoInputLogin(dataForValidLogin.get("login"));
        loginPage.enterPasswordIntoInputPassword(dataForValidLogin.get("pass"));
        loginPage.clickOnSignIn();

        Assert.assertTrue("Button Sign Out went for vacation", homePage.isDispayedButtonSignOut());
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
