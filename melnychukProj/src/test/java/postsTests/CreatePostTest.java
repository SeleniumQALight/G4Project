package postsTests;

import baseTest.BaseTest;
import libs.Util;
import org.junit.After;
import org.junit.Test;

public class CreatePostTest  extends BaseTest{
   final String title  = "G4-OlhaM 1-13" + Util.getDateAndTimeFormatted();
    @Test
    public  void createNewPost(){

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
        . checkPostWasCreated(title);
    }
    @After
    public void deletePost(){
      homePage
              .openHomePage()
              .checkIsButtonSignOutDisplayed()
              .clickOnMyProfileButton()
              .checkIsRedirectToMyProfilePage()
              .deletePostWithTitleWhilePresent(title);
    }
/*
ДЗ№4 part1
1. Разработать метод выбора значения в дропдауне
 (найти элемент закрытый, кликнуть, найти строку с нужным текстом и кликнуть по ней для выбора) .
 Назвать его selectTextInDropDownByUI
 ДЗ№5

1. разработать метод установки нужного состояния в чекбокс
- метод должен принимать на вход стринговое состояние (check or uncheck)
- в зависимости от исходного состояния чекбокса и необходимого состояния
 - кликать и выводить сообщение в лог, или не кликать и просто выводить сообщение.
 */

}
