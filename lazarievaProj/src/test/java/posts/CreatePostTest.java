package posts;

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
                .checkIsRedirectedToCreatePostPage()
                .enterTextinToTitleInput("G4-Marigold")
                .enterTextInToBodyInput("text for body")
                //.selectTextInDropDownRole("Частное сообщение")
                .selectValueInDropDown("One Person")
                .clickOntButtonSaveNewPOst()
                .checkIsRedirectToPostPage()
                .checkTextInAlert("New post successfully created.");

    }
}
