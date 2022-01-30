package registrationTests;

import baseTest.BaseTest;
import org.junit.Test;

public class RegistrationTestsSecondVersion extends BaseTest {
    String expectedErrors = "Username must be at least 3 characters.;You must provide a valid email address.;Password must be at least 12 characters.";
    @Test
    public void registrationErrors(){
        loginPage.openLoginPage();
        loginPage
                .enterUsernameIntoInputUsernameSignUpForm("1")
                .enterEmailIntiEmailInputSignUpForm("1")
                .enterPasswordIntoPasswordInputSignUpForm("1")
                .checkErrorMessages(expectedErrors);
    }
}
