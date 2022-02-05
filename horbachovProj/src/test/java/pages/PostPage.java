package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PostPage extends ParentPageWithHeader{
    @FindBy(xpath = ".//*[@class='alert alert-success text-center']")
    private WebElement alertSuccess;
    @FindBy(xpath = ".//a[@data-original-title='Edit']")
    private WebElement editButton;

    @FindBy(xpath = ".//button[@data-original-title='Delete']")
    private WebElement buttonDelete;
    @FindBy(xpath = ".//a[contains(@href,'edit')]")
    private WebElement postEditButton;
    @FindBy(xpath = ".//input[@id='post-title']")
    private WebElement title;
    @FindBy(xpath = ".//button[text()='Save Updates']")
    private WebElement saveUpdatesButton;
    @FindBy(xpath = ".//*[@data-original-title='My Profile']")
    private WebElement buttonMyProfile;

    public PostPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/post/";
    }

    public PostPage checkIsRedirectToPostPage(){
        waitChatToBeHide();
        checkUrlWithPattern();
        Assert.assertTrue("Edit Button is notDisplayed.", isElementDisplayed(editButton));
        return this;
    }

    public PostPage checkTextInAlert(String text){
        Assert.assertEquals("Text in Alert", text, alertSuccess.getText());
        return this;
    }

    public MyProfilePage clickOnDeleteButton() {
        clickOnElement(buttonDelete);
        return new MyProfilePage(webDriver);
    }

    public void clickOnPostEditButton() {
        clickOnElement(postEditButton);
    }

    public PostPage changePostTitle(String newTitle) {
        enterTextIntoElement(title, newTitle);
        return this;
    }

    public void saveUpdatesAndGoToMyProfilePage() {
        clickOnElement(saveUpdatesButton);
        clickOnElement(buttonMyProfile);

    }
}
