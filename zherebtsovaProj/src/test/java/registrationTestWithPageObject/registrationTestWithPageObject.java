package registrationTestWithPageObject;
import baseTest.BaseTest;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
//@Category(SmokeTestFilter.class)
public class registrationTestWithPageObject extends BaseTest {

    String expectedErrors = " Username must be at least 3 characters.;You must provide a valid email address.;Password must be at least 12 characters.";
    @Parameters({
            "tr,qqq,345,Username must be at least 3 characters.;You must provide a valid email address.;Password must be at least 12 characters."
            ,"tr,test,123456qwerty,Username must be at least 3 characters.;You must provide a valid email address."
    })

    @Test
    @TestCaseName("registrationErrors : login = {0}, email = {1}, password = {2}")
    public void registrationErrors(String login, String email, String password, String expectedErrors){
        loginPage.openLoginPage();
        loginPage
                .enterLoginRegistration(login)
                .enterEmailRegistration(email)
                .enterPasswordRegistration(password)
                .checkErrorsMessages(expectedErrors);
    }
    @Test
    public void registration() {
        loginPage.openLoginPage();
        loginPage
                .enterLoginRegistration("jdfjfgh")
                .enterEmailRegistration("test12@ukr.net")
                .enterPasswordRegistration("111123445qwerty12345")
                .clickOnButtonSignUp();
        homePage
                .checkIsButtonSignOutDisplayed();
    }
    }
