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
public class RegistrationTest extends BaseTest {

//    String expectedErrors = "Username must be at least 3 characters.;You must provide a valid email address.;Password must be at least 12 characters.";

//    @Test
//    public void invalidRegistrationTest() {
//        registrationPage.userInvalidRegistration();
//        registrationPage.isRegistrationErrorsDisplayed();
//    }

    @Test
    @Parameters({
            "tr,123,159,Username must be at least 3 characters.;You must provide a valid email address.;Password must be at least 12 characters."
            ,"tr,ghghg@aa.aa,123456qwertyy,Username must be at least 3 characters."
            ,
    })
    @TestCaseName("registrationErrors : login = {0}, email = {1}, password = {2}")
    public void registrationErrors(String login, String email, String pass, String expectedErrors){
        loginPage.openLoginPage();
        loginPage
                .enterLoginRegistration(login)
                .enterEmailRegistration(email)
                .enterPassWordRegistration(pass)
                .checkErrorsMessages(expectedErrors);
    }
}
