package pages;

import libs.TestData;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EditPostPage extends ParentPage{

    @FindBy(id = "post-title")
    private WebElement titleInput;

    @FindBy(id = "post-body")
    private WebElement textAreaInput;

    @FindBy(xpath = ".//input[@type='checkbox']")
    private WebElement createPostCheckBox;

    @FindBy(tagName = "select")
    private WebElement dropDownRole;

    @FindBy(xpath = ".//a[@class='small font-weight-bold']")
    private WebElement bakToPostsPermalink;

    @FindBy(xpath = ".//button[@class='btn btn-primary']")
    private WebElement saveUpdateButton;

    @FindBy(xpath = ".//*[text()='Post successfully updated.']")
    private WebElement postUpdatedSuccessText;

    public EditPostPage(WebDriver webDriver) {
        super(webDriver);
    }

    public EditPostPage checkIsRedirectedToEditPage(){
        checkUrl();
        Assert.assertTrue("",elementIsVisible(bakToPostsPermalink));
        return this;
    }

    public EditPostPage clearTitleAndUpdateItWithNewValue() {
        titleInput.clear();
        enterTextIntoElement(titleInput, TestData.VALID_POST_TITLE_AFTER_UPDATE);
        return this;
    }

    public EditPostPage clickOnSaveUpdatesButton(){
        clickOnElement(saveUpdateButton);
        return this;
    }

    @Override
    String getRelativeUrl() {
        return "/edit";
    }
}
