package registrationTests;

import baseTest.BaseTest;
import categories.SmokeTestFilter;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;


@RunWith(JUnitParamsRunner.class)
@Category(SmokeTestFilter.class)
public class RegistrationTestWithPageObject extends BaseTest {
    @Test
    public void unValidRegistration() {
        loginPage.openLoginPage();
        loginPage.enterLoginRegistration("tr");
        loginPage.enterEmailRegistration("test.com");
        loginPage.enterCreatePasswordRegistration("123");
        loginPage.clickOnButtonSignUpForOurApp();
        loginPage.checkIsMessagesDisplayed();
        Assert.assertFalse("Go to page HomePage", homePage.isButtonSignOutDisplayed());

    }

    String expectedErrors = "Username must be at least 3 characters.;" +
            "You must provide a valid email address.;" +
            "Password must be at least 12 characters.";

    @Test
    @Parameters({
            "tr,qqq,234,Username must be at least 3 characters.;You must provide a valid email address.;Password must be at least 12 characters."
            , "tr,test@qqq.com,123456qwerty,Username must be at least 3 characters."
            , "tr,test,123456qwerty,Username must be at least 3 characters.;You must provide a valid email address."
    })
    @TestCaseName("registrationErrors : login = {0}, email = {1}, password = {2}")

    public void registrationErrors(String login, String email, String password, String expectedErrors) {
        loginPage.openLoginPage();
        loginPage.enterLoginRegistration(login)
                .enterEmailRegistration(email)
                .enterCreatePasswordRegistration(password)
                .checkErrorMessages(expectedErrors);


    }
}
