package registrationTests;

import baseTest.BaseTest;
import org.junit.Test;

public class RegistrationTest extends BaseTest {

    @Test
    public void invalidRegistrationTest() {
        loginPage.openLoginPage();
        registrationPage.userInvalidRegistration();
        registrationPage.isRegistrationErrorsDisplayed();
    }
}
