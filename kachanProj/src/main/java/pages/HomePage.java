package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends ParentPageWithHeader {
    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    public boolean isButtonSingOutDisplayed() {
        try {
            return webDriver.findElement(By.xpath(".//button[text()='Sign Out']")).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public HomePage checkIsButtonSingOutDisplayed(){
        Assert.assertTrue("Button SingOut is not diplayed", isButtonSingOutDisplayed());
        return this;
    }
}
