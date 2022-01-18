package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreatePostPage extends ParentPageWithHeader {
    //.//*[@name='title']
    @FindBy(name = "title")
    private WebElement inputTitle;

    @FindBy(id = "post-body")
    private WebElement inputBody;

    @FindBy(tagName = "select")
    private WebElement dropDownRole;

    @FindBy(xpath = ".//*[text()='Save New Post']")
    private WebElement buttonSaveNewPost;

    @FindBy(xpath = ".//*[text()='Частное сообщение']")
    private WebElement textOnePersonInDropDown;

    @FindBy(xpath = ".//*[text()='Общедоступное']")
    private WebElement textGroupMessage;

    @FindBy(xpath = ".//input[@type='checkbox']")
    private WebElement checkBox;


    public CreatePostPage(WebDriver webDriver) {
        super(webDriver);
    }

    public CreatePostPage checkIsRedirectToCratePostPage() {
        waitChatTobeHide();
        Assert.assertTrue("InputTitle is not displayed"
                , isElementDisplayed(inputTitle));
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

    //   public CreatePostPage selectTextInDropDownRole(String textForSelect) {
    //      selectTextInDropDown(dropDownRole, textForSelect);
    //      return this;
    //  }

    public CreatePostPage selectValueInDropDownRole(String valueForSelect) {
        selectValueInDropDown(dropDownRole, valueForSelect);
        return this;
    }

    public CreatePostPage selectTextInDropDownRoleByUi(String text) {
        selectTextInDropDownByUI(dropDownRole,textOnePersonInDropDown );

        return this;
    }

    public CreatePostPage clickOnDropDownRole() {
        clickOnEltment(dropDownRole);
        return this;
    }

    public CreatePostPage clickOnTextOnePersonInDropDown() {
        clickOnEltment(textOnePersonInDropDown);
        return this;
    }

    public CreatePostPage checkFieldSelectIsDisplayed() {

        Assert.assertTrue("Select is not displayed", isElementDisplayed(dropDownRole));
        return this;
    }

    public CreatePostPage checkFieldCheckBoxIsDisplayed() {
        Assert.assertTrue("Checkbox is not displayed", isElementDisplayed(checkBox));
        return this;
    }

    public CreatePostPage clickOnCheckBox() {

        clickOnEltment(checkBox);
        return this;
    }

    public PostPage clickOnButtonSaveNewPost() {
        clickOnEltment(buttonSaveNewPost);
        return new PostPage(webDriver);
    }
}
