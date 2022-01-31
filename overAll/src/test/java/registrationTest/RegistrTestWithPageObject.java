package registrationTest;

import org.junit.Test;

import baseTest.BaseTest;

public class RegistrTestWithPageObject extends BaseTest {
    String expectedErrors = "Username must be at least 3 characters.;You must provide a valid email address.;Password must be at least 12 characters.";
    @Test
    public void registrationErrors(){
        loginPage.openLoginPage();
        loginPage
                .enterLoginRegistration("tr")
                .enterEmailRegistration("qqq")
                .enterPassWordRegistration("345")
                .checkErrorsMessages(expectedErrors);
    }
}
