package RegistrTest;

import baseTest.BaseTest;
import org.junit.Assert;
import org.junit.Test;

public class RegistrTestWithPageObject extends BaseTest {
    @Test
    public void validRegistrTest(){
        loginPage.openLoginPage();
        loginPage.enterLoginInputIntoUserNameRegiste("tr");
        loginPage.enterEmailIntoInputEmail("test.com");
        loginPage.enterPassWordIntoInputPassWordRegister("123");
        loginPage.clickOnButtonSignUpForOurApp();

        Assert.assertTrue("Username must be at least 3 characters is not displayed", loginPage.isDivTextErrorLoginDisplayed());
        Assert.assertTrue("You must provide a valid email address is not displayed ", loginPage.isDivTextErrorEmailDisplayed());
        Assert.assertTrue("Password must be at least 12 characters is not displayed ", loginPage.isDivTextErrorPasswordDisplayed());
    }

}
