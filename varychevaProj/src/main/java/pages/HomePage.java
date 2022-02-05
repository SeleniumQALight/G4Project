package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.junit.Assert.assertTrue;

public class HomePage extends ParentPageWithHeader {

    @FindBy(xpath =".//button[text()='Sign Out']")
    private WebElement buttonSignOut;

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    public boolean isButtonSignOutDisplayed() {
        try {
            return buttonSignOut.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public HomePage checkIsButtonSignOutDisplayed() {
        assertTrue("Button SignOut is not displayed", isButtonSignOutDisplayed());
        return this;
    }
}
