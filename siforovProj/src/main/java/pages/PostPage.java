package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PostPage extends ParentPageWithHeader{

    @FindBy(xpath = ".//*[@class='alert alert-success text-center']")
    private WebElement successTextPostCreated;

    @FindBy(xpath = ".//a[@class='text-primary mr-2']")
    private WebElement editButton;

    @FindBy(xpath = ".//button[@class='delete-post-button text-danger']")
    private WebElement deletePostButton;

    @FindBy(xpath = ".//input[@name='title']")
    private WebElement titleInput;

    @FindBy(xpath = ".//*[@class='d-flex justify-content-between']/h2")
    private WebElement postName;

    private String tmpPostName;

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
        Assert.assertTrue("The Edit button is not displayed", elementIsVisible(editButton));
        return this;
    }

    public PostPage checkTextInAlert(String text){
        Assert.assertEquals("Text in Alert ",text, successTextPostCreated.getText());
        return this;
    }

    public ProfilePage clickOnDeleteButton() {
        clickOnElement(deletePostButton);
        return new ProfilePage(webDriver);
    }

    public EditPostPage clickOnEditButton() {
        clickOnElement(editButton);
        return new EditPostPage(webDriver);
    }
}
