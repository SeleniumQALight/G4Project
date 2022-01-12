package postsTests;

import baseTest.BaseTest;
import org.junit.Test;

public class CreatePostTest extends BaseTest {
    @Test
    public void createNewPost() {
        loginPage
                .loginWithValidCred()
                .checkIsButtonSignOutDisplayed()
                .clickOnCreatePostButton();
        createPostPage
                .checkIsRedirectToCratePostPage()
                .enterTextIntoTitleInput("G4-Anna")
                .enterTextIntoBodyInput("Body post")
                //               .selectTextInDropDownRole("Частное сообщение")
                .selectValueInDropDownRole("One Person")
                .clickOnButtonSaveNewPost()
                .checkIsRedirectToPostPage()
                .checkTextInAlert("New post successfully created.")
        ;


    }
}
