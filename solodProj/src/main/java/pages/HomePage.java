package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage  extends ParentPageWithHeader{
    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/";
    }

    public boolean isButtonSingOutDisplayed() {// метод вернет значение is
        try {
            return webDriver.findElement(By.xpath(".//button[text()='Sign Out']")).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public HomePage checkIsButtonSignOutDisplayed() {
        Assert.assertTrue(" Button SignOut is not displayed", isButtonSingOutDisplayed());
        return this;
    }

    public HomePage openHomePage() {
        LoginPage loginPage= new LoginPage(webDriver);
        loginPage.openLoginPage();//открой логин страницу
        if (!isButtonSingOutDisplayed()){//если ты не залогиненты выполни действие ниже
            loginPage.loginWithValidCred();//заголинся валидным логин или полем
        }
        return  this;
    }
}
