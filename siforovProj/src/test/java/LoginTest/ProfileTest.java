package LoginTest;

import baseTest.BaseTest;
import org.junit.Assert;
import org.junit.Test;

public class ProfileTest extends BaseTest {

    //homework1
    @Test
    public void userProfileIsAvailableForAuthenticatedUser(){
        loginPage.loginWithValidCredentials()
                .clickOnProfileLink();

        Assert.assertTrue("The Posts tab is not available", profilePage.postTabIsVisible());
    }


}
