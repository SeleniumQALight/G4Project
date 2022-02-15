package loginTest;

import baseTest.BaseTest;
import io.qameta.allure.*;
import org.junit.Assert;
import org.junit.Test;

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
    public void validLoginTest(){
    loginPage.openLoginPage();
    loginPage.enterLoginIntoInputLogin("qaauto");
    loginPage.enterPassWordIntoInputPassWord("123456qwerty");
    loginPage.clickOnButtonSignIn();

    checkER("Button SingOut is not displayed"  // отработает только тогда, когда появится кнопка
            , homePage.isButtonSignOutDisplayed());
    }
}
