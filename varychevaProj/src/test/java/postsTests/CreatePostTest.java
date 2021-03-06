package postsTests;

import baseTest.BaseTest;
import libs.Util;
import org.junit.Test;

public class CreatePostTest extends BaseTest {
    final String title = "G4-daryna" + Util.getDateAndTimeFormatted();

    @Test
    public void createNewPost() {
        loginPage
                .loginWithValidCred()
                .checkIsButtonSignOutDisplayed()
                .clickOnCreatePostButton();
        createPostPage
                .checkIsRedirectToCreatePostPage()
                .enterTextInToTitleInput(title)
                .enterTextInToBodyInput("Body post")
                .selectRoleInDropdownByText("Частное сообщение")
            //    .selectValueInDropDownRole("One Person")
                .setCheckboxValue("check")
                .clickOnButtonSaveNewPost()
                .checkIsRedirectToPostPage()
                .checkTextInAlert("New post successfully created.")
                .clickOnMyProfileButton()
                .checkPostWasCreated(title);
    }
}
