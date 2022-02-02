package postsTests;

import baseTest.BaseTest;
import libs.Util;
import org.junit.After;
import org.junit.Test;

public class CreatePostTest extends BaseTest {

    final String title = "G4-Anna" + Util.getDateAndTimeFormatted();

    @Test
    public void createNewPost() {

        loginPage
                .loginWithValidCred()
                .checkIsButtonSignOutDisplayed()
                .clickOnCreatePostButton();
        createPostPage
                .checkIsRedirectToCratePostPage()
                .enterTextIntoTitleInput(title)
                .enterTextIntoBodyInput("Body post")
                //               .selectTextInDropDownRole("Частное сообщение")
                .selectValueInDropDownRole("One Person")
                .clickOnButtonSaveNewPost()
                .checkIsRedirectToPostPage()
                .checkTextInAlert("New post successfully created.")
                .clickOnMyProfileButton()
                .checkPostWasCreated(title)


        ;
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

    @Test
    public void selectInDropdown() {
        loginPage
                .loginWithValidCred()
                .checkIsButtonSignOutDisplayed()
                .clickOnCreatePostButton()
                .checkIsRedirectToCratePostPage()
                .enterTextIntoTitleInput(title)
                .enterTextIntoBodyInput("Body post")

                .checkFieldSelectIsDisplayed()
                .clickOnDropDownRole()
                .selectTextInDropDownRoleByUi("Частное сообщение")
                .checkFieldCheckBoxIsDisplayed();
        Util.waitABit(5);
        createPostPage
                .clickOnCheckBox("check")


        ;

    }
}
