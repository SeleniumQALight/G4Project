package postsTests;

import baseTest.BaseTest;
import org.junit.Test;

public class CreatePostTest extends BaseTest {
    @Test
    public void createNewPost() {
        loginPage
                .loginWithValidCred()
                .checkIsButtonSingOutDisplayed()
                .clickOnCreatePostButton();
        createPostPage
                .checkIsRedirectToCreatePostPage()
                .enterTextInToTitleInput("G4-Kachan")
                .enterTextInToBodyInput("Body role")
   //           .selectTextInDropDownRole("")
                .selectvalueInDropDownRole("One Person")
                .clickOnButtonSaveNewPost()
                .checkTextInAlter("New post successfully created.");


    }
}
