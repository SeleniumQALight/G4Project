package postsTest;

import baseTest.BaseTest;
import org.junit.Test;

public class CreatePostTest extends BaseTest {
    @Test
    public void createNewPost(){
        loginPage
                .loginWithValidCred()
                .checkIsButtonSingOutDisplayed()
                .clickOnCreatePostButton();

        createPostPage
                .checkIsRedirectToCreatePostPage()
                .enterTextInToTitleInput("A1-Kolbasin")
                .enterTextInToBodyInput("jfjfh")
//                .selectTextInDropDownRole("Частное сообщение ")
                .selectValueInDropDownRole("One Person")
                .clickOnButtonSaveNewPost()
                .checkIsRedirectToPostPage()
                .checkTextInAlert("New post successfully created.")
        ;

    }
}
