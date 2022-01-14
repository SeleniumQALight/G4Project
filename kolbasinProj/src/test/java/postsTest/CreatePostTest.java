package postsTest;

import baseTest.BaseTest;
import libs.Util;
import org.junit.Test;

public class CreatePostTest extends BaseTest {
    final String title = "A1 - Kolbasin - " + Util.getDateAndTimeFormatted();

    @Test
    public void createNewPost(){

        loginPage
                .loginWithValidCred()
                .checkIsButtonSingOutDisplayed()
                .clickOnCreatePostButton();

        createPostPage
                .checkIsRedirectToCreatePostPage()
                .enterTextInToTitleInput(title)
                .enterTextInToBodyInput("jfjfh")
//                .selectTextInDropDownRole("Частное сообщение ")
                .selectValueInDropDownRole("One Person")
                .clickOnButtonSaveNewPost()
                .checkIsRedirectToPostPage()
                .checkTextInAlert("New post successfully created.")
                .clickOnMyProfileButton()
                .checkPostWasCreated(title)

        ;

    }
}
