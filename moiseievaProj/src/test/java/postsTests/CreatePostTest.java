package postsTests;

import baseTest.BaseTest;
import libs.Util;
import org.junit.Test;

public class CreatePostTest extends BaseTest {

    final String title = "G4-Nadia-" + Util.getDateAndTimeFormatted();

    @Test
    public void createNewPost() {
        loginPage
                .loginWithValidaCred()
                .checkIsButtonSignOutDisplayed()
                .clickOnCreatePostButton()
                .checkIsRedirectToCreatePostPage()
                .enterTextInToTitleInput(title)
                .enterTextInToBodyInput("New post text")
                // .selectTextInToDropDownRole("Частное сообщение")
                // .selectValueInProdDownRole("One Person")
                .selectCheckbox()
                .selectOptionByTextAtDropDown("Частное сообщение")
                .clickOnButtonSaveNewPost()
                .checkIsRedirectPostPage()
                .checkTextInAlert("New post successfully created.")
                .clickOnMyProfileButton()
                .checkPostWasCreated(title)
        ;
    }
}
