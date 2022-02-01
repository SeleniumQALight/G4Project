package registrationTests;

import baseTest.BaseTest;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class RegistrTestWithPageObject extends BaseTest {
//String expectedErrors ="Username must be at least 3 characters.;You must provide a valid email address.;Password must be at least 12 characters.";
//    @Test
//    @Parameters({
//           "tr,qqq,345,Username must be at least 3 characters.;You must provide a valid email address.;Password must be at least 12 characters."
//    })
//    public void registrationErrors(){
//    loginPage.openLoginPage();
//            loginPage.enterLoginRegistration("tr")
//                     .enterEmailRegistration("qqq")
//                     .enterPassWordRegistration("345")
//                     .checkErrorsMessages(expectedErrors);
//}
    @Test
    @Parameters({
            "tr,qqq,345,Username must be at least 3 characters.;You must provide a valid email address.;Password must be at least 12 characters."
    ,"tr,test@qqqq.com,123456qwerty,Username must be at least 3 characters."
,"tr,test@qqqq.com,123456qwerty,Username must be at least 3 characters.;You must provide a valid email address."
    })
    @TestCaseName("registrationErrors : login = {0}, email = {1}, password = {2}")
    public void registrationErrors(String login,String email,String passWord,String expectedErrors ){
        loginPage.openLoginPage();
        loginPage.enterUsernameForRegistration(login);
        loginPage.enterEmailForRegistration(email);
        loginPage.enterPasswordForRegistration(passWord);
        loginPage.checkErrorsMessages(expectedErrors);
    }
}
