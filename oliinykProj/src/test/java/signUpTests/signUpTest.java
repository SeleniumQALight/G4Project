package signUpTests;

import baseTest.BaseTest;
import org.junit.Test;

public class signUpTest extends BaseTest {

    String expectedErrors = "Username must be at least 3 characters.;You must provide a valid email address.;Password must be at least 12 characters.";

    @Test
    public void notValidSignUp(){
        loginPage.openLoginPage();
        loginPage.enterLoginSignUp("tr");
        loginPage.enterMailSignUp("test.com");
        loginPage.enterPasswordSignUp("123");
        loginPage.checkErrorTextSignUpLogin();
        loginPage.checkErrorTextSignUpMail();
        loginPage.checkErrorTextSignUpPass();
    }

    @Test
    public void registrationErrors(){
        loginPage.openLoginPage();
        loginPage.enterLoginSignUp("tr")
                .enterMailSignUp("qqq")
                .enterPasswordSignUp("345")
                .checkErrorsMessages(expectedErrors);
    }
}
