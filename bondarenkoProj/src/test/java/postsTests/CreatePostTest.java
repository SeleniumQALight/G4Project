package postsTests;

import baseTest.BaseTest;
import org.junit.Test;

public class CreatePostTest extends BaseTest {
    @Test
    public  void createNewPost(){
        loginPage
                .loginWithValidCred()
                .checkIsButtonSignOutDisplayed();

    }
}
