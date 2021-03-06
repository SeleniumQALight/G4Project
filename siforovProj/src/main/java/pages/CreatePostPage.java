package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CreatePostPage extends ParentPageWithHeader {

    @FindBy(id = "post-title")
    private WebElement titleInput;

    @FindBy(id = "post-body")
    private WebElement textAreaInput;

    @FindBy(xpath = ".//input[@type='checkbox']")
    private WebElement createPostCheckBox;

    @FindBy(tagName = "select")
    private WebElement dropDownRole;

    @FindBy(xpath = ".//button[text()='Save New Post']")
    private WebElement buttonSaveNewPost;

    @FindBy(xpath = ".//select[@name='select1']/option")
    private List<WebElement> selectOptions;

    public CreatePostPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/create-post";
    }

    public CreatePostPage checkIsRedirectToCreatePostPage() {
        waitChatToBeHide();
        checkUrl();
        Assert.assertTrue("The Create Post page is not reached", elementIsVisible(titleInput));
        return this;
    }

    public CreatePostPage enterTextIntoTitleInput(String text) {
        enterTextIntoElement(titleInput, text);
        return this;
    }

    public CreatePostPage enterTextIntoBody(String text) {
        enterTextIntoElement(textAreaInput, text);
        return this;
    }

    //Homework with checkbox
    public CreatePostPage markCheckbox(boolean condition) {
        System.out.println(createPostCheckBox.isSelected());
        if(createPostCheckBox.isSelected() == condition) {
            logger.info("The check-box matches you request already");
        }
        clickOnElement(createPostCheckBox);
        logger.info("Check-box has been clicked");
        return this;
    }

    public CreatePostPage selectTextInDropDownCreatePost(String textForSelect) {
        selectTextInDD(dropDownRole, textForSelect);
        return this;
    }

    public CreatePostPage selectValueInDropDownRole(String valueForSelect) {
        selectValueInDropDown(dropDownRole, valueForSelect);
        return this;
    }

    public CreatePostPage selectValueInDDUsingList(String optionForSelect) {
        selectTextInDropDownByUI(selectOptions, optionForSelect);
        return this;
    }

    public PostPage clickOnSaveNewPostButton() {
        clickOnElement(buttonSaveNewPost);
        return new PostPage(webDriver);
    }
}
