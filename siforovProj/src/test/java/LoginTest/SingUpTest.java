package LoginTest;

import baseTest.BaseTest;
import org.junit.Assert;
import org.junit.Test;

public class SingUpTest extends BaseTest {
    //Homework3
    @Test
    public void invalidSignUpTest(){
        loginPage.openLoginPage();
        loginPage.enterUsernameIntoInputUsernameSignUpForm("tr");
        loginPage.enterEmailIntiEmailInputSignUpForm("test.com");
        loginPage.enterPasswordIntoPasswordInputSignUpForm("123");
        loginPage.clickOnSignUpButton();

        Assert.assertTrue("User was registered", loginPage.signUpButtonIsVisible());
        Assert.assertTrue("User name validation text isn't displayed", loginPage.userNameAlertTextIsVisible());
        Assert.assertTrue("Email validation text isn't displayed", loginPage.emailAlertTextIsVisible());
        Assert.assertTrue("Password validation text isn't displayed", loginPage.passwordAlertTextIsVisible());

    }
}
