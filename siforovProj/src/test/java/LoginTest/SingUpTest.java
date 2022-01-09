package LoginTest;

import baseTest.BaseTest;
import libs.TestData;
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

    //Homework1

    // To be updated since Thread Sleep is a really bad approach.
    //Next time I will update this with the Explicit waiter
    @Test
    public void validSignUpTest() throws InterruptedException{
        loginPage.openLoginPage();
        loginPage.enterUsernameIntoInputUsernameSignUpForm(TestData.VALID_USERNAME);
        loginPage.enterEmailIntiEmailInputSignUpForm(TestData.VALID_EMAIL);
        loginPage.enterPasswordIntoPasswordInputSignUpForm(TestData.VALID_SIGN_UP_PASSWORD);
        Thread.sleep(2000);
        loginPage.clickOnSignUpButton();

        Assert.assertTrue("User hasn't been registered", homePage.isButtonSignOutDisplayed());
    }
}
