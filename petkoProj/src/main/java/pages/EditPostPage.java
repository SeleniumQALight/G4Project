package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EditPostPage extends ParentPageWithHeader{

    @FindBy(xpath = ".//button[text()='Save Updates']")
    private WebElement buttonSaveUpdates;

    @FindBy(name = "title")
    private WebElement inputTitle;

    @FindBy(xpath = ".//*[@class='alert alert-success text-center']")
    private WebElement alertSuccess;

    @FindBy(xpath = ".//a[text()='« Back to post permalink']")
    private WebElement linkToPostPage;

    public EditPostPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/post/";
    }

    public EditPostPage checkIsRedirectToEditPostPage(){
        waitChatToBeHide();
        checkUrlWithPattern();
        Assert.assertTrue("Button 'Save updates' is not displayed", isElementDisplayed(buttonSaveUpdates));
        return this;
    }

    public EditPostPage checkTextInAlert(String text){
        Assert.assertEquals("Text in Alert",text,alertSuccess.getText());
        logger.info("Successfully edit message in the alert is present");
        return this;
    }

    public EditPostPage editTextInTitleInputAndSaveChanges(String newTitle){
        inputTitle.clear();
        enterTextInToElement(inputTitle, newTitle);
        clickOnElement(buttonSaveUpdates);
        return this;
    }

    public PostPage clickOnLinkToPostPage(){
        clickOnElement(linkToPostPage);
        return new PostPage(webDriver);
    }
}
