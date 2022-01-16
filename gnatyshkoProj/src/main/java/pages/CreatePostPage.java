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

    @FindBy(xpath = ".//select[@name='select1']")
    private WebElement dropDownMenu;

    @FindBy(xpath = ".//input[@type='checkbox']")
    private WebElement checkBox;

    public CreatePostPage(WebDriver webDriver) {
        super(webDriver);
    }

    public CreatePostPage checkIsRedirectToCreatePostPage() {
        waitChatToBeHide();
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

    public CreatePostPage selectTextInDropDownRole(String textForSelect) {
        selectTextInDropDown(dropDownRole, textForSelect);
        return this;
    }

    public CreatePostPage selectTextInDropDownByUi(String textForSelect) {
        selectTextInDropDownUi(dropDownMenu, textForSelect);
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

    public CreatePostPage clickOnCheckBox() {
        Assert.assertTrue("CheckBox is not displayed", checkBox.isDisplayed());
        try {
            if (checkBox.isSelected() == false) {
                checkBox.click();
            } else if (checkBox.isSelected() == true) {
                System.out.println("CheckBox is selected already");
            } else if (!checkBox.isSelected() == true) {
                checkBox.click();
            } else if (!checkBox.isSelected() == false) {
                System.out.println("CheckBox is selected already");
            }
            logger.info("CheckBox is active");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
        return new CreatePostPage(webDriver);
    }
}
