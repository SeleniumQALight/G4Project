package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreatePostPage extends ParrentPageWithHeader{
    @FindBy(id = "post-title")
    private WebElement inputTitle;

    @FindBy(id="post-body")
    private WebElement inputBody;

    @FindBy(tagName = "select")
    private WebElement dropDownRole;

    @FindBy(xpath = ".//*[text()='Save New Post']")
    private WebElement buttonSaveNewPost;

    @FindBy(xpath = ".//option[@value='One Person']")
    private WebElement valueOnePerson;

    @FindBy(xpath = ".//input[@type='checkbox']")
    private WebElement checkBoxUnique;

    public CreatePostPage(WebDriver driver) {
        super(driver);
    }

    public CreatePostPage checkRedirectToCreatePostPage(){
        waitChatToBeHide();
        Assert.assertTrue("Input 'Title' isn't displayed", isElementDisplayed(inputTitle));
        return this;
    }

    public CreatePostPage enterTextIntoTitleInput(String text) {
        enterTextIntoElement(inputTitle, text);
        return this;
    }

    public CreatePostPage enterTextIntoBodyInput(String text) {
        enterTextIntoElement(inputBody, text);
        return this;
    }

    public CreatePostPage selectTextInDropDownRole(String textForSelect) {
        selectTextInDropDown(dropDownRole, textForSelect);
        return this;
    }

    public CreatePostPage selectValueInDropdownRole(String valueForSelect) {
        selectValueInDropdown(dropDownRole,valueForSelect);
        return this;
    }

    public PostPage clickOnButtonSaveNewPost() {
        clickOnElement(buttonSaveNewPost);
        return new PostPage(driver);
    }

    public CreatePostPage selectTextInDropDownByUI(){
        clickOnElement(dropDownRole);
        clickOnElement(valueOnePerson);
        return this;
    }

    public CreatePostPage checkBoxActionsByState(String state){
        checkBoxActions(state, checkBoxUnique);
        return this;
    }
}
