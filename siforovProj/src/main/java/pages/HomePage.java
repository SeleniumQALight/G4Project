package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends ParentPage {

    @FindBy(xpath = ".//button[text()='Sign Out']")
    private WebElement signOutButton;

    @FindBy(xpath = ".//a[@class='mr-2']")
    private WebElement profileLink;

    @FindBy(xpath = ".//a[contains(text(),'Posts')]")
    private WebElement postsTab;

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    public boolean isButtonSignOutDisplayed() {
        try {
            return signOutButton.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    public HomePage checkIsButtonSignOutDisplayed(){
        Assert.assertTrue("Button Sign Out is not displayed", isButtonSignOutDisplayed());
        return new HomePage(webDriver);
    }

    //homework1
    public void clickOnSignOutButton(){
        clickOnElement(signOutButton);
    }

    //homework1
    public void clickOnProfileLink(){
        clickOnElement(profileLink);
    }

    //homework1
    public boolean postTabIsVisible(){
       return elementIsVisible(postsTab);
    }

}
