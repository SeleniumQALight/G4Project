package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreatePostPage extends ParentPageWithHeader{
    // .//*[@name = 'title']
    @FindBy(name = "title")
    private WebElement inputTitle;
    @FindBy(id = "post-body")
    private WebElement inputBody;
    @FindBy(tagName = "select")
    private WebElement dropDownRole;
    @FindBy(xpath = ".//*[text()='Save New Post']")
    private WebElement buttonSaveNewPost;
    @FindBy(xpath = "//*[@type='checkbox']")
    private WebElement checkboxUniquePost;

    private String onePersonRowDropDownRole = "//option[text() = '%s']";

    public CreatePostPage(WebDriver webDriver) {
        super(webDriver);
    }

    public CreatePostPage checkIsRedirectToCreatePostPage(){
        waitChatToBeHide();
        Assert.assertTrue("InputTitle is not displayed"
                , isElementDisplayed(inputTitle));
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
        selectTextInDropDown(dropDownRole, textForSelect);
        return this;
    }

    public CreatePostPage selectValueInDropDownRole(String valueForSelect) {
        selectValueInDropDown(dropDownRole, valueForSelect);
        return this;
    }

    public CreatePostPage selectTextByUIInDropDownRole(String valueForSelect) {
        selectTextInDropDownByUI(dropDownRole, onePersonRowDropDownRole,valueForSelect);
        return this;
    }

    public PostPage clickOnButtonSaveNewPost() {
        clickOnElement(buttonSaveNewPost);
        return new PostPage(webDriver);
    }

    public CreatePostPage setStateForCheckboxUniquePost(String requiredState) {
        setStateForCheckbox(checkboxUniquePost, requiredState);
        return this;
    }

}
