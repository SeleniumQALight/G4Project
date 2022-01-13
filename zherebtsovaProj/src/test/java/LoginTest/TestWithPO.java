package LoginTest;

import BaseTest.BaseTest;
import org.junit.Assert;
import org.junit.Test;

public class TestWithPO extends BaseTest {
        @Test
        public void validLoginTest() {
            loginPage.openLoginPage();
            loginPage.enterLoginIntoInputLogin("qaauto");
            loginPage.enterPassWordIntoInputPassWord("123456qwerty");
            loginPage.clickOnButtonSignIn();

            Assert.assertTrue("Button SignOut is not displayed", homePage.isButtonSignOutDisplayed());
        }


        @Test
        public void inValidLoginTest(){
            loginPage.openLoginPage();
            loginPage.enterLoginIntoInputLogin("qaauto1");
            loginPage.enterPassWordIntoInputPassWord("123456qwerty");
            loginPage.clickOnButtonSignIn();

            Assert.assertFalse("Button SignOut is not displayed", homePage.isButtonSignOutDisplayed());
            Assert.assertTrue("Error Field is not Displayed", loginPage.isErrorFieldDisplayed());

        }


    }


