package registrationTests;

import baseTest.BaseTest;
import org.junit.Test;

public class RegistrationTest extends BaseTest {

    @Test
    public void invalidRegistrationTest() {
        registrationPage.userInvalidRegistration();
        registrationPage.isRegistrationErrorsDisplayed();
    }
}
