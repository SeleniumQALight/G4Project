package signUpTests;

import baseTest.BaseTest;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class signUpTest extends BaseTest {

//    String expectedErrors = "Username must be at least 3 characters.;You must provide a valid email address.;Password must be at least 12 characters.";

//    @Test
//    public void notValidSignUp(){
//        loginPage.openLoginPage();
//        loginPage.enterLoginSignUp("tr");
//        loginPage.enterMailSignUp("test.com");
//        loginPage.enterPasswordSignUp("123");
//        loginPage.checkErrorTextSignUpLogin();
//        loginPage.checkErrorTextSignUpMail();
//        loginPage.checkErrorTextSignUpPass();
//    }

    @Test
    @Parameters({
            "tr,qqq,345,Username must be at least 3 characters.;You must provide a valid email address.;Password must be at least 12 characters."
            ,"tr,test@qqq.com,123456qwerty,Username must be at least 3 characters."
            ,"tr,test,123456qwerty,Username must be at least 3 characters.;You must provide a valid email address."

    })
    @TestCaseName("registrationErrors : login = {0}, email = {1}, password = {2}")
    public void registrationErrors(String login, String email, String password, String expectedErrors){
        loginPage.openLoginPage();
        loginPage.enterLoginSignUp(login)
                .enterMailSignUp(email)
                .enterPasswordSignUp(password)
                .checkErrorsMessages(expectedErrors);
    }
}
