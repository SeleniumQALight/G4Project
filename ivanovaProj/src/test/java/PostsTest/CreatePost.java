package PostsTest;

import baseTest.BaseTest;
import org.junit.Test;

public class CreatePost extends BaseTest {
    @Test
    public void createNewPost (){
        loginPage
                .loginWithValidCred()
                .checkIsButtonSignOutDisplayed();

    }
}
