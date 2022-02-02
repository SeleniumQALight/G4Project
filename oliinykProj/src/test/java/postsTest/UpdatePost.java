package postsTest;

import baseTest.BaseTest;
import libs.Util;
import org.junit.After;
import org.junit.Test;

public class UpdatePost extends BaseTest {
    final String title = "G4-Oliinyk " + Util.getDateAndTimeFormatted();
    final String addedText = " test";

    @Test
    public void editPostTitle(){
        loginPage.logedInHomepage()
                .checkIsButtonSingOutDisplayed()
                .clickOnCreatePostButton()
                .checkRedirectToCreatePostPage()
                .enterTextIntoTitleInput(title)
                .enterTextIntoBodyInput("Test text")
                .selectTextInDropDownByUI()
                .clickOnButtonSaveNewPost()
                .clickOnEditButton()
                .editPostTitle(addedText)
                .clickSaveUpdateButton()
                .checkTextWasAdded()
                .clickOnMyProfileButton()
                .checkPostWasCreated(title + addedText);

    }

    @After
    public void deleteCreatedPost(){
        homePage.openHomePage()
                .checkIsButtonSingOutDisplayed()
                .clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .deletePostWithTitleWhilePresent(title + addedText);
    }
}
