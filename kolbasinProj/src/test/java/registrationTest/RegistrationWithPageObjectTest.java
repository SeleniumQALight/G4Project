package registrationTest;

import baseTest.BaseTest;
import categories.SmokeTestFilter;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)  //
@Category(SmokeTestFilter.class)
public class RegistrationWithPageObjectTest extends BaseTest {
    //String expectedErrors = "Username must be at least 3 characters.;You must provide a valid email address.;Password must be at least 12 characters.";
    @Test
    @Parameters({
            "tr,ttt,852,Username must be at least 3 characters.;You must provide a valid email address.;Password must be at least 12 characters."
            ,"tr,test1@mail.com,123789qwerty,Username must be at least 3 characters."
    })
    @TestCaseName("registrationErrors : login = {0}, email = {1}, password = {2}")
    public void registrationErrors(String login, String email, String password, String expectedErrors){
        loginPage.openLoginPage();
        loginPage
                .enterLoginRegistration(login)
                .enterEmailRegistration(email)
                .enterPassWordRegistration(password)
                .checkErrorsMessages(expectedErrors);
    }
}
