package loginTests;

import baseTest.BaseTest;
import categories.SmokeTestFilter;
import io.qameta.allure.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

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
    // @Test
    // @Ignore -- to skip test
    @Category(SmokeTestFilter.class)
    public void validLogin() {
        loginPage.openLoginPage();
        loginPage.enterLoginIntoInputLogin("qaauto");
        // loginPage.enterPasswordIntoInputPassword("123456qwerty");
        loginPage.enterPasswordIntoInputPassword("123456qwerty11");
        loginPage.clickOnButtonSignIn();

//        Assert.assertTrue("Button SignOut is not displayed",
//                homePage.isButtonSignOutDisplayed());
        // added assert for allure report
        checkExpResult("Button SignOut is not displayed", homePage.isButtonSignOutDisplayed());

    }

    @Description("Next test description")
    @Link("https://example.org")
    @Link(name = "allure", type = "mylink1")
    @Issue("555")
    @Issue("434")
    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Story("Base support for bdd annotations")
    public void invalidLogin() {
        loginPage.openLoginPage();
        loginPage.enterLoginIntoInputLogin("test555");
        loginPage.enterPasswordIntoInputPassword("12qwerty");
        loginPage.clickOnButtonSignIn();

        Assert.assertFalse("Button SignOut is not displayed",
                homePage.isButtonSignOutDisplayed());

        Assert.assertTrue("The the SignUP Button isn`t displayed",
                loginPage.verifyIfTheSignUPButtonIsDisplayed());


    }


}
