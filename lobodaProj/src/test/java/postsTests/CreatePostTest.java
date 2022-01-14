package postsTests;

import baseTest.BaseTest;
import libs.Util;
import org.junit.Test;

public class CreatePostTest extends BaseTest {
    final String title = "G4-loboda " + Util.getDateAndTimeFormatted();

    @Test
    public void createNewPost(){
        loginPage
                .loginWithValidCred()
                .checkIsButtonSignOutDisplayed()
                .clickOnCreatePostButton()
                .checkIsRedirectToCreatePostPage()
                .enterTextInToTitleInput(title)
                .enterTextInToBodyInput("G4-loboda-body")
//                .selectTextInDropDownRole("Частное сообщение")
                .selectValueInDropDownRole("One Person")
                .clickOnButtonSaveNewPost()
                .checkIsRedirectToPostPage()
                .checkTextInAlert("New post successfully created.")
                .clickOnMyProfileButton()
                .checkPostWasCreated(title)
        ;

    }
}
