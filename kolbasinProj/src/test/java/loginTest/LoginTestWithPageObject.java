package loginTest;

import baseTest.BaseTest;
import io.qameta.allure.*;
import categories.SmokeTestFilter;
import libs.ExcelDriver;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import pages.ParentPage;

import java.io.IOException;
import java.util.Map;

import static pages.ParentPage.configProperties;

@Epic("Allure examples")
@Feature("Junit 4 support")
public class LoginTestWithPageObject extends BaseTest {
    @Description("Some detailed test description")
    @Link("https://example.org")
    @Link(name = "allure", type = "mylink")
    @Issue("123")
    @Issue("432")
    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Story("Base support for bdd annotations")
//    @Ignore
    @Category(SmokeTestFilter.class)
    public void validLoginTest(){
    loginPage.openLoginPage();
    loginPage.enterLoginIntoInputLogin("qaauto");
    loginPage.enterPassWordIntoInputPassWord("123456qwerty");
    loginPage.clickOnButtonSignIn();

    checkER("Button SingOut is not displayed"  // отработает только тогда, когда появится кнопка
            , homePage.isButtonSignOutDisplayed());
    }
    @Test
    public void validLoginTestWithExel() throws IOException {
        Map<String,String> dataForValidLogin = ExcelDriver.getData(configProperties.DATA_FILE(),"validLogOn");
    loginPage.openLoginPage();
    loginPage.enterLoginIntoInputLogin(dataForValidLogin.get("login"));
    loginPage.enterPassWordIntoInputPassWord(dataForValidLogin.get("pass"));
    loginPage.clickOnButtonSignIn();

    Assert.assertTrue("Button SingOut is not displayed"  // отработает только тогда, когда появится кнопка
            , homePage.isButtonSignOutDisplayed());
    }

}
