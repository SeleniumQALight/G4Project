package registrationTests;

import baseTest.BaseTest;
import org.junit.Assert;
import org.junit.Test;

public class RegistrationTestWithPageObject extends BaseTest {
    @Test
    public void unValidRegistration() {
        loginPage.openLoginPage();
        loginPage.enterUsernameIntoInputPickUsername("tr");
        loginPage.enterEmailIntoInputEmail("test.com");
        loginPage.enterCreatePasswordIntoInputCreatePaswword("123");
        loginPage.clickOnButtonSignUpForOurApp();
        loginPage.checkIsMessagesDisplayed();
        Assert.assertFalse("Go to page HomePage", homePage.isButtonSignOutDisplayed());

    }
}
