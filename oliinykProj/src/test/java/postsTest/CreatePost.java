package postsTest;

import baseTest.BaseTest;
import libs.Util;
import org.junit.After;
import org.junit.Test;


public class CreatePost extends BaseTest {
    final String title = "G4-Oliinyk " + Util.getDateAndTimeFormatted();

    @Test
    public void createNewPost(){
        loginPage.logedInHomepage()
                .checkIsButtonSingOutDisplayed()
                .clickOnCreatePostButton();
        createPostPage.checkRedirectToCreatePostPage()
                .enterTextIntoTitleInput(title)
                .enterTextIntoBodyInput("Test Auto")
                //.selectTextInDropDownRole("Частное сообщение")
                .selectValueInDropdownRole("One Person")
                .clickOnButtonSaveNewPost()
                .checkIsRedirectToPostPage()
                .checkTextInAlert("New post successfully created.")
                .clickOnMyProfileButton()
                .checkPostWasCreated(title);
    }

    @After
    public void deleteCreatedPost(){
        homePage.openHomePage()
                .checkIsButtonSingOutDisplayed()
                .clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .deletePostWithTitleWhilePresent(title);
    }

    @Test
    public void createNewPostByUIDropdown(){
        loginPage.logedInHomepage()
                .checkIsButtonSingOutDisplayed()
                .clickOnCreatePostButton();
        createPostPage.checkRedirectToCreatePostPage()
                .enterTextIntoTitleInput("G4-Oliinyk UI")
                .enterTextIntoBodyInput("Test Auto UI")
                //.selectTextInDropDownRole("Частное сообщение")
                .selectTextInDropDownByUI()
                .clickOnButtonSaveNewPost()
                .checkIsRedirectToPostPage()
                .checkTextInAlert("New post successfully created.");
    }
}
