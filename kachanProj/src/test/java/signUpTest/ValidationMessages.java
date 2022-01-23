package signUpTest;

import baseTest.BaseTest;
import org.junit.Assert;
import org.junit.Test;

public class ValidationMessages extends BaseTest {

    @Test
    public void signUpValidationMessages() {
        loginPage.openLoginPage();
        loginPage.enterLoginIntoRegistrationForm("tr");
        loginPage.enterEmailIntoRegistrationForm("test.com");
        loginPage.enterPasswordIntoRegistrationForm("123");
        loginPage.clickOnButtonSignUp();

        Assert.assertTrue("Username error messages is not displayed", loginPage.checkValidationFormsDisplayed());
    }

}
