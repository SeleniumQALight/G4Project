package postsTests;

import baseTest.BaseTest;
import libs.Util;
import org.junit.After;
import org.junit.Test;

public class UpdatePostTitleTest extends BaseTest {
    final String title = "G4-bondarenko" + Util.getDateAndTimeFormatted();
    final String updatedTitle = title + " updated";

    @Test
    public  void updatePostTitle(){

        loginPage
                .loginWithValidCred()
                .checkIsButtonSignOutDisplayed()
                .clickOnCreatePostButton();

        createPostPage
                .checkIsRedirectToCreatePostPage()
                .enterTextIntoTitleInput(title)
                .enterTextIntoBodyInput("Body post")
                .clickOnButtonSaveNewPost()
                .checkIsRedirectToPostPage()
                .checkTextInAlert("New post successfully created.")
                .clickOnMyProfileButton()
                .checkPostIsInList(title)
                .clickOnPostTitle(title)
                .clickOnEditButton()
                .enterTextIntoTitleInput(updatedTitle)
                .clickOnSaveUpdatesButton()
                .checkTextInAlert("Post successfully updated.")
                .clickOnMyProfileButton()
                .checkPostIsInList(updatedTitle);
    }

   @After
  public void deletePost(){
   homePage
           .openHomePage()
           .checkIsButtonSignOutDisplayed()
           .clickOnMyProfileButton()
           .checkIsRedirectToMyProfilePage()
           .deletePostWithTitleWhilePresent(updatedTitle);
}
}

