package signUpTests;

import baseTest.BaseTest;
import org.junit.Test;

public class SignUpTest extends BaseTest {

    @Test
    public void invalidSignUpTest() {
        loginPage
                .signUpWithInvalidCredentials()
                .checkIsErrorUsernameSignupDisplayed()
                .checkIsErrorEmailSignupDisplayed()
                .checkIsErrorPasswordSignupDisplayed();
    }
}
