package postsTests;

import org.junit.Test;

import baseTest.BaseTest;

public class CreatePostTest extends BaseTest {
    @Test
    public void createNewPost(){
        loginPage
                .loginWithValidCred()
              .checkIsButtonSignOutDisplayed()
                .clickOnCreatePostButton();
        createPostPage
                .checkIsRedirectToCreatePostPage()
                .enterTextInToTitleInput("G4-taras")
                .enterTextInToBodyInput("Body post")
//                .selectTextInDropDownRole("Частное сообщение")
                .selectValueInDropDownRole("One Person")
                .clickOnButtonSaveNewPost()
                .checkIsRedirectToPostPage()
                .checkTextInAlert("New post successfully created.")
        ;
    }
}
