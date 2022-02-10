package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends ParentPageWithHeaders{
    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/";
    }

    public boolean isButtonSignOutDisplayed(){
        try {
            return webDriver.findElement(By.xpath(".//button[text()='Sign Out']")).isDisplayed();
        }catch (Exception e){
            return false;
        }
    }

    public HomePage checkIsButtonSingOutDisplayed(){
        Assert.assertTrue("Button SingOut is not displayed", isButtonSignOutDisplayed());
        return this;  // this -- вернуть эту же страничку
    }

    public HomePage openHomePage() {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.openLoginPage();
        if (!isButtonSignOutDisplayed()){
            loginPage.loginWithValidCred();
        }
        return this;
    }
}
