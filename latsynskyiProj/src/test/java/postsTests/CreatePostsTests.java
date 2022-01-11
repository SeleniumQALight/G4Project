package postsTests;

import baseTest.BaseTest;
import org.junit.Test;

public class CreatePostsTests extends BaseTest {
    @Test
    public void createNewPost(){
      loginPage
              .loginWithValidCred()
              .checkIsButtonSignOutDisplayed();

    }

}
