package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreatePostPage extends ParentPageWithHeader{
    //.//input[@name='title']
    @FindBy(name="title")
    private WebElement inputTitle;

    @FindBy(id="post-body")
    private WebElement inputBody;

    @FindBy(tagName = "select")
    private WebElement dropDownRole;
    @FindBy(xpath = ".//*[text()='Save New Post']")
    private WebElement buttonSaveNewPost;
    private String elementInSelect = ".//*[text()='%s']";

    public CreatePostPage(WebDriver webDriver) {
        super(webDriver);
    }

public CreatePostPage checkIsRedirectToCreatePostPage(){
        waitChatTobeHide();
    Assert.assertTrue("Input title is not displayed"
            ,isElementDisplayed(inputTitle));
        return this;
}

    public CreatePostPage enterTextInToTitleInput(String text) {
        enterTextInToElement(inputTitle,text);
        return this;
    }

    public CreatePostPage enterTextInToBodyInput(String text) {
        enterTextInToElement(inputBody,text);
        return this;
    }

    public CreatePostPage selectTextInDropDownRole(String textForSelect) {
        selectTextInDropDown(dropDownRole,textForSelect);
        return this;
    }

    public CreatePostPage selectValueInDropDownRole(String valueForSelect) {
        selectValueInDropDown(dropDownRole,valueForSelect);
        return this;
    }
    public CreatePostPage selectValueInDropDownRoleByUi(String valueForSelectUI) {
        selectTextInDropDownByUI(dropDownRole,elementInSelect, valueForSelectUI);
        return this;
    }

    public PostPage clickOnButtonSaveNewPost() {
    clickOnElement(buttonSaveNewPost);
    return new PostPage(webDriver);
    }
}
