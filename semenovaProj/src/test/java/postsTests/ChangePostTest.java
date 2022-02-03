package postsTests;

import baseTest.BaseTest;
import libs.Util;
import org.junit.After;
import org.junit.Test;

public class ChangePostTest extends BaseTest {
    final String title = "G4-Anna" + Util.getDateAndTimeFormatted();
    String changeTitle = title + 1;

    @Test
    public void changePost() {


        loginPage
                .loginWithValidCred()
                .checkIsButtonSignOutDisplayed()
                .clickOnCreatePostButton();
        createPostPage
                .checkIsRedirectToCratePostPage()
                .enterTextIntoTitleInput(title)
                .enterTextIntoBodyInput("Body post")
                //               .selectTextInDropDownRole("Частное сообщение")
                .selectValueInDropDownRole("One Person")
                .clickOnButtonSaveNewPost()
                .checkIsRedirectToPostPage()
                .checkTextInAlert("New post successfully created.")
                .clickOnMyProfileButton()
                .checkPostWasCreated(title)
                .enterToPost(title)
                .checkIsRedirectToPostPage()
                .clickOnEditButton()
                .changeTitlePost(changeTitle)
                .clickOnSaveUpdates()
                .checkTextInAlert("Post successfully updated.")
                .clickOnMyProfileButton()
                .checkPostWasCreated(changeTitle);

    }

    @After
    public void deleteChangePost() {
        homePage
                .openHomePage()
                .checkIsButtonSignOutDisplayed()
                .clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .deletePostWithTitleWhilePresent(changeTitle);
    }
}
