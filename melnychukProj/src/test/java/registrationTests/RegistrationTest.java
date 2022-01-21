package registrationTests;

import baseTest.BaseTest;
import org.junit.Assert;
import org.junit.Test;
import pages.LoginPage;

public class RegistrationTest extends BaseTest {
    final String userNameValidationText = "Username must be at least 3 characters.";
    final String mailValidationText = "You must provide a valid email address.";
    final String passwordValidationText = "Password must be at least 12 characters.";

    @Test
    public void validationMessagesAfterInvalidLoginTest() {

        loginPage.openLoginPage();
        loginPage.enterLoginForRegistration("tr");
        loginPage.enterEmailForRegistration("test.com");
        loginPage.enterPasswordForRegistration("123");
        loginPage.clickOnSignUpButton();
        loginPage.checkTextInValidationUsername(userNameValidationText)
                .checkTextInValidationMail(mailValidationText)
                .checkTextInValidationPassword(passwordValidationText);


    }
}
