package registrationTests;

import baseTest.BaseTest;
import org.junit.Test;

public class RegistrTestWithPageObject extends BaseTest {
String expectedErrors ="Username must be at least 3 characters.;You must provide a valid email address.;Password must be at least 12 characters.";
    @Test
    public void registrationErrors(){
    loginPage.openLoginPage();

                loginPage.enterUsernameForRegistration("tr");
            //.enterLoginRegistration("tr")
                loginPage.enterEmailForRegistration("qqq");
            //.enterEmailRegistration("qqq")
                loginPage.enterPasswordForRegistration("345");
            //.enterPassWordRegistration("345")
                loginPage.checkErrorsMessages(expectedErrors);
}
}
