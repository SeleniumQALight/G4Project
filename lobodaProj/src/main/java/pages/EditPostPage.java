package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EditPostPage extends ParentPageWithHeader{

    @FindBy(name = "title")
    private WebElement inputTitle;

    @FindBy(xpath = ".//button[contains(text(),'Save Updates')]")
    private WebElement saveUpdateButton;

    @FindBy(xpath = ".//*[contains(text(),'« Back to post permalink')]")
    private WebElement backToPostPermalink;

    @FindBy(xpath = ".//*[contains(text(),'Post successfully updated.')]")
    private WebElement alertSuccessUpdate;

    public EditPostPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/post/";
    }

    public EditPostPage enterTextInToTitleInput(String text) {
        enterTextInToElement(inputTitle, text);
        return this;
    }

    public EditPostPage clickOnButtonSaveUpdate() {
        clickOnElement(saveUpdateButton);
        return this;
    }

    public EditPostPage checkIsRedirectToEditPostPage(){
        waitChatToBeHide();
        checkUrlWithPattern();
        Assert.assertTrue("backToPostPermalink is not displayed"
                , isElementDisplayed(backToPostPermalink));
        return this;
    }

    public EditPostPage checkIsAlertSuccessPostUpdatedDisplayed(){
        waitChatToBeHide();
        checkUrlWithPattern();
        Assert.assertTrue("alertSuccessUpdate is not displayed"
                , isElementDisplayed(alertSuccessUpdate));
        return this;
    }

}
