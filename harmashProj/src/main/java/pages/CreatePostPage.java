package pages;

import libs.Util;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreatePostPage extends ParentPageWithHeader {
    @FindBy(name = "title")
    private WebElement inputTitle;
    @FindBy(id = "post-body")
    private WebElement inputBody;
    @FindBy(tagName = "select")
    private WebElement dropDownRole;
    @FindBy(xpath = ".//*[text()='Save New Post']")
    private WebElement buttonSaveNewPost;
    @FindBy(id = "”UniquePost”")
    private WebElement checkboxUniquePost;


    public CreatePostPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/create-post";
    }


    public CreatePostPage checkIsRedirectToCreatePostPage() {
        waitChatTobeHide();
        checkUrl();
        Assert.assertTrue("Input Title is not displayed",
                isElementDisplayed(inputTitle));
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
        selectValueInDropDown(dropDownRole, valueForSelect);
        return this;
    }

    public CreatePostPage selectTextInDropDownByUI(String valueForSelect) {
        dropDownRole.click();
        WebElement secondValue = dropDownRole.findElement(By.xpath(".//*[text()='" + valueForSelect + "']"));
        secondValue.click();
        return this;
    }

    public CreatePostPage toggleCheckbox(String checkBoxStatus) {
        if (checkboxUniquePost.isSelected() && !checkBoxStatus.equals("check") ||
                !checkboxUniquePost.isSelected() && checkBoxStatus.equals("check")) {
            checkboxUniquePost.click();
        }
        logger.info("Checkbox is " + checkBoxStatus + "ed");
        return this;
    }

    public PostPage clickOnButtonSaveNewPost() {
        clickOnElement(buttonSaveNewPost);
        return new PostPage(webDriver);
    }
}
