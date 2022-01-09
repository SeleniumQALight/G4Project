package LoginTest;

import baseTest.BaseTest;
import org.junit.Assert;
import org.junit.Test;

public class SearchTest extends BaseTest {
    //homework1
    @Test
    public void searchItemIsVisible(){
        loginPage.loginWithValidCredentials();

        Assert.assertTrue("The Search item is not displayed", homePage.searchItemIsVisible());
    }
}
