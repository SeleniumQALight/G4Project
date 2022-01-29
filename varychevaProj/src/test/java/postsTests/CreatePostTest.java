package postsTests;

import baseTest.BaseTest;
import org.junit.Test;

public class CreatePostTest extends BaseTest {

    @Test
    public void createNewPost() {
        loginPage
                .loginWithValidCred()
                .checkIsButtonSignOutDisplayed();
        createPostPage
                .checkIsRedirectToCreatePostPage()
                .enterTextInToTitleInput("Test Title 12345")
                .enterTextInToBodyInput("Body post")
//                .selectTextInDropDownRole("Частное сообщение")
                .selectValueInDropDownRole("One Person")
                .clickOnButtonSaveNewPost()
                .checkIsRedirectToPostPage()
                .checkTextInAlert("New post successfully created.");
    }
}
