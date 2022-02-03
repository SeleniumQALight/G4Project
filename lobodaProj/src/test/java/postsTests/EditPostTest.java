package postsTests;

import libs.Util;
import org.junit.Test;

public class EditPostTest extends BasePostTest{

    @Test
    public void editPost(){
        title = "G4-loboda " + Util.getDateAndTimeFormatted();
        loginPage
                .loginWithValidCred()
                .checkIsButtonSignOutDisplayed()
                .clickOnCreatePostButton()
                .checkIsRedirectToCreatePostPage()
                .enterTextInToTitleInput(title)
                .enterTextInToBodyInput("G4-loboda-body")
                .setStateForCheckboxUniquePost("check")
                .selectTextByUIInDropDownRole("Частное сообщение")
                .clickOnButtonSaveNewPost()
                .checkIsRedirectToPostPage()
                .checkTextInAlert("New post successfully created.")
                .clickOnMyProfileButton()
                .checkPostIsPresentOnMyProfilePage(title)
                .openPostDetails(title)
                .clickOnEditButton()
                .checkIsRedirectToEditPostPage()
                .enterTextInToTitleInput(title = "Updated " + title)
                .clickOnButtonSaveUpdate()
                .checkIsAlertSuccessPostUpdatedDisplayed()
                .clickOnMyProfileButton()
                .checkPostIsPresentOnMyProfilePage(title)
        ;


    }
}
