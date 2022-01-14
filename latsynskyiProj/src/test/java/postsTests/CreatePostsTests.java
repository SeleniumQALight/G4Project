package postsTests;

import baseTest.BaseTest;
import libs.Util;
import org.junit.Test;

public class CreatePostsTests extends BaseTest {
    @Test
    public void createNewPost(){
       final String title = "G4-Latsynskyi" + Util.getDateAndTimeFormatted();

      loginPage
              .loginWithValidCred()
              .checkIsButtonSignOutDisplayed()
              .clickOnCreatePostButton();
      createPostPage
              .checkIsRedirectToCreatePostPage()
              .enterTextInToTitleInput(title)
              .enterTextInToBodyInput("Body post")
              //.selectTextInDropDownRole("Частное сообщение")
              .selectValueInDropDownRole("One Person")
              .clickOnButtonSaveNewPost()
              .checkIsRedirectPostPage()
              .checkTextInAlert("New post successfully created.")
              .clickOnMyProfileButton()
              .checkPostWasCreated(title)
      ;

    }

}
