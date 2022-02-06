package postsTests;

import baseTest.BaseTest;
import libs.Util;
import org.junit.After;
import org.junit.Test;

public class EditPostTest extends BaseTest {
    final String title = "G4-gnatyshko 1-13" + Util.getDateAndTimeFormatted();
    final String editedTitle = title + "edit";

    @Test
    public void editPost(){
        loginPage
                .loginWithValidCredentials()
                .checkIsButtonSignOutDisplayed()
                .clickOnCreatePostButton();
        createPostPage
                .checkIsRedirectToCreatePostPage()
                .enterTextIntoTitleInput(title)
                .enterTextIntoBodyInput("Body post")
                .checkCheckbox("CHECK")
                .selectTextInDropDownByUi("Частное сообщение")
                .clickOnButtonSaveNewPost()
                .checkIsRedirectToPostPage()
                .checkTextInAlert("New post successfully created.")
                .clickOnMyProfileButton()
                .checkPostWasCreated(title)
                .clickOnCreatedPost(title);
        postPage
                .checkIsRedirectToPostPage()
                .clickEditPost()
                ;
        editPostPage
                .editPostTitle(editedTitle)
                .clickOnButtonSaveEditedPost()
                .checkTextInAlert("Post successfully updated.")
                .clickOnMyProfileButton()
                .checkPostWasEdited(editedTitle)
                ;
    }

    @After
    public void deletePost(){
        homePage.openHomePage()
                .checkIsButtonSignOutDisplayed()
                .clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .deletePostWithTitleWhilePresent(editedTitle)
        ;
    }

}
