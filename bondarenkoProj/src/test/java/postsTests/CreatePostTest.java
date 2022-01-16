package postsTests;

import baseTest.BaseTest;
import libs.Util;
import org.junit.Test;

public class CreatePostTest extends BaseTest {
   final String title = "G4-bondarenko" + Util.getDateAndTimeFormatted();


    @Test
    public  void createNewPost(){

        loginPage
                .loginWithValidCred()
                .checkIsButtonSignOutDisplayed()
                .clickOnCreatePostButton();

        createPostPage.checkIsRedirectToCreatePostPage()
                .checkIsRedirectToCreatePostPage()
                .enterTextIntoTitleInput(title)
                .enterTextIntoBodyInput("Body post")
   //             .selectTextInDropDownRole("Частное сообщение")
                //.selectValueInDropDownRole("One Person")
                .selectTextInDropDownRoleByUI()
                .clickOnButtonSaveNewPost()
                .checkIsRedirectToPostPage()
                .checkTextInAlert("New post successfully created.")
                .clickOnMyProfileButton()
                .checkPostWasCreated(title)

        ;
    }
}
