package postsTests;

import baseTest.BaseTest;
import org.junit.Test;

public class CreatePostTest extends BaseTest {

    @Test
    public void createNewPost(){
        loginPage
                .loginWithValidaCred()
                .checkIsButtonSignOutDisplayed()
                .clickOnCreatePostButton()
                .checkIsRedirectToCreatePostPage()
                .enterTextInToTitleInput("G4-Nadia")
                .enterTextInToBodyInput("New post text")
            //    .selectTextInToDropDownRole("Частное сообщение")
                .selectValueInProdDownRole("One Person")
                .clickOnButtonSaveNewPost()
                .checkIsRedirectPostPage()
                .checkTextInAlert("New post successfully created.");
    }
}
