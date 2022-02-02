package postsTests;

import baseTest.BaseTest;
import libs.Util;
import org.junit.After;
import org.junit.Test;

public class CreatePostTest extends BaseTest {
    final String title = "G4- Yulia-01-13"+ Util.getDateAndTimeFormatted();
    @Test
    public void createNewPost() {

        loginPage
                .loginWithValidCred()
                .checkIsButtonSingOutDisplayed()
                .clickOnCreatePostButton();
        createPostPage
                .checkIsRedirectToCreatePostPage()
                .enterTextInToTitleInput(title)
                .enterTextInToBodyInput("Body role")
                //           .selectTextInDropDownRole("")
                .selectvalueInDropDownRole("One Person")
                .clickOnButtonSaveNewPost()
                .checkIsRedirectToPostPage()
                .checkTextInAlter("New post successfully created.")
                .clickOfMyProfileButton()
                .checkPostWasCreated(title)
        ;
    }
    @After
    public void deletePost(){
        homePage
                .openHomePage()
                .checkIsButtonSingOutDisplayed()
                .clickOfMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .deletePostWithTitleWhilePresent(title)

        ;
    }
}
