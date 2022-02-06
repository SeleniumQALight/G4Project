package postsTests;

import baseTest.BaseTest;
import libs.Util;
import org.junit.After;
import org.junit.Test;

public class DropdownTest extends BaseTest {
    /*
    ДЗ№4 part1
 Разработать метод выбора значения в дропдауне
 (найти элемент закрытый, кликнуть, найти строку с нужным текстом и кликнуть по ней для выбора) .
     */
    final String title = "G4-OlhaM 02-06" + Util.getDateAndTimeFormatted();

    @Test
    public void dropdownCheckTest() {
        loginPage.loginWithValidCred()
                .checkIsButtonSignOutDisplayed()
                .clickOnCreatePostButton();
        createPostPage
                .checkIsredirectToCreatePostPage()
                .enterTextInToTitleInput(title)
                .enterTextInToBodyInput("Body post")
                // .selectTextInDropDownRole("Частное сообщение")
                .selectValueInDropDownRole("One Person")
                .clickOnButtonSaveNewPost()
                .checkIsRedirectToPostPage()
                .checkTextInAlert("New post successfully created.")
                .checkNoteTextForDropdownValue("One Person")
                .clickOnMyProfileButton()
                .checkPostWasCreated(title);
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
