package pages;

import io.qameta.allure.Step;
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

    @Step
    public boolean isDispayedButtonSignOut(){
        try {
            return driver.findElement(By.xpath(".//button[text()='Sign Out']")).isDisplayed();
        }catch (Exception e){
            return false;
        }
    }

    @Step
    public HomePage checkIsButtonSingOutDisplayed(){
        Assert.assertTrue("Button Sing Out is not displayed", isDispayedButtonSignOut());
        return this;
    }

    @Step
    public HomePage openHomePage() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.openLoginPage();
        if (!isDispayedButtonSignOut()){
            loginPage.logedInHomepage();
        }
        return this;
    }
}
