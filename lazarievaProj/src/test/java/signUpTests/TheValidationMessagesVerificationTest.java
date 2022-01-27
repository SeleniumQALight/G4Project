package signUpTests;

import baseTest.BaseTest;
import org.junit.Assert;
import org.junit.Test;

public class TheValidationMessagesVerificationTest extends BaseTest {
    @Test
    public void verifyTheValidationMessagesDisplayed() {
        loginPage.openLoginPage();
        loginPage.enterInvalidDataInTheSignUpForm();

        Assert.assertTrue("NO message - 'Username must be at least 3 characters.'",
                loginPage.isValidationUserNameMessageDisplayed());
        Assert.assertTrue("NO message - 'You must provide a valid email address.'",
                loginPage.isValidationUserEmailMessageDisplayed());
        Assert.assertTrue("NO message - Password must be at least 12 characters.",
                loginPage.isValidationUserPasswordMessageDisplayed());

    }

    @Test
    public void registrationErrors() {
        String expectedErrors = "Username must be at least 3 characters.;You must provide a valid email address.;Password must be at least 12 characters.";
        loginPage.openLoginPage();

        loginPage
                .enterUserNameInTheSignUpForm("tr")
                .enterUserEmailInTheSignUpForm("ree")
                .enterUserPasswordInTheSignUpForm("345")
                .checkErrorMessages(expectedErrors);


    }

}
