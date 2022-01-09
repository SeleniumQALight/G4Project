package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends ParentPage {
    @FindBy(xpath = ".//button[text()='Sign Out']")
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
        Assert.assertTrue("Button SignOut is not displayed", isButtonSignOutDisplayed());
        return this;
    }

}
