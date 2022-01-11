package postsTest;

import baseTest.BaseTest;
import org.junit.Test;
import pages.LoginPage;

public class CreatePostTest extends BaseTest {
    @Test
    public void createNewPost(){
        loginPage
                .loginWithValidCred()
                .checkIsbuttonSignOutDisplayed();


    }
}
