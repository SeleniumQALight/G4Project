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
        Assert.assertTrue("Message is not displayed", loginPage.messageFieldPickUsernameDisplayed());
        Assert.assertTrue("Message is not displayed", loginPage.messageFieldEmail());
        Assert.assertTrue("Message is not displayed", loginPage.messageFieldCreatePassword());
    }
}
