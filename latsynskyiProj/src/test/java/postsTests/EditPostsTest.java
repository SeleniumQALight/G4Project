package postsTests;

import baseTest.BaseTest;
import libs.Util;
import org.junit.After;
import org.junit.Test;


public class EditPostsTest extends BaseTest {
    String textInTitle = "text" + Util.getDateAndTimeFormatted();
    String textForEdit ="Text was edit at "+Util.getDateAndTimeFormatted();
    @Test
   public void editPost (){
        loginPage.loginWithValidCred()
                .checkIsButtonSignOutDisplayed()
                .clickOnCreatePostButton()
                .checkIsRedirectToCreatePostPage()
                .enterTextInToTitleInput(textInTitle)
                .enterTextInToBodyInput("test edit")
                .clickOnButtonSaveNewPost()
                .checkIsRedirectPostPage()
                .checkTextInAlert("New post successfully created.")
                .clickOnMyProfileButton()
                .checkPostWasCreated(textInTitle)
                .clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .editPostWithTitleWhilePresent(textInTitle,textForEdit)
              ;
    }
        @After
                public void editCreatedPost(){

            homePage
                    .openHomePage()
                    .checkIsButtonSignOutDisplayed()
                    .clickOnMyProfileButton()
                    .checkIsRedirectToMyProfilePage()
                    .deletePostWithTitleWhilePresent(textForEdit);


        }

    }

