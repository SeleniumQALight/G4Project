package LoginTest;

import BaseTest.BaseTest;
import libs.Util;
import org.junit.After;
import org.junit.Test;

import java.awt.*;

public class CreatePostTest extends BaseTest {
    final String title = "G4-Zherebtsova" + Util.getDateAndTimeFormatted();


    @Test
    public void creteNewPost() {
        loginPage
                .loginWithValidCred()
                .checkIsButtonSignOutDisplayed()
                .clickButtonCreatePost();

        createPostPage
                .checkIsRedirectToCreatePostPage()
                .enterTextInToTitleInput(title)
                .enterTextInToBodyInput("28-09-TomCat")
                //.selectTextInDropDownRole("Частное сообщение")
                //.selectValueInDropDownRole("One Person")
                .selectTextInDropDownByUI()
                .selectValueInCheckBox()
                .clickOnButtonSaveNewPost()
                .checkTextInAlert("New post successfully created.")
                .checkIsRedirectToPostPage()
                .clickOnMyProfileButton()
                .checkPostWasCreated(title);
    }


        @After
        public void deletePost(){
            homePage
                    .openHomePage()
            .checkIsButtonSignOutDisplayed()
                    .clickOnMyProfileButton()
                    .checkIsRedirectToMyProfilePage()
                    .deletePostWithTitleWhilePresent(title);



        }
  }
