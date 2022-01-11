package LoginTest;

import baseTest.BaseTest;
import org.junit.Assert;
import org.junit.Test;

public class ChatTest extends BaseTest {
    //Homework1
    @Test
    public void chatIsClickableAndAvailable(){
        loginPage.loginWithValidCredentials()
                .clickOnChatIcon();

        Assert.assertTrue("The chat isn't opened", homePage.chatIsOpened());
    }
}
