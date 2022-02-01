package registrationTestHW3;

import baseTest.BaseTest;
import org.junit.Test;

public class RegistrationTest extends BaseTest {

    @Test
    public void notValidRegistration() {

        loginPage.openLoginPage();

        registrationPageHW3
                .checkIsInputUsernameRegisterDisplayed()
                .checkIsInputEmailRegisterDisplayed()
                .checkIsInputPasswordRegisterDisplayed()

                .enterTextIntoUsernameRegisterInput("tr")
                .enterTextIntoEmailRegisterInput("test.com")
                .enterTextIntoPasswordRegisterInput("123")

                .clickOnButtonSingOurApp()
                .checkIsInvalidMassageEmailDisplayed()
                .checkIsInvalidMassagePasswordDisplayed()
                .checkIsInvalidMassageUsernameDisplayed();

    }
}
