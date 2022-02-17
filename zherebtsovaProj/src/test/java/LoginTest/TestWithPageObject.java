package LoginTest;

import BaseTest.BaseTest;
import category.SmokeTestFilter;
import io.qameta.allure.*;
import libs.ExcelDriver;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import pages.ParentPage;
import java.io.IOException;
import java.util.Map;
@Epic("Allure examples")
@Feature("Junit 4 support")

public class TestWithPageObject extends BaseTest {
    @Description("Some test description")
    @Link("https://example.org")
    @Link(name="allure", type="mylink")
    @Issue("123")
    @Issue("432")
    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Story("Base support BBD")
        @Category(SmokeTestFilter.class)
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


