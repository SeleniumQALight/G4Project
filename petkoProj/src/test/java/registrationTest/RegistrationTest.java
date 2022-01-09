package registrationTest;

import baseTest.BaseTest;
import org.junit.Test;

public class RegistrationTest extends BaseTest {

    @Test
    public void checkingValidationMess(){
        loginPage.openLoginPage();
        loginPage.enterSignUpLogin("tr");
        loginPage.enterSignUpEmail("test.com");
        loginPage.enterSignUpPassword("123");
        loginPage.clickOnButtonSignUp();

        loginPage.isErrorLoginValidationForSignUpDisplayed();
        loginPage.isErrorEmailValidationForSignUpDisplayed();
        loginPage.isErrorPasswordValidationForSignUpDisplayed();


    }

}
