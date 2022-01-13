package postsTests;

import baseTest.BaseTest;
import libs.Util;
import org.junit.Test;

public class CreatePostTest  extends BaseTest{
   final String title  = "G4-OlhaM 1-13" + Util.getDateAndTimeFormatted();
    @Test
    public  void createNewPost(){


        loginPage.loginWithValidCred().checkIsButtonSignOutDisplayed()
                .clickOnCreatePostButton();
        createPostPage.checkIsredirectToCreatePostPage()
                .enterTextInToTitleInput(title)
                .enterTextInToBodyInput("Body post")
                // .selectTextInDropDownRole("Частное сообщение")
                .selectValueInDropDownRole("One Person")
                .clickOnButtonSaveNewPost()
                .checkIsRedirectToPostPage()
                .checkTextInAlert("New post successfully created.")
        .clickOnMyProfileButton()
        . checkPostWasCreated(title);
    }


}
