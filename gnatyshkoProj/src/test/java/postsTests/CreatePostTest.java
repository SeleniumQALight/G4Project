package postsTests;

import baseTest.BaseTest;
import libs.Util;
import org.junit.Test;

public class CreatePostTest extends BaseTest {
    final String title = "G4-gnatyshko 1-13" + Util.getDateAndTimeFormatted();

    @Test
    public void createNewPost() {
        loginPage
                .loginWithValidCredentials()
                .checkIsButtonSignOutDisplayed()
                .clickOnCreatePostButton();

        createPostPage
                .checkIsRedirectToCreatePostPage()
                .enterTextIntoTitleInput(title)
                .enterTextIntoBodyInput("Body post")
                .clickOnCheckBox()
//                .selectTextInDropDownRole("Частное сообщение")
//                .selectValueInDropDownRole("One Person")
                .selectTextInDropDownByUi("Частное сообщение")
                .clickOnButtonSaveNewPost()
                .checkIsRedirectToPostPage()
                .checkTextInAlert("New post successfully created.")
                .clickOnMyProfileButton()
                .checkPostWasCreated(title)
        ;
    }
}
