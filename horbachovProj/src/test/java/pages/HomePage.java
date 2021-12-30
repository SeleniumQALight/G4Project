package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends ParentPage {

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    public boolean isButtonSignOutDisplayed(){
        try{
            logger.info("Success !!!");
            return webDriver.findElement(By.xpath(".//button[text()='Sign Out']")).isDisplayed();

        } catch (Exception e) {
            return false;
        }
    }

}
