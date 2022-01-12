package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreatePostPage extends ParentPageWithHeader {

    @FindBy(name = "title")
    private WebElement inputTitle;

    @FindBy(name = "body")
    private WebElement inputBody;

    @FindBy(tagName = "select")
    private WebElement dropDownRole;

    @FindBy(xpath = ".//*[text()='Save New Post']")
    private WebElement buttonSavePost;


    public CreatePostPage(WebDriver webDriver) {
        super(webDriver);
    }

    public CreatePostPage checkIsRedirectToCreatePostPage() {
        waitChatToBeHide();
        Assert.assertTrue("InputTitle is not displayed", isElementDisplayed(inputTitle));
        return this;
    }

    public CreatePostPage enterTextInToTitleInput(String text) {
        enterTextToElement(inputTitle, text);
        return this;
    }

    public CreatePostPage enterTextInToBodyInput(String text) {
        enterTextToElement(inputBody, text);
        return this;
    }

    public CreatePostPage selectTextInToDropDownRole(String textForSelect) {
        selectTextInDropDown(dropDownRole, textForSelect);
        return this;
    }

    public CreatePostPage selectValueInProdDownRole(String valueForSelect) {
        selectValueInProdDown(dropDownRole, valueForSelect);
        return this;
    }

    public PostPage clickOnButtonSaveNewPost() {
        clickOnElement(buttonSavePost);
        return new PostPage(webDriver);
    }
}
