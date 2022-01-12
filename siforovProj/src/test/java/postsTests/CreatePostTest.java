package postsTests;

import baseTest.BaseTest;
import libs.TestData;
import org.junit.Test;

public class CreatePostTest extends BaseTest {
    @Test
    public void createNewPost(){
            loginPage
                    .loginWithValidCredentials()
                    .checkIsButtonSignOutDisplayed()
                    .clickOnCreatePostButton();
            createPostPage
                    .checkIsRedirectToCreatePostPage()
                    .enterTextIntoTitleInput(TestData.VALID_POST_TITLE)
                    .enterTextIntoBody(TestData.VALID_TEXT)
//                    .selectTextInDropDownCreatePost("Частное сообщение")
                    .selectValueInDropDownRole("One Person")
                    .clickOnSaveNewPostButton()
                    .checkIsRedirectToPostPage()
                    .checkTextInAlert("New post successfully created.");
    }

    //Homework 4 (Custom select)
    @Test
    public void createNewPostUsingCustomSelect(){
        loginPage
                .loginWithValidCredentials()
                .checkIsButtonSignOutDisplayed()
                .clickOnCreatePostButton();
        createPostPage
                .checkIsRedirectToCreatePostPage()
                .enterTextIntoTitleInput(TestData.VALID_POST_TITLE)
                .enterTextIntoBody(TestData.VALID_TEXT)
//                    .selectTextInDropDownCreatePost("Частное сообщение")
                .selectValueInDDUsingList("Частное сообщение")
                .clickOnSaveNewPostButton()
                .checkIsRedirectToPostPage()
                .checkTextInAlert("New post successfully created.");
    }
}
