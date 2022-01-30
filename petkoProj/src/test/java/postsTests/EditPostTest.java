package postsTests;

import baseTest.BaseTest;
import libs.Util;
import org.junit.After;
import org.junit.Test;

public class EditPostTest extends BaseTest {

    final String title = "G4-Petko" + Util.getDateAndTimeFormatted();
    final String addTitle = "Edited";

    @Test
    public void EditPostTitle(){
        loginPage
                .loginWithValidCred()
                .checkIsButtonSignOutDisplayed()
                .clickOnCreatePostButton()
                .checkIsRedirectToCreatePostPage()
                .enterTextIntoTitleInput(title)
                .enterTextIntoBodyInput("Body post")
                .selectValueInDropDownRole("One Person")
                .clickOnButtonSaveNewPost()
                .checkIsRedirectToPostPage()
                .checkTextInAlert("New post successfully created.")
                .clickOnMyProfileButton()
                .checkPostWasCreated(title)
                .clickOnPostName(title)
                .clickOnEditButton()
                .checkIsRedirectToEditPostPage()
                .editTextInTitleInputAndSaveChanges(title + addTitle)
                .checkTextInAlert("Post successfully updated.")
                .clickOnLinkToPostPage()
                .checkIsRedirectToPostPage()
                .checkTextInTitle(title + addTitle);


    }

    @After
    public void deletePostAfterEditing(){
        homePage
                .openHomePage()
                .checkIsButtonSignOutDisplayed()
                .clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .deletePostWithTitleWhilePresent(title + addTitle);
    }


}
