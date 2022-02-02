package registrationTest;

import baseTest.BaseTest;
import junitparams.JUnitParamsRunner;
//import org.junit.Assert;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.junit.Test;
import org.junit.runner.RunWith;


@RunWith(JUnitParamsRunner.class)
public class RegistrationTests extends BaseTest {
    // String expectedErrors = "Username must be at least 3 characters.;You must provide a valid email address.;Password must be at least 12 characters.";

//    @Test
//    public void checkingValidationMess(){
//        loginPage.openLoginPage();
//        loginPage.enterLoginRegistration("tr");
//        loginPage.enterEmailRegistration("test.com");
//        loginPage.enterPasswordRegistration("123");
//        loginPage.clickOnButtonSignUp();
//
//        Assert.assertTrue("Login validation error missing", loginPage.isErrorLoginValidationForSignUpDisplayed());
//        Assert.assertTrue("Email validation error missing", loginPage.isErrorEmailValidationForSignUpDisplayed());
//        Assert.assertTrue("Password validation error missing" , loginPage.isErrorPasswordValidationForSignUpDisplayed());
//
//
//    }

    @Test
    @Parameters({
            "tr,qqq,345,Username must be at least 3 characters.;You must provide a valid email address.;Password must be at least 12 characters."
            ,"tr,test@qqqq.com,123456qwerty,Username must be at least 3 characters."
            ,"tr,test,123456qwerty,Username must be at least 3 characters.;You must provide a valid email address."
    })
    @TestCaseName("registrationErrors : login = {0}, email = {1}, password = {2}")
    public void registrationErrors(String login, String email, String password, String expectedErrors){
        loginPage.openLoginPage();
        loginPage
                .enterLoginRegistration(login)
                .enterEmailRegistration(email)
                .enterPasswordRegistration(password)
                .checkErrorsMessages(expectedErrors);



    }

}
