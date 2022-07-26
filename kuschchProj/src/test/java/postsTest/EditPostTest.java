package postsTest;

import baseTest.BaseTest;
import libs.Util;
import org.junit.After;
import org.junit.Test;

public class EditPostTest extends BaseTest {

    final String title = "G4-kuschch: " + Util.getDateAndTimeFormatted();
    final String editTitleText = title + "title edited";

    @Test
    public void editPost(){
        loginPage
                .loginWithValidCred()
                .checkIsButtonSignOutDisplayed()
                .clickOnCreatePostButton()
                .checkIsRedirectToCreatePostPage()
                .enterTextIntoTitleInput(title)
                .enterTextIntoBody("New post text")
                .clickOnButtonSaveNewPost()
                .checkIsRedirectToPostPage()
                .checkTestInAlert("New post successfully created.")
                .clickOnEditBtn()
                .checkIsRedirectToEditPostPage()
                .enterTextInToTitle(editTitleText)
                .clickOnBtnUpdateSavePost()
                .checkTextInAlert("Post successfully updated.")
                .clickOnMyProfileButton()
                .checkPostWasCreated(editTitleText);
    }

    @After
    public void deletePost() {
        homePage
                .openHomePage()
                .checkIsButtonSignOutDisplayed()
                .clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .deletePostWithTitleWhilePresent(editTitleText);

    }

}
