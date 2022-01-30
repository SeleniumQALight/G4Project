package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends ParentPageWithHeader{


    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/";
    }

    public boolean isButtonSignOutDisplayed(){
        try{
            return webDriver.findElement(By.xpath(".//button[text()='Sign Out']")).isDisplayed();
        }catch (Exception e){
            return false;
        }
    }

    public HomePage checkIsButtonSignOutDisplayed(){
        Assert.assertTrue("Button SignOut is not displayed", isButtonSignOutDisplayed());
        return this;
    }
    public HomePage openHomePage(){
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.openLoginPage();
        if (!isButtonSignOutDisplayed()){ // ! если кнопка не показана
            loginPage.loginWithValidCred();
        }
        return this;
    }

}
