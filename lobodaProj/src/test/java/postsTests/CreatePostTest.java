package postsTests;

import libs.Util;
import org.junit.Test;

public class CreatePostTest extends BasePostTest {

    @Test
    public void createNewPost(){
        title = "G4-loboda " + Util.getDateAndTimeFormatted();
        loginPage
                .loginWithValidCred()
                .checkIsButtonSignOutDisplayed()
                .clickOnCreatePostButton()
                .checkIsRedirectToCreatePostPage()
                .enterTextInToTitleInput(title)
                .enterTextInToBodyInput("G4-loboda-body")
                .setStateForCheckboxUniquePost("check")
//                .selectTextInDropDownRole("Частное сообщение")
//                .selectValueInDropDownRole("One Person")
                .selectTextByUIInDropDownRole("Частное сообщение")
                .clickOnButtonSaveNewPost()
                .checkIsRedirectToPostPage()
                .checkTextInAlert("New post successfully created.")
                .clickOnMyProfileButton()
                .checkPostIsPresentOnMyProfilePage(title)
        ;

    }
}
