package registrationTest;

import baseTest.BaseTest;
import org.junit.Assert;
import org.junit.Test;

public class RegistrationTest extends BaseTest {

    @Test
    public void checkingValidationMess(){
        loginPage.openLoginPage();
        loginPage.enterSignUpLogin("tr");
        loginPage.enterSignUpEmail("test.com");
        loginPage.enterSignUpPassword("123");
        loginPage.clickOnButtonSignUp();

        Assert.assertTrue("Login validation error missing", loginPage.isErrorLoginValidationForSignUpDisplayed());
        Assert.assertTrue("Email validation error missing", loginPage.isErrorEmailValidationForSignUpDisplayed());
        Assert.assertTrue("Password validation error missing" , loginPage.isErrorPasswordValidationForSignUpDisplayed());


    }

}
