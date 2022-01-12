package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

public class HomePage extends ParentPageWithHeader {


    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    public HomePage checkIsButtonSignOutDisplayed() {
        Assert.assertTrue("Button SignOut is not displayed", isElementDisplayed(buttonSignOut));
        return this;
    }
}
