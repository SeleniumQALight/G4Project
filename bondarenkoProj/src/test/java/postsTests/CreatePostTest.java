package postsTests;

import baseTest.BaseTest;
import org.junit.Test;

public class CreatePostTest extends BaseTest {
    @Test
    public  void createNewPost(){
        loginPage
                .loginWithValidCred()
                .checkIsButtonSignOutDisplayed()
                .clickOnCreatePostButton();

        createPostPage.checkIsRedirectToCreatePostPage()
                .checkIsRedirectToCreatePostPage()
                .enterTextIntoTitleInput("G4-nastya")
                .enterTextIntoBodyInput("Body post")
   //             .selectTextInDropDownRole("Частное сообщение")
                .selectValueInDropDownRole("One Person")
                .clickOnButtonSaveNewPost()
                .checkIsRedirectToPostPage()
                .checkTextInAlert("New post successfully created.")

        ;
    }
}
