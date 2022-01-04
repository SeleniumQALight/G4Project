package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends ParentPage{
    public HomePage(WebDriver driver) {
        super(driver);
    }

    public boolean isDispayedButtonSignOut(){
        try {
            return driver.findElement(By.xpath(".//button[text()='Sign Out']")).isDisplayed();
        }catch (Exception e){
            return false;
        }
    }

    public HomePage checkIsButtonSingOutDisplayed(){
        Assert.assertTrue("Button Sing Out is not displayed", isDispayedButtonSignOut());
        return this;
    }
}
