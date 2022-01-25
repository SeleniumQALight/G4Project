package singUpTestForOurApp;

import baseTest.BaseTest;
import org.junit.Assert;
import org.junit.Test;

public class CheckValidationMessage extends BaseTest {

    @Test
    public void checkMassage(){
        loginPage
                .enterInvalidDataForOurApp();

        Assert.assertTrue(" Massage is not SHOW-'Username must be at least 3 characters.' "
                ,loginPage.isValidationTextUsernameDisplayed());
        Assert.assertTrue("Massage is not SHOW-'You must provide a valid email address.'"
                ,loginPage.isValidationTextEmailDisplayed());
        Assert.assertTrue("Massage is not SHOW-'Password must be at least 12 characters.' "
                ,loginPage.isValidationTextPasswordDisplayed());


    }


}
