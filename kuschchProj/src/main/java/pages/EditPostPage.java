package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EditPostPage extends ParentPageWithHeader {

    @FindBy(xpath = "//a[text()='« Back to post permalink']")
    private WebElement linkBackToPost;
    @FindBy(xpath = "//button[@class='btn btn-primary']")
    private WebElement btnUpdate;
    @FindBy(xpath = "//div[@class='alert alert-success text-center']")
    private WebElement alertEdited;
    @FindBy(id = "post-title")
    private WebElement inputTitle;
    @FindBy(xpath = "//button[@class='btn btn-primary']")
    private WebElement btnUpdateSave;

    public EditPostPage(WebDriver webDriver) {super(webDriver); }

    @Override
    String getRalativeUrl() {return "/post/"; }

    public EditPostPage checkIsRedirectToEditPostPage() {
        waitChatTobeHide();
        checkUrlWithPattern();
        Assert.assertTrue("linkBackToPost is not displayed", isElementDisplayed(linkBackToPost));
        return this;
    }

    public EditPostPage clickOnBtnUpdatePost() {
        clickOnElement(btnUpdate);
        return this;
    }

    public EditPostPage checkTextInAlert(String text) {
        waitChatTobeHide();
        checkTextInElem(text, alertEdited);
        return this;
    }
    public EditPostPage enterTextInToTitle(String text) {
        enterTextIntoElement(inputTitle, text);
        return this;
    }

    public EditPostPage clickOnBtnUpdateSavePost() {
        clickOnElement(btnUpdateSave);
        return this;
    }

}
