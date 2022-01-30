package postsTests;

import baseTest.BaseTest;
import libs.Util;
import org.junit.After;
import org.junit.Test;

public class CreatePostTest extends BaseTest {
   final  String title = "Text156 " + Util.getDateAndTimeFormatted();// добавили time steamp
    String textDropDown = "Частное сообщение";
    @Test
    public void createNewPost(){

        loginPage
                .loginWithValidCred()
                .checkIsButtonSignOutDisplayed()
                .clickOnCreatePostButton();
        createPostPage
                .checkIsRedirectToCreatePostPage()
                .enterTextInToTitleInput(title)
                .enterTextInBodyInput("BodyText")
               // .selectTextInDropDownRole("Частное сообщение")
                .selectTextInDropDownByUI()
                //.selectValueInDropDown("One Person")
                .setCheckboxValueCreatePost("uncheck")
                .clickOnButtonSaveNewPost()
                .checkIsRedirectToPostPage()
                .checkTextInAlert("New post successfully created.")
                .clickOnMyProfileButton()
                .checkPostIsInListOfPosts(title)
        ;

    }

    @After
    public void deletePost(){
        homePage
                .openHomePage()
                .checkIsButtonSignOutDisplayed()
                .clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .deletePostWithTitleWilePresent(title)
                ;

    }
}
