package registrationTests;

import baseTest.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

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
    public void registrationErrors(){
        loginPage.openLoginPage();
        loginPage.enterLoginRegistration("tr")
                .enterEmailRegistration("qqq")
                .enterCreatePasswordRegistration("234")
                .checkErrorMessages(expectedErrors);



    }
}
