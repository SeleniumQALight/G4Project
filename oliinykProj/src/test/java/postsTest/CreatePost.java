package postsTest;

import baseTest.BaseTest;
import org.junit.Test;


public class CreatePost extends BaseTest {
    @Test
    public void createNewPost(){
        loginPage.logedInHomepage()
                .checkIsButtonSingOutDisplayed()
                .clickOnCreatePostButton();
        createPostPage.checkRedirectToCreatePostPage()
                .enterTextIntoTitleInput("G4-Oliinyk")
                .enterTextIntoBodyInput("Test Auto")
                //.selectTextInDropDownRole("Частное сообщение")
                .selectValueInDropdownRole("One Person")
                .clickOnButtonSaveNewPost()
                .checkIsRedirectToPostPage()
                .checkTextInAlert("New post successfully created.");
    }
}
