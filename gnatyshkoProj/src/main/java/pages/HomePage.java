package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

public class HomePage extends ParentPageWithHeader {
    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/";
    }

    public HomePage checkIsButtonSignOutDisplayed() {
        Assert.assertTrue("Button SignOut is not displayed", isElementDisplayed(buttonSignOut));
        return this;
    }

    public HomePage openHomePage() {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.openLoginPage();
        if (!isElementDisplayed(buttonSignOut)) {
            loginPage.loginWithInvalidCredentials();
        }
        return this;
    }
}
