package RegistrTest;

import baseTest.BaseTest;
import org.junit.Assert;
import org.junit.Test;

public class RegistrTestWithPageObject extends BaseTest {
    @Test
    public void signUpFieldValidationAlerts(){
        loginPage.openLoginPage();
        loginPage.enterLoginInputIntoUserNameRegiste("tr");
        loginPage.enterEmailIntoInputEmail("test.com");
        loginPage.enterPassWordIntoInputPassWordRegister("123");
        loginPage.clickOnButtonSignUpForOurApp();
        Assert.assertTrue("Error text does not match", loginPage.isDivTextErrorLoginDisplayed());
        Assert.assertTrue("Error text does not match", loginPage.isDivTextErrorEmailDisplayed());
        Assert.assertTrue("Error text does not match", loginPage.isDivTextErrorPasswordDisplayed());

        //Assert.assertTrue("Username must be at least 3 characters is not displayed", loginPage.isDivTextErrorLoginDisplayed());
    }

}
