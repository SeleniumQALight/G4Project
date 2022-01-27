package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends ParentPageWithHeader {
    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/";
    }

    public boolean isButtonSignOutDisplayed(){
        try{
            return  webDriver.findElement(By.xpath(".//button[text()='Sign Out']")).isDisplayed();
        }catch (Exception e){
            return false;
        }
    }
    public HomePage checkIsButtonSignOutDisplayed(){
        waitChatTobeHide();
        Assert.assertTrue("Button SignOut is not displayed", isButtonSignOutDisplayed());// остановить если проверка не прошла
        return this; //верни эту же страницу
    }

    public HomePage openHomePage() {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.openLoginPage();
        if(!isButtonSignOutDisplayed()){ //если не показана залогинется
            loginPage.loginWithValidCred();
        }
        return this;
    }
}
