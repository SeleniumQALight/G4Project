package registrationTests;

import baseTest.BaseTest;
import org.junit.Assert;
import org.junit.Test;

public class RegistrationTestWithPageObject extends BaseTest {
    @Test
    public void unValidRegistration() {
        loginPage.openLoginPage();
        loginPage.enterUsernameIntoInputPickUsername("tr");
        Assert.assertTrue("Message is not displayed", loginPage.messageFieldPickUsernameDisplayed());
        loginPage.enterEmailIntoInputEmail("test.com");
        Assert.assertTrue("Message is not displayed", loginPage.messageFieldEmail());
        loginPage.enterCreatePasswordIntoInputCreatePaswword("123");
        Assert.assertTrue("Message is not displayed",loginPage.messageFieldCreatePassword());
        loginPage.clickOnButtonSignUpForOurApp();


    }
}
