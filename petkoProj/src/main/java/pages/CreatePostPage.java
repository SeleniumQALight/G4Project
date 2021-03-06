package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreatePostPage extends ParentPageWithHeader{
    // .//*[name = 'title']
    @FindBy(name = "title")
    private WebElement inputTitle;

    @FindBy(id = "post-body")
    private WebElement inputBody;

    @FindBy(tagName = "select")
    private WebElement dropDownRole;

    @FindBy(xpath = ".//*[text()='Save New Post']")
    private WebElement buttonSaveNewPost;

    @FindBy(xpath = ".//option[@value='One Person']")
    private WebElement valueOnePerson;

    @FindBy(xpath = ".//input[@type='checkbox']")
    private WebElement checkBoxUniquePost;

    public CreatePostPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/create-post";
    }

    public CreatePostPage checkIsRedirectToCreatePostPage(){
        waitChatToBeHide();
        checkUrl();
        Assert.assertTrue("Input Title is not displayed", isElementDisplayed(inputTitle));
        return this;
    }

    public CreatePostPage enterTextIntoTitleInput(String text) {
        enterTextInToElement(inputTitle, text);
        return this;
    }

    public CreatePostPage enterTextIntoBodyInput(String text) {
        enterTextInToElement(inputBody, text);
        return this;
    }

    public CreatePostPage selectTextInDropDownRole(String textForSelect) {
        selectTextInDropDown(dropDownRole, textForSelect);
        return this;
    }

    public CreatePostPage selectValueInDropDownRole(String valueForSelect) {
        selectValueInDropDown(dropDownRole,valueForSelect);
        return this;
    }

    public CreatePostPage selectTextInDropDownByUI(){
        clickOnElement(dropDownRole);
        clickOnElement(valueOnePerson);
        return this;
    }

    public CreatePostPage checkBoxActionsByState(String state){
        checkBoxActions(state, checkBoxUniquePost);
        return this;
    }

    public PostPage clickOnButtonSaveNewPost() {
        clickOnElement(buttonSaveNewPost);
        return  new PostPage(webDriver);
    }
}
