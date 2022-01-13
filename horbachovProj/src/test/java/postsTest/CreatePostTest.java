package postsTest;

import baseTest.BaseTest;
import libs.Util;
import org.junit.Test;

public class CreatePostTest extends BaseTest {
    final String title = "G4-horbachov" + Util.getDateAndTimeFormatted();
    @Test
    public void createNewPost(){

        loginPage
                .loginWithValidCred()
                .checkIsbuttonSignOutDisplayed()
                .clickOnCreatePostButton();
        createPostPage
                .checkIsRedirectToCreatePostPage()
                .enterTextIntoTitleInput(title)
                .enterTextIntoBodyInput("Body post")
 //               .selectTextInDropDownRole("Частное сообщение")
                .selectValueInDropDownRole("One Person")
                .clickOnButtonSaveNewPost()
                .checkIsRedirectToPostPage()
                .checkTextInAlert("New post successfully created.")
                .clickOnMyProfileButton()
                .checkPostWasCreated(title);


    }
}
