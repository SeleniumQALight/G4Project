package signUpTests;

import baseTest.BaseTest;
import org.junit.Test;

public class SignUpTest extends BaseTest {

    @Test
    public void invalidSignUpTest() {
        loginPage.openLoginPage();
        loginPage.enterUsernameIntoInputUsernameSignUp("tr");
        loginPage.enterEmailIntoInputEmailSignUp("test.com");
        loginPage.enterPasswordIntoInputPasswordSignUp("123");
        loginPage.clickOnButtonSignUp();
        loginPage.checkIsErrorUsernameSignupDisplayed();
        loginPage.checkIsErrorEmailSignupDisplayed();
        loginPage.checkIsErrorPasswordSignupDisplayed();
    }

    @Test
    public void invalidSignUpTestSecondVersion() {
        loginPage
                .signUpWithInvalidCredentials()
                .checkIsErrorUsernameSignupDisplayed()
                .checkIsErrorEmailSignupDisplayed()
                .checkIsErrorPasswordSignupDisplayed();
    }
}
