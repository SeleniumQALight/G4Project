package postsTests;

import baseTest.BaseTest;
import org.junit.After;

abstract public class BasePostTest extends BaseTest {

    String title;

    @After
    public void deletePost(){
        homePage
                .openHomePage()
                .checkIsButtonSignOutDisplayed()
                .clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .deletePostWithTitleWhilePresent(title);
    }
}
