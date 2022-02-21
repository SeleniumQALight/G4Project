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
public class RegistrTestWithPageObject extends BaseTest {
    //    String expectedErrors ="Username must be at least 3 characters.;You must provide a valid email address.;Password must be at least 12 characters.";
    @Test
//    public void registrationErrors(){
//        loginPage.openLoginPage();
//
//        loginPage.enterUsernameIntoInputUsernameSignUp("tr");
//        loginPage.enterEmailIntoInputEmailSignUp("qqq");
//        loginPage.enterPasswordIntoInputPasswordSignUp("345");
//        loginPage.checkErrorsMessages(expectedErrors);
//    }
    @Parameters({
            "tr,qqq,345,Username must be at least 3 characters.;You must provide a valid email address.;Password must be at least 12 characters."
            , "tr,test@qqqq.com,123456qwerty,Username must be at least 3 characters."
            , "tr,qqq,123456qwerty,Username must be at least 3 characters.;You must provide a valid email address."
    })
    @TestCaseName("registrationErrors : login = {0}, email = {1}, password = {2}")
    public void registrationErrors(String login, String email, String passWord, String expectedErrors) {
        loginPage.openLoginPage();
        loginPage
                .enterUsernameIntoInputUsernameSignUp(login)
                .enterEmailIntoInputEmailSignUp(email)
                .enterPasswordIntoInputPasswordSignUp(passWord)
                .checkErrorsMessages(expectedErrors);
    }
}
