package LoginTest;

import baseTest.BaseTest;
import libs.ExcelDriver;
import org.junit.Assert;
import org.junit.Test;
import pages.ParentPage;

import java.io.IOException;
import java.util.Map;

import static libs.ExcelDriver.getData;


public class LoginTestWithPageObject extends BaseTest {
    @Test
    public void validLoginTest() {
        loginPage.openLoginPage();
        loginPage.enterLoginIntoInputLogin("qaauto");
        loginPage.enterPassWordIntoInputPassWord("123456qwerty");
        loginPage.clickOnButtonSignIn();

        Assert.assertTrue("Button SignOut is not displayed", homePage.isButtonSingOutDisplayed());
    }

    @Test
    public void validLoginTestWithExcel() throws IOException {//тест с Ексель файлом !
        Map<String,String> dataForValidLogin =ExcelDriver.getData(ParentPage.configProperties.DATA_FILE(),"validLogOn");
        loginPage.openLoginPage();
        loginPage.enterLoginIntoInputLogin(dataForValidLogin.get("login"));
        loginPage.enterPassWordIntoInputPassWord(dataForValidLogin.get("pass"));
        loginPage.clickOnButtonSignIn();

        Assert.assertTrue("Button SignOut is not displayed", homePage.isButtonSingOutDisplayed());
    }


    @Test
    public void unValidLoginTest(){
        loginPage.openLoginPage();
        loginPage.enterLoginIntoInputLogin("qaautoO");
        loginPage.enterPassWordIntoInputPassWord("123456qwertyY");
        loginPage.clickOnButtonSignIn();

        Assert.assertFalse("Button SignOut is not displayed", homePage.isButtonSingOutDisplayed());
        Assert.assertTrue("Error Field is not Displayed", loginPage.isErrorFieldDisplayed());

    }
}
