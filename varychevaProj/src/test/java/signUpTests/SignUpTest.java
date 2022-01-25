package signUpTests;

import baseTest.BaseTest;
import org.junit.Test;

public class SignUpTest extends BaseTest {
    @Test
    public void signUpValidationMessages() {
        loginPage.openLoginPage();
        signUpPage.singUpWithInvalidData()
                .checkErrorMessages();
    }
}
