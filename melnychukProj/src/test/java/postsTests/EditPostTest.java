package postsTests;

import baseTest.BaseTest;
import libs.Util;
import org.junit.After;
import org.junit.Test;

public class EditPostTest extends BaseTest {
    /*
    Написать тест на проверку функционала изменения поста.
    Тест должен иметь все что нужно для его работы и подчищать за собой
     */
    final String title = "G4-OlhaM edit post title test" + Util.getDateAndTimeFormatted();
    final String title2 = "Edited title" + Util.getDateAndTimeFormatted();

    @Test
    public void createNewPost() {


        loginPage.loginWithValidCred()
                .checkIsButtonSignOutDisplayed()
                .clickOnCreatePostButton();
        createPostPage.checkIsredirectToCreatePostPage()
                .enterTextInToTitleInput(title)
                .enterTextInToBodyInput("Body post")
                // .selectTextInDropDownRole("Частное сообщение")
                .selectValueInDropDownRole("One Person")
                .clickOnButtonSaveNewPost()
                .checkIsRedirectToPostPage()
                .checkTextInAlert("New post successfully created.")
                .clickOnMyProfileButton()
                .checkPostWasCreated(title)
                .clickOnRecentlyCreatedPost()
                .clickOnEditButtonInThePost()
                .enterTextInToTitleInputEdit(title2)
                .clickOnSaveUpdatesButton()
                .clickOnMyProfileButton()
                .checkPostWithEditedTitle(title2);

    }

    @After
    public void deletePost() {
        homePage
                .openHomePage()
                .checkIsButtonSignOutDisplayed()
                .clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .deletePostWithTitleWhilePresent(title);
    }
}
