package registrationPage;

import baseTest.BaseTest;
import org.junit.Test;

public class RegistrationTestWithPageOblect extends BaseTest {
    String expectedErrors ="Username must be at least 3 characters.;You must provide a valid email address.;Password must be at least 12 characters.";
    @Test
    public void registrationErrors (){
        loginPage.openLoginPage();
        loginPage
                .enterLoginRegistration("tr")
                .enterEmailRegistration("qqq")
                .enterPasswordRegistration("345")
                .cheackErrorsMessages(expectedErrors);
    }
}
