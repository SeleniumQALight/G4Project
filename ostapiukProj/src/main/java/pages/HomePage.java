package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends ParentPage{
    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    public boolean isButtonSignOutDisplayed(){
        try{
            return webDriver.findElement(By.xpath(".//button[text()='Sign Out']")).isDisplayed();
        }catch (Exception e){
            return false;
        }
    }

    public boolean isButtonSignInDisplayed() {
        try{
            return webDriver.findElement(By.xpath(".//button[text()='Sign In']")).isDisplayed();
        }catch (Exception e){
            return false;
        }
    }

    public boolean isTextLoginShown(){
        try{
            return webDriver.findElement(By.xpath(".//*[text()='Username must be at least 3 characters.']")).isDisplayed();
        }catch (Exception e){
            return false;
        }
    }

    public boolean isTextEmailShown(){
        try{
            return webDriver.findElement(By.xpath(".//*[text()='You must provide a valid email address.']")).isDisplayed();
        }catch (Exception e){
            return false;
        }
    }

    public boolean isTextPassWordShown(){
        try{
            return webDriver.findElement(By.xpath(".//*[text()='Password must be at least 12 characters.']")).isDisplayed();
        }catch (Exception e){
            return false;
        }
    }

    public HomePage checkIsButtonSignOutDisplayed(){
        Assert.assertTrue("Button Sign Out is not displayed", isButtonSignOutDisplayed());
        return this;
    }

    public HomePage checkIsTextLoginShown(){
        Assert.assertTrue("Text 'Username must be at least 3 characters.' is not displayed", isTextLoginShown());
        return this;
    }

    public HomePage checkIsTextEmailShown(){
        Assert.assertTrue("Text 'You must provide a valid email address.' is not displayed", isTextEmailShown());
        return this;
    }

    public HomePage checkIsTextPassWordShown(){
        Assert.assertTrue("Text 'Password must be at least 12 characters.' is not displayed", isTextPassWordShown());
        return this;
    }
}
