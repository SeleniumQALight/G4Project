package postsTests;

import baseTest.BaseTest;
import libs.Util;
import org.junit.After;
import org.junit.Test;

public class EditPostTest extends BaseTest {
    final String title = "Text156 " + Util.getDateAndTimeFormatted();
    final String titleNew = title + " NEW30";
    final String body = "BodyText";

    @Test
    public void EditPostTest(){
        loginPage
                .loginWithValidCred()
                .checkIsButtonSignOutDisplayed()
                .clickOnCreatePostButton();
        createPostPage
                .checkIsRedirectToCreatePostPage()
                .entreTextInToTitleInput(title)
                .enterTextInBodyInput(body)
                .clickOnButtonSaveNewPost()
                .checkIsRedirectToPostPage()
                .checkTextInAlert("New post successfully created.")
                .clickOnMyProfileButton()
                .checkPostWasCreated(title)
                .clickOnPost(title)
                .checkOpenedPost(title)
                .clickOnEdit()
                .checkIsButtonSaveUpdates()
                .clickOnTitle()
                .cleanerTitleInPost(titleNew)
                .clickOnSaveUpdates()
                .checkTextInAlert("Post successfully updated.")
                .clickOnMyProfileButton()
                .checkPostWasUpdates(titleNew)
        ;
    }

    @After
    public void deletePost(){
        homePage
                .openHomePage()
                .checkIsButtonSignOutDisplayed()
                .clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .deletePostWithTitleWilePresent(titleNew)
        ;

    }
}
