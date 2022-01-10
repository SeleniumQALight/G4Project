package signUpTests;

import baseTest.BaseTest;
import org.junit.Assert;
import org.junit.Test;

public class SignUpTest extends BaseTest {
    @Test
    public void signUpValidationMessages(){
        loginPage.openLoginPage();
        loginPage.enterLoginIntoInputLoginSignUp("tr");
        loginPage.enterEmailIntoInputEmailSignUp("test.com");
        loginPage.enterPassWordIntoInputPassWordSignUp("123");
        loginPage.clickOnButtonSignUp();

        Assert.assertTrue("Button SignIn is not displayed"
                , loginPage.isButtonSignInDisplayed());
        Assert.assertTrue("AlertInvalidLogin does not contain correct text"
                , loginPage.isTextInAlertInvalidLoginCorrect());
        Assert.assertTrue("AlertInvalidEmail does not contain correct text"
                ,loginPage.isTextInAlertInvalidEmailCorrect());
        Assert.assertTrue("AlertInvalidPassWord does not contain correct text"
                ,loginPage.isTextInAlertInvalidPassWordCorrect());
    }
}
