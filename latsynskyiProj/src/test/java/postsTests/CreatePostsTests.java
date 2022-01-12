package postsTests;

import baseTest.BaseTest;
import org.junit.Test;

public class CreatePostsTests extends BaseTest {
    @Test
    public void createNewPost(){
      loginPage
              .loginWithValidCred()
              .checkIsButtonSignOutDisplayed()
              .clickOnCreatePostButton();
      createPostPage.checkIsRedirectToCreatePostPage()
              .enterTextInToTitleInput("G4-Latsynskyi")
              .enterTextInToBodyInput("Body post")
              //.selectTextInDropDownRole("Частное сообщение")
              .selectValueInDropDownRole("One Person")
              .clickOnButtonSaveNewPost()
              .checkIsRedirectPostPage()
              .checkTextInAlert("New post successfully created.")
      ;

    }

}
