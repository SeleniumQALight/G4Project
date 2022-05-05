package registrationTests;

import baseTest.BaseTest;
import categories.SmokeTestFilter;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
@Category(SmokeTestFilter.class)
public class RegistrationTestsSecondVersion extends BaseTest {
    //String expectedErrors = "Username must be at least 3 characters.;You must provide a valid email address.;Password must be at least 12 characters.";
    @Test
    @Parameters({
            "tr,qqq,345,Username must be at least 3 characters.;You must provide a valid email address.;Password must be at least 12 characters.",
            "qw,roman@gmail.com,Roman19881111,Username must be at least 3 characters.",
            "tr,qqq,Roman19881111,Username must be at least 3 characters.;You must provide a valid email address."
    })
    @TestCaseName("registrationErrors : login = {0}, email = {1}, password = {2}")
    public void registrationErrors(String username, String email, String password, String expectedErrors){
        loginPage.openLoginPage();
        loginPage
                .enterUsernameIntoInputUsernameSignUpForm(username)
                .enterEmailIntiEmailInputSignUpForm(email)
                .enterPasswordIntoPasswordInputSignUpForm(password)
                .checkErrorMessages(expectedErrors);
    }
}
