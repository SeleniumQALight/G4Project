package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EditPostPage extends ParentPageWithHeader {

    @FindBy(xpath = "//a[text()='« Back to post permalink']")
    private WebElement linkBackToPost;
    @FindBy(id = "post-title")
    private WebElement inputPostTitle;
    @FindBy(xpath = "//button[@class='btn btn-primary']")
    private WebElement buttonUpdateSave;
    @FindBy(xpath = "//div[@class='alert alert-success text-center']")
    private WebElement alertSuccess;

    public EditPostPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/post/";
    }

    public EditPostPage checkIsRedirectToEditPostPage() {
        waitChatToBeHide();
        checkUrlWithPattern();
        Assert.assertTrue("linkBackToPost is not displayed", isElementDisplayed(linkBackToPost));
        return this;
    }

    public EditPostPage enterTextInToTitleInput(String text) {
        enterTextToElement(inputPostTitle, text);
        return this;
    }

    public EditPostPage clickOnButtonUpdateSavePost() {
        clickOnElement(buttonUpdateSave);
        return this;
    }

    public EditPostPage checkTextInAlert(String text) {
        waitChatToBeHide();
        checkTextFromElement(text, alertSuccess);
        return this;
    }

}
