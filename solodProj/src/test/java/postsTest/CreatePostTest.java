package postsTest;

import baseTest.BaseTest;
import libs.Util;
import org.junit.Test;

public class CreatePostTest  extends BaseTest {
    final String title="G4-artem " + Util.getDateAndTimeFormatted();

    @Test
    public void createNewPost(){


        loginPage
                .loginWithValidCred()
                .checkIsButtonSignOutDisplayed()
                .clickOnCreatePostButton();

        createPostPage
                .checkIsRedirectToCreatePostPage()
                .enterTextInToTitleInput(title)
                .enterTextInToBodyInput("Body post")
//                .selectTextInDropDownRole("Частное сообщение");
                .selectValueInDropDownRole("One Person")
                .clickOnButtonSavePost()
                .checkIsRedirectToPostPage()
                .checkIsTextInAlert("New post successfully created.")
                .clickOnMyProfileButton()
                .checkPostWasCreate(title)
        ;


    }

}
