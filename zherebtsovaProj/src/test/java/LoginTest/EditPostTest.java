package LoginTest;

import BaseTest.BaseTest;
import libs.Util;
import org.junit.After;
import org.junit.Test;
import pages.CreatePostPage;

public class EditPostTest extends BaseTest {
    final String title = "G4-Zherebtsova" + Util.getDateAndTimeFormatted();
    final String newTitle = "New valid Title 123!@#$^";

    @Test
    public void editNewPost() {
        loginPage
                .loginWithValidCred()
                .checkIsButtonSignOutDisplayed()
                .clickButtonCreatePost();

        createPostPage
                .checkIsRedirectToCreatePostPage()
                .enterTextInToTitleInput(title)
                .enterTextInToBodyInput("28-09-TomCat")
                .selectTextInDropDownByUI()
                .selectValueInCheckBox()
                .clickOnButtonSaveNewPost()
                .checkTextInAlert("New post successfully created.")
                .checkIsRedirectToPostPage()
                .clickOnMyProfileButton()
                .checkPostWasCreated(title);
        createPostPage
                .clickOnOldTitle()
                .clickOnEdit()
                .enterNewTextInToTitleInput(newTitle)
                .clickOnSaveUpdatesButton()
                .checkTextInUpdateAlert("Post successfully updated.")
                .clickOnMyProfileButton()
                .checkPostWasCreated(newTitle);

    }


    @After
    public void deletePost() {
        homePage
                .openHomePage()
                .checkIsButtonSignOutDisplayed()
                .clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .deletePostWithTitleWhilePresent(newTitle);


    }
}


