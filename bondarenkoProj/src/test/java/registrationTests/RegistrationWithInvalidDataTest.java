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
public class RegistrationWithInvalidDataTest extends BaseTest {
    //String expectedErrors = "Username must be at least 3 characters.;You must provide a valid email address.;Password must be at least 12 characters.";
   // @Test
    //public  void enterInvalidData(){
      //  loginPage.openLoginPage();
      //  loginPage.enterUsernameIntoInputUsernameSignUp("tr");
      //  loginPage.enterEmailIntoInputEmailSignUp("test.com");
       // loginPage.enterPasswordIntoInputPasswordSignUp("123");
      //  loginPage.clickOnButtonSignUpForOurApp();
      //  loginPage.checkIsMessageForUsernameSignUpFieldDisplayed();
       // loginPage.checkIsMessageForEmailSignUpFieldDisplayed();
      //  loginPage.checkIsMessageForPasswordSignUpFieldDisplayed();
   // }
@Test
@Parameters({
        "tr, qqq, 345, Username must be at least 3 characters.;You must provide a valid email address.;Password must be at least 12 characters.",
        "tr, test@qqqq.com, 123456qwerty, Username must be at least 3 characters.",
        "tr, test, 123456qwerty, Username must be at least 3 characters.;You must provide a valid email address."
})
@TestCaseName("registrationErrors : login = {0}, email = {1}, password = {2}")
    public void registrationErrors(String login, String email, String password, String expectedErrors){
        loginPage.openLoginPage();
        loginPage.enterUsernameIntoInputUsernameSignUp(login);
        loginPage.enterEmailIntoInputEmailSignUp(email);
        loginPage.enterPasswordIntoInputPasswordSignUp(password);
        loginPage.checkErrorsMessages(expectedErrors);
    }
}
