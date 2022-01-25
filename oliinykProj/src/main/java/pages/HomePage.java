package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends ParrentPageWithHeader{
    public HomePage(WebDriver driver) {
        super(driver);
    }

    @Override
    String getRelativeUrl() {
        return "/";
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

    public HomePage openHomePage() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.openLoginPage();
        if (!isDispayedButtonSignOut()){
            loginPage.logedInHomepage();
        }
        return this;
    }
}
