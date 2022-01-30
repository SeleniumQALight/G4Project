package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreatePostPage extends  ParentPageWithHeader {

    @FindBy(name="title")
    private WebElement inputTitle;
    @FindBy(id="post-body")
    private WebElement inputBody;
    @FindBy(tagName="select")
    private WebElement dropdownRole;
    @FindBy(xpath =".//*[text()='Save New Post']")
    private WebElement buttonSaveNewPost;

    public CreatePostPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/create-post";
    }

    public CreatePostPage checkIsredirectToCreatePostPage(){
        waitChatToBeHide();
        checkUrl();
        Assert.assertTrue("Input title is not displayed", isElementDisplayed(inputTitle));
        return this;
    }

    public CreatePostPage enterTextInToTitleInput(String text) {
        enterTextInToElement(inputTitle, text);
        return this;
    }

    public CreatePostPage enterTextInToBodyInput(String text) {
        enterTextInToElement(inputBody, text);
        return this;
    }

    public CreatePostPage selectTextInDropDownRole(String textForSelect) {
        selectTextInDropDown(dropdownRole, textForSelect);
        return this;
    }


    public CreatePostPage selectValueInDropDownRole(String valueForSelect) {
selectValueInDropDown(dropdownRole, valueForSelect);
    return this;
    }

    public PostPage clickOnButtonSaveNewPost() {
        clickOnElement(buttonSaveNewPost);
        return  new PostPage(webDriver);
    }
}
