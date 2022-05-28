package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import io.qameta.allure.Step;

public class HomePage extends ParentPageWithHeader{
    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/";
    }

    @Step
    public boolean isButtonSignOutDisplayed(){
        try{
            return webDriver.findElement(By.xpath(".//button[text()='Sign Out']")).isDisplayed();
        }catch (Exception e){
            return false;
        }
    }
    @Step
    public HomePage checkIsButtonSignOutDisplayed(){
        Assert.assertTrue("Button SignOut is not displayed", isButtonSignOutDisplayed());
        logger.info("Button SignOut is displayed");
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
