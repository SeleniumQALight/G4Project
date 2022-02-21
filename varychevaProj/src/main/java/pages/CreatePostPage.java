package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.junit.Assert.assertTrue;

public class CreatePostPage extends ParentPageWithHeader {
    @FindBy(name = "title")
    private WebElement inputTitle;

    @FindBy(id = "post-body")
    private WebElement inputBody;

    @FindBy(tagName = "select")
    private WebElement dropDownRole;

    @FindBy(xpath = ".//*[text()='Save New Post']")
    private WebElement buttonSaveNewPost;

    @FindBy(xpath = ".//input[@type = 'checkbox']")
    private WebElement inputCheckbox;

    public CreatePostPage(WebDriver webDriver) {
        super(webDriver);
    }

    public CreatePostPage checkIsRedirectToCreatePostPage(){
        waitChatTobeHide();
        assertTrue("InputTitle is not displayed"
                , isElementDisplayed(inputTitle));
        return this;
    }

    public CreatePostPage enterTextInToTitleInput(String text) {
        enterTextIntoElement(inputTitle, text);
        return this;
    }

    public CreatePostPage enterTextInToBodyInput(String text) {
        enterTextIntoElement(inputBody, text);
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

    public PostPage clickOnButtonSaveNewPost() {
        clickOnElement(buttonSaveNewPost);
        return new PostPage(webDriver);
    }

    public CreatePostPage selectRoleInDropdownByText(String text){
        selectTextInDropDownByUI(dropDownRole, text);
        return  this;
    }

    public CreatePostPage setCheckboxValue(String value) {
        setCheckbox(inputCheckbox, value);
        return this;
    }
}
