package pages;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends ParentPageWithHeader{

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRalativeUrl() {
        return "/";
    }

    @Step
    public boolean isButtonSignOutDisplayed(){
        try {
            return webDriver.findElement(By.xpath(".//button[text()='Sign Out']")).isDisplayed();
        }catch (Exception e) {
            return false;
        }
    }

    @Step
    public HomePage checkIsButtonSignOutDisplayed(){
        Assert.assertTrue("Button signOut is not displayed", isButtonSignOutDisplayed());
        return this;
    }

    @Step
    public HomePage openHomePage() {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.openLoginPage();
        if (!isButtonSignOutDisplayed()){
            loginPage.loginWithValidCred();
        }
        return this;
    }
}
