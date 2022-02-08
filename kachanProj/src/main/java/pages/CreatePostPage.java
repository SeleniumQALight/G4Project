package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreatePostPage extends ParentPageWithHeader {
    @FindBy(name = "title")
    private WebElement inputTitle;
    @FindBy(id ="post-body")
    private WebElement inputBody;
    @FindBy(tagName = "select")
    private WebElement dropDownRole;
    @FindBy(xpath =".//*[text()='Save New Post']")
    private WebElement buttonSaveNewPost;
    @FindBy(xpath = ".//option[@value='One Person']")
    private WebElement dropDownOnePerson;
    @FindBy(xpath = ".//input[ @id='”UniquePost”']")
    private WebElement checkBox;

    public CreatePostPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRalativeUrl() {
        return "/create-post";
    }

    public CreatePostPage checkIsRedirectToCreatePostPage (){
        waitChatTobeHide();
        checkUrl();
        Assert.assertTrue("InputTitle is not displayed", isElementDisplayed(inputTitle));
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

    public CreatePostPage selectvalueInDropDownRole(String valueForSelect) {
        selectValueInDropDown(dropDownRole,valueForSelect);
        return this;
    }

    public PostPage clickOnButtonSaveNewPost() {
        clickOnElement(buttonSaveNewPost);
        return new PostPage(webDriver);
    }

    public CreatePostPage selectTextInDropDownByUI () {
        clickOnElement(dropDownRole);
        clickOnElement(dropDownOnePerson);
        return this;
    }


    public CreatePostPage setCheckBox( String state) {
        boolean initialState = checkBox.isSelected();

        if ((state.equalsIgnoreCase("check") && initialState)) {
            logger.info("Checkbox is already checked");
        }
        else if ((state.equalsIgnoreCase("uncheck") && !initialState)) {
            logger.info("Checkbox is already unchecked");
        }else {
            clickOnElement(checkBox);
            logger.info("Changed checkbox state to " + state);
        }
        return this;
    }
}
