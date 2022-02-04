package singUpTestForOurApp;

import baseTest.BaseTest;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)// с помощю этой натации запустим несколько раз с разными параметрами

public class CheckValidationMessage extends BaseTest {
    //String expectedErrors ="Username must be at least 3 characters.;You must provide a valid email address.;Password must be at least 12 characters.";
//
//    @Test
//    public void checkMassage(){
//        loginPage
//                .enterInvalidDataForOurApp();
//
//        Assert.assertTrue(" Massage is not SHOW-'Username must be at least 3 characters.' "
//                ,loginPage.isValidationTextUsernameDisplayed());
//        Assert.assertTrue("Massage is not SHOW-'You must provide a valid email address.'"
//                ,loginPage.isValidationTextEmailDisplayed());
//        Assert.assertTrue("Massage is not SHOW-'Password must be at least 12 characters.' "
//                ,loginPage.isValidationTextPasswordDisplayed());
//
//
//    }

    @Test
    @Parameters({
            "tr,qqq,345,Username must be at least 3 characters.;You must provide a valid email address.;Password must be at least 12 characters."
            ,"tr,qqq,test@qqqq.com,Username must be at least 3 characters."
            ,"tr,test,123456qwerty,Username must be at least 3 characters.;You must provide a valid email address."
    })
    @TestCaseName("registrationErrors : login = {0}, email = {1}, password = {2})")
    public void registrationErrors(String login,String email, String password,String expectedErrors){
        loginPage.openLoginPage();
        loginPage
                .enterUserNameForOurApp(login)
                .enterEmailForOurApp(email)
                .enterPasswordForOurApp(password)
                .checkErrorsMessages(expectedErrors);

    }
}
