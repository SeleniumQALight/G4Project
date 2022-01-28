package registrationTestWithPageObject;

import BaseTest.BaseTest;
import org.junit.Test;

public class registrationTestWithPageObject extends BaseTest {
    String expectedErrors = " Username must be at least 3 characters.;You must provide a valid email address.;Password must be at least 12 characters.";
@Test
public void registration(){
        loginPage.openLoginPage();
        loginPage
                .enterLoginRegistration("newuser")
                .enterEmailRegistration("test@ukr.net")
                .enterPasswordRegistration("111123445qwerty12345");

        homePage
                .checkIsButtonSignOutDisplayed();

}
    @Test
    public void registrationErrors(){
        loginPage.openLoginPage();
        loginPage
                .enterLoginRegistration("tr")
                .enterEmailRegistration("ydgewhf")
                .enterPasswordRegistration("hfg")
                .checkErrorsMessages(expectedErrors);


    }
}
