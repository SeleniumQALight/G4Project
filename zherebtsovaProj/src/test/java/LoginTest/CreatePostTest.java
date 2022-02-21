package LoginTest;

import baseTest.BaseTest;
import junitparams.JUnitParamsRunner;
import libs.Util;
import org.junit.Test;
import org.junit.experimental.categories.Categories;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;


public class CreatePostTest extends BaseTest {
    final String title = "G4-Zherebtsova" + Util.getDateAndTimeFormatted();
    final String newTitle = "New valid Title 123!@#$^";



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
        }

