package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreatePostPage extends ParentPageWithHeader {

    // .//*[@name='title']
    @FindBy(name = "title")
    private WebElement inputTitle;

    @FindBy(id = "post-body")
    private WebElement inputBody;

    @FindBy(tagName = "select")
    private WebElement dropDownRole;

    @FindBy(xpath = ".//*[text()='Save New Post']")
    private WebElement buttonSaveNewPost;

    public CreatePostPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/create-post";
    }

    public CreatePostPage checkIsRedirectedToCreatePostPage() {
        waitChatTobeHide();
        checkUrl();
        Assert.assertTrue("InputTitle is not displayed",
                isElementDisplayed(inputTitle));
        return this;
    }

    public CreatePostPage enterTextinToTitleInput(String title) {
        enterTextInToElement(inputTitle, title);
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


    public  CreatePostPage selectValueInDropDown(String valueForSelect) {
        selectValueInDropDown(dropDownRole, valueForSelect);
        return this;
    }


    public PostPage clickOntButtonSaveNewPOst() {
        clickOnElement(buttonSaveNewPost);
        return new PostPage(webDriver);
    }

}
