package registrationTest;

import baseTest.BaseTest;
import org.junit.Assert;
import org.junit.Test;

public class RegistrationTests extends BaseTest {
    String expectedErrors = "Username must be at least 3 characters.;You must provide a valid email address.;Password must be at least 12 characters.";

    @Test
    public void checkingValidationMess(){
        loginPage.openLoginPage();
        loginPage.enterLoginRegistration("tr");
        loginPage.enterEmailRegistration("test.com");
        loginPage.enterPasswordRegistration("123");
        loginPage.clickOnButtonSignUp();

        Assert.assertTrue("Login validation error missing", loginPage.isErrorLoginValidationForSignUpDisplayed());
        Assert.assertTrue("Email validation error missing", loginPage.isErrorEmailValidationForSignUpDisplayed());
        Assert.assertTrue("Password validation error missing" , loginPage.isErrorPasswordValidationForSignUpDisplayed());


    }

    @Test
    public void registrationErrors(){
        loginPage.openLoginPage();
        loginPage
                .enterLoginRegistration("tr")
                .enterEmailRegistration("qqq")
                .enterPasswordRegistration("345")
                .checkErrorsMessages(expectedErrors);



    }

}
