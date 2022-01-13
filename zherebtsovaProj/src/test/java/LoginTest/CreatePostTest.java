package LoginTest;

import BaseTest.BaseTest;
import libs.Util;
import org.junit.Test;

public class CreatePostTest extends BaseTest {
    final String title = "G4-Zherebtsova" + Util.getDateAndTimeFormatted();

    @Test
    public void creteNewPost(){
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
                .clickOnButtonSaveNewPost()
                .checkTextInAlert("New post successfully created.")
                .checkIsRedirectToPostPage()
                .clickOnMyProfileButton()
                .checkPostWasCreated(title);


    }

}
