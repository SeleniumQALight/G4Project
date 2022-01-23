package registrationTests;

import baseTest.BaseTest;
import org.junit.Assert;
import org.junit.Test;

public class ValidationMessagesTests extends BaseTest {
    @Test
    public void validationMsgForRegistration() {
        loginPage.openLoginPage();
        loginPage.enterUsernameForRegistration("tr");
        loginPage.enterEmailForRegistration("test.com");
        loginPage.enterPasswordForRegistration("123");
        loginPage.clickOnButtonSignUpForOurApp();
        Assert.assertTrue("Username must be at least 3 characters. is not displayed."
                , loginPage.errormsgValidUsernameIsDisplayed());
        Assert.assertTrue("You must provide a valid email address. is not displayed."
                , loginPage.errormsgValidemailIsDisplayed());
        Assert.assertTrue("Password must be at least 12 characters. is not displayed."
                , loginPage.errormsgValidPasswordIsDisplayed());
        Assert.assertTrue("Button SignUpForOurApp is not displayed"
                , loginPage.isButtonSignUpForOurAppDisplayed());
    }
    }

