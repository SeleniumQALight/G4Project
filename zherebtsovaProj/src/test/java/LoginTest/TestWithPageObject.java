package LoginTest;

import BaseTest.BaseTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import libs.ExcelDriver;
import org.junit.Assert;
import org.junit.Test;
import pages.ParentPage;
import org.junit.experimental.categories.Category;
import java.io.IOException;
import java.util.Map;
@Epic("Allure examples")
@Feature("Junit 4 support")
public class TestWithPageObject extends BaseTest {
        @Test
        //@Category(SmokeTestFilter.class)
        public void validLoginTest() {
            loginPage.openLoginPage();
            loginPage.enterLoginIntoInputLogin("qaauto");
            loginPage.enterPassWordIntoInputPassWord("123456qwerty");
            loginPage.clickOnButtonSignIn();

            Assert.assertTrue("Button SignOut is not displayed", homePage.isButtonSignOutDisplayed());
        }
    @Test
    public void validLoginTestWithExel() throws IOException {
        Map<String,String > dataForValidLogin = ExcelDriver.getData(ParentPage.configProperties.DATA_FILE(),"validLogOn");
        loginPage.openLoginPage();
        loginPage.enterLoginIntoInputLogin(dataForValidLogin.get ("login"));
        loginPage.enterPassWordIntoInputPassWord(dataForValidLogin.get("pass"));
        loginPage.clickOnButtonSignIn();

        checkER("Button SignOut is not displayed", homePage.isButtonSignOutDisplayed());
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


