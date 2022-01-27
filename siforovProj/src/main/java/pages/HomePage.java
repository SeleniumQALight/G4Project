package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends ParentPageWithHeader {

    @FindBy(xpath = ".//button[text()='Sign Out']")
    private WebElement signOutButton;

    @FindBy(xpath = ".//a[@class='mr-2']")
    private WebElement profileLink;

    @FindBy(xpath = ".//span[@class='text-white mr-2 header-chat-icon']")
    private WebElement chatItem;

    @FindBy(xpath = ".//div[@id='chat-wrapper']")
    private WebElement chatObject;

    @FindBy(xpath = ".//a[@class='text-white mr-2 header-search-icon']")
    private WebElement searchItem;

    @FindBy(xpath = ".//span[@class='text-white mr-2']")
    private WebElement currentUserName;

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/";
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
    public ProfilePage clickOnProfileLink(){
        clickOnElement(profileLink);
        return new ProfilePage(webDriver);
    }

    //homework1
    public void clickOnChatIcon(){
        clickOnElement(chatItem);
    }

    public boolean chatIsOpened(){
        return elementIsVisible(chatObject);
    }

    public boolean searchItemIsVisible(){
        return elementIsVisible(searchItem);
    }

    public boolean checkUserName(){
        return compareElementTextWithExpectedText(currentUserName);
    }

    public HomePage openHomePage() {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.openLoginPage();
        if(!isButtonSignOutDisplayed()){
            loginPage.loginWithValidCredentials();
        }
        return this;
    }
}
