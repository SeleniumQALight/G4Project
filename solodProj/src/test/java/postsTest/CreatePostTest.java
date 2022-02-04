package postsTest;

import baseTest.BaseTest;
import libs.Util;
import org.junit.After;
import org.junit.Test;

public class CreatePostTest  extends BaseTest {
    final String title="G4-artem " + Util.getDateAndTimeFormatted();

    @Test
    public void createNewPost(){


        loginPage
                .loginWithValidCred()
                .checkIsButtonSignOutDisplayed()
                .clickOnCreatePostButton();

        createPostPage
                .checkIsRedirectToCreatePostPage()
                .enterTextInToTitleInput(title)
                .enterTextInToBodyInput("Body post")
                .checkBoxCondition("check")//check\ uncheck
                .selectTextInDropDownRole("Общедоступное")// Общедоступное \ Частное сообщение \Сообщение для группы
                //.selectValueInDropDownRole("One Person")
                .clickOnButtonSavePost()
                .checkIsRedirectToPostPage()
                .checkIsTextInAlert("New post successfully created.")
                .clickOnMyProfileButton()
                .checkPostWasCreate(title)
        ;
    }
    @After//тест удаления созданого после себя
    public void deletePost(){
        homePage
                .openHomePage()
                .checkIsButtonSignOutDisplayed()
                .clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .deletePostWithTitleWhilePresent(title);

    }





    }




