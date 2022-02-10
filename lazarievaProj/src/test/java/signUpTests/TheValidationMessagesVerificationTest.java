package signUpTests;

import baseTest.BaseTest;
import categories.SmokeTestFilter;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
@Category(SmokeTestFilter.class)
public class TheValidationMessagesVerificationTest extends BaseTest {
//    @Test
//    public void verifyTheValidationMessagesDisplayed() {
//        loginPage.openLoginPage();
//        loginPage.enterInvalidDataInTheSignUpForm();
//
//        Assert.assertTrue("NO message - 'Username must be at least 3 characters.'",
//                loginPage.isValidationUserNameMessageDisplayed());
//        Assert.assertTrue("NO message - 'You must provide a valid email address.'",
//                loginPage.isValidationUserEmailMessageDisplayed());
//        Assert.assertTrue("NO message - Password must be at least 12 characters.",
//                loginPage.isValidationUserPasswordMessageDisplayed());
//    }

   // String expectedErrors = "Username must be at least 3 characters.;You must provide a valid email address.;Password must be at least 12 characters.";
    @Test
    // на даний тест можна додати @Categories.IncludeCategory(SmokeSuitWithCategories.class)
    // тільки на цілий клас!!! @Categories.IncludeCategory(SmokeSuitWithCategories.class)
    @Parameters({
            "tr,qqq,345,Username must be at least 3 characters.;You must provide a valid email address.;Password must be at least 12 characters."
            ,"tr,test@qqqq.com,123456qwerty,Username must be at least 3 characters."
            ,"tr,test,123456qwerty,Username must be at least 3 characters.;You must provide a valid email address."

    })
    @TestCaseName("registrationErrors : login = {0}, email = {1}, password = {2}")
    public void registrationErrors(String login, String email, String password, String expectedErrors) {
        loginPage.openLoginPage();
        loginPage
                .enterUserNameInTheSignUpForm(login)
                .enterUserEmailInTheSignUpForm(email)
                .enterUserPasswordInTheSignUpForm(password)
                .checkErrorMessages(expectedErrors);


    }

}
