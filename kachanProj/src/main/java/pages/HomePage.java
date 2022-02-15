package pages;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends ParentPageWithHeader {
    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRalativeUrl() {
        return "/";
    }

    @Step
    public boolean isButtonSingOutDisplayed() {
        try {
            return webDriver.findElement(By.xpath(".//button[text()='Sign Out']")).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    @Step
    public HomePage checkIsButtonSingOutDisplayed() {
        Assert.assertTrue("Button SingOut is not diplayed", isButtonSingOutDisplayed());
        return this;
    }

    @Step
    public HomePage openHomePage() {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.openLoginPage();
        if (!isButtonSingOutDisplayed()) {
            loginPage.loginWithValidCred();
        }
        return this;
    }
}
