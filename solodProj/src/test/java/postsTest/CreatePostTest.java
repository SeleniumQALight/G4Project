package postsTest;

import baseTest.BaseTest;
import org.junit.Test;

public class CreatePostTest  extends BaseTest {

    @Test
    public void createNewPost(){
        loginPage
                .loginWithValidCred()
                .checkIsButtonSignOutDisplayed()
                .clickOnCreatePostButton();

        createPostPage
                .checkIsRedirectToCreatePostPage()
                .enterTextInToTitleInput("G4-artem")
                .enterTextInToBodyInput("Body post")
                .checkBoxCondition("check")//check\ uncheck
                .selectTextInDropDownRole("Общедоступное")// Общедоступное \ Частное сообщение \Сообщение для группы
                //.selectValueInDropDownRole("One Person")
                .clickOnButtonSavePost()
                .checkIsRedirectToPostPage()
                .checkIsTextInAlert("New post successfully created.")
        ;


    }





    }




