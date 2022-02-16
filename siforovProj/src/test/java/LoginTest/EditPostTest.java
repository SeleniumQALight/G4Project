package LoginTest;

import baseTest.BaseTest;
import libs.TestData;
import org.junit.After;
import org.junit.Test;
import pages.ParentPageWithHeader;

public class EditPostTest extends BaseTest {
//Homework wit EditPost
    @Test
    public void editPostSuccessTest(){
        loginPage.openLoginPage();
        loginPage
                .loginWithValidCredentials()
                .clickOnProfileLink()
                .checkIsRedirectToProfilePage()
                .clickOnAvailablePost()
                .clickOnEditButton()
            //    .checkIsRedirectedToEditPage()
                .clearTitleAndUpdateItWithNewValue(TestData.VALID_POST_TITLE_AFTER_UPDATE)
                .clickOnSaveUpdatesButton()
                .checkIfUpdateSuccessTextDisplayed()
                .clickOnProfile()
                .checkIfUpdatedPostDisplayedInThePostsList();
    }

    @After
    public void recoverPost(){
        homePage
                .openHomePage()
                .checkIsButtonSignOutDisplayed()
                .clickOnProfileLink()
                .checkIsRedirectToProfilePage()
                .clickOnEditedPost()
                .clickOnEditButton()
                .clearTitleAndUpdateItWithNewValue(ParentPageWithHeader.postTitle)
                .clickOnSaveUpdatesButton();
    }

}
