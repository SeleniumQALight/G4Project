package postsTest;

import baseTest.BaseTest;
import libs.Util;
import org.junit.After;
import org.junit.Test;

public class UpdatePostTest extends BaseTest {
    final String title = "G4-horbachov" + Util.getDateAndTimeFormatted();
    String newTitle = title + " Update";
    @Test
    public void createNewPost(){

        loginPage
                .loginWithValidCred()
                .checkIsButtonSignOutDisplayed()
                .clickOnCreatePostButton();
        createPostPage
                .checkIsRedirectToCreatePostPage()
                .enterTextIntoTitleInput(title)
                .enterTextIntoBodyInput("Body post")
 //               .selectTextInDropDownRole("Частное сообщение")
                .selectValueInDropDownRole("One Person")
                .clickOnButtonSaveNewPost()
                .checkIsRedirectToPostPage()
                .checkTextInAlert("New post successfully created.")
                .clickOnMyProfileButton()
                .checkPostWasCreated(title);
        myProfilePage
                .openMyLastPost(".//*[strong='" + title + "']");
        postPage.clickOnPostEditButton();
        postPage.changePostTitle(newTitle)
                .saveUpdatesAndGoToMyProfilePage();
        myProfilePage.checkUpdatedPost(".//*[strong='" + newTitle + "']");





    }

   @After
    public void deletePost(){
        homePage
               .openHomePage()
                .checkIsButtonSignOutDisplayed()
               .clickOnMyProfileButton()
               .checkIsRedirectToMyProfilePage()
                .deletePostWithTitleWhilePresent(newTitle);

    }
}
