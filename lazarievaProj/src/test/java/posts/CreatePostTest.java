package posts;

import baseTest.BaseTest;
import libs.Util;
import org.junit.Test;

public class CreatePostTest extends BaseTest {
    final String title = "G4-Marigold " + Util.getDateAndTimeFormatted();

    @Test
    public void createNewPost() {


        loginPage
                .loginWithValidCred()
                .checkIsButtonSignOutDisplayed()
                .clickOnCreatePostButton()
                //;
                // createPostPage
                .checkIsRedirectedToCreatePostPage()
                .enterTextinToTitleInput(title)
                .enterTextInToBodyInput("text for body")
                //.selectTextInDropDownRole("Частное сообщение")
                .selectValueInDropDown("One Person")
                .clickOntButtonSaveNewPOst()
                .checkIsRedirectToPostPage()
                .checkTextInAlert("New post successfully created.")
                .clickOnMyProfile()
                .checkPostWasCreated(title)

        ;


    }
}
