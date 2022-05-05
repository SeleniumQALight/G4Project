package postsTests;

import baseTest.BaseTest;
import categories.SmokeTestFilter;
import libs.TestData;
import libs.Util;
import org.junit.After;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class CreatePostTest extends BaseTest {
    final String title = "RomanSiforov G4 "+ Util.getDateAndTimeFormatted();

    @Test
    public void createNewPost(){
            loginPage
                    .loginWithValidCredentials()
                    .checkIsButtonSignOutDisplayed()
                    .clickOnCreatePostButton()
                    .checkIsRedirectToCreatePostPage()
                    .enterTextIntoTitleInput(title)
                    .enterTextIntoBody(TestData.VALID_TEXT)
                    .markCheckbox(true)
//                    .selectTextInDropDownCreatePost("Частное сообщение")
                    .selectValueInDropDownRole("One Person")
                    .clickOnSaveNewPostButton()
                    .checkIsRedirectToPostPage()
                    .checkTextInAlert("New post successfully created.")
                    .clickOnProfile()
                    .checkPostWasCreated(title);
    }

    @After
    public void deletePost(){
        homePage
                .openHomePage()
                .checkIsButtonSignOutDisplayed()
                .clickOnProfileLink()
                .checkIsRedirectToProfilePage()
                .deletePostWithTitleWhilePresent(title);
    }

    //Homework 4 (Custom select)
    @Test
    @Category(SmokeTestFilter.class)
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
