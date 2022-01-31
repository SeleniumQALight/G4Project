package LoginTest;

import baseTest.BaseTest;
import org.junit.Test;

public class EditPostTest extends BaseTest {

    @Test
    public void editPostSuccessTest(){
        loginPage.openLoginPage();
        loginPage
                .loginWithValidCredentials()
                .clickOnProfileLink()
                .checkIsRedirectToProfilePage()
                .clickOnAnyAvailablePost()
                .clickOnEditButton()
                .checkIsRedirectedToEditPage()
                .clearTitleAndUpdateItWithNewValue();
    }
}
