package registrationTests;

import baseTest.BaseTest;
import org.junit.Test;

public class RegistrationTest extends BaseTest {

    String expectedErrors = "Username must be at least 3 characters.;You must provide a valid email address.;Password must be at least 12 characters.";
    @Test
    public void invalidRegistrationTest() {
        registrationPage.userInvalidRegistration();
        registrationPage.isRegistrationErrorsDisplayed();
    }

    @Test
    public void registrationErrors(){
        loginPage.openLoginPage();
        loginPage
                .enterLoginRegistration("tr")
                .enterEmailRegistration("123")
                .enterPassWordRegistration("159")
                .checkErrorsMessages(expectedErrors);
    }
}
