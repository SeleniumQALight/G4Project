package signUpTests;

import baseTest.BaseTest;
import libs.TestData;
import org.junit.Test;

public class signUpTest extends BaseTest {

    @Test
    public void notValidSignUp(){
        loginPage.openLoginPage();
        loginPage.enterNotValidLogin("tr");
        loginPage.enterNotValidMail("test.com");
        loginPage.enterNotValidPassword("123");
        loginPage.checkErrorTextSignUpLogin();
        loginPage.checkErrorTextSignUpMail();
        loginPage.checkErrorTextSignUpPass();
    }
}
