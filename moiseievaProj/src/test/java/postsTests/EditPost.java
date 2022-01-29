package postsTests;

import baseTest.BaseTest;
import libs.Util;
import org.junit.After;
import org.junit.Test;

public class EditPost extends BaseTest {

    final String title = "G4-Nadia-" + Util.getDateAndTimeFormatted();
    final String editTitle = title + "-was_edit";

    @Test
    public void editPost(){
        loginPage
                .loginWithValidaCred()
                .checkIsButtonSignOutDisplayed()
                .clickOnCreatePostButton()
                .checkIsRedirectToCreatePostPage()
                .enterTextInToTitleInput(title)
                .enterTextInToBodyInput("New post text")
                .selectOptionByTextAtDropDown("Частное сообщение")
                .clickOnButtonSaveNewPost()
                .checkIsRedirectPostPage()
                .checkTextInAlert("New post successfully created.")
                .clickOnEditButton()
                .checkIsRedirectToEditPostPage()
                .enterTextInToTitleInput(editTitle)
                .clickOnButtonUpdateSavePost()
                .checkTextInAlert("Post successfully updated.")
                .clickOnMyProfileButton()
                .checkIsPostTitleCorrect(editTitle)
                ;
    }

    @After
    public void deletePost() {
        homePage
                .openHomePage()
                .checkIsButtonSignOutDisplayed()
                .clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .deletePostWithTitleWhilePresent(editTitle)
        ;
    }
}
