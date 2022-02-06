package postsTests;

import baseTest.BaseTest;
import libs.Util;
import org.junit.After;
import org.junit.Test;

public class CheckBoxTest extends BaseTest {
    /*
    Home Work 5
     разработать метод установки нужного состояния в чекбокс
- метод должен принимать на вход стринговое состояние (check or uncheck)
- в зависимости от исходного состояния чекбокса и необходимого состояния
 кликать и выводить сообщение в лог, или не кликать и просто выводить сообщение.
     */
    final String title = "G4-OlhaM-checkBoxTest" + Util.getDateAndTimeFormatted();

    @Test
    public void checkBoxTest() {
        loginPage.loginWithValidCred()
                .checkIsButtonSignOutDisplayed()
                .clickOnCreatePostButton();
        createPostPage.checkIsredirectToCreatePostPage()
                .enterTextInToTitleInput(title)
                .enterTextInToBodyInput("Body post")
                .selectValueInDropDownRole("One Person")
                .checkBoxCondition("uncheck")
                .clickOnButtonSaveNewPost()
                .checkIsRedirectToPostPage()
                .checkTextInAlert("New post successfully created.")
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
