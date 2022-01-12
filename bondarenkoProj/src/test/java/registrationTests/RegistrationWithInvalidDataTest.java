package registrationTests;

import baseTest.BaseTest;
import org.junit.Test;

public class RegistrationWithInvalidDataTest extends BaseTest {
    @Test
    public  void enterInvalidData(){
        loginPage.openLoginPage();
        loginPage.enterUsernameIntoInputUsernameSignUp("tr");
        loginPage.enterEmailIntoInputEmailSignUp("test.com");
        loginPage.enterPasswordIntoInputPasswordSignUp("123");
        loginPage.clickOnButtonSignUpForOurApp();
        loginPage.checkIsMessageForUsernameSignUpFieldDisplayed();
        loginPage.checkIsMessageForEmailSignUpFieldDisplayed();
        loginPage.checkIsMessageForPasswordSignUpFieldDisplayed();
    }
}
