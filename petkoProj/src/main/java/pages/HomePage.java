package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends ParentPage {

    @FindBy(xpath = ".//button[text()='Sign Out']")
    private WebElement buttonSignOut;


    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    public void isButtonSignOutDisplayed(){
        isElementDisplayed(buttonSignOut);
    }

    public HomePage checkIsButtonSignOutDisplayed(){
        isButtonSignOutDisplayed();
        return this;
    }
}
