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
        loginPage.isDivTextErrorLoginDisplayed("Username must be at least 3 characters. ");
        loginPage.isDivTextErrorEmailDisplayed("You must provide a valid email address.");
        loginPage.isDivTextErrorPasswordDisplayed("Password must be at least 12 characters.");

        //Assert.assertTrue("Username must be at least 3 characters is not displayed", loginPage.isDivTextErrorLoginDisplayed());

    }

}
