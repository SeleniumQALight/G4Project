package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreatePostPage extends ParentPageWithHeader{
    // .//*[@name='title']
    @FindBy(name = "title")
    private WebElement inputTitle;
    @FindBy(id = "post-body")
    private WebElement inputBody;
    @FindBy(tagName = "select")
    private WebElement dropDownRole;
    @FindBy(xpath = ".//*[text()='Save New Post']")
    private WebElement clickOnButtonSaveNewPost;
    @FindBy(xpath = ".//select")
    private WebElement clickOnItemPrivateMessage;

    public CreatePostPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRalativeUrl() {
        return "/create-post";
    }

    public CreatePostPage checkIsRedirectToCreatePostPage(){
        waitChatTobeHide();
        checkUrl();
        Assert.assertTrue("InputTitle is not displayed", isElementDisplayed(inputTitle));
        return this;
    }


    public CreatePostPage enterTextIntoTitleInput(String text) {
        enterTextIntoElement(inputTitle, text);
        return this;
    }

    public CreatePostPage enterTextIntoBody(String text) {
        enterTextIntoElement(inputBody, text);
        return this;
    }

    public CreatePostPage selectTextInDropDownRole(String textForSelect) {
        selectTextInDropDown(dropDownRole, textForSelect);
        return this;
    }

    public CreatePostPage selectValueDropdownRole(String valueForSelect) {
        sectValueInDropdown(dropDownRole, valueForSelect);
        return this;
    }

    public PostPage clickOnButtonSaveNewPost() {
        clickOnElement(clickOnButtonSaveNewPost);
        return new PostPage(webDriver);
    }

    public CreatePostPage selectTextInDropDownByUI(String valueForSelectTextInDropDownByUI) {
        try {
            clickOnElement(clickOnItemPrivateMessage);
            WebElement elementInMenu = webDriver.findElement(By.xpath(".//*[text()='" + valueForSelectTextInDropDownByUI + "']"));
            clickOnElement(elementInMenu);
        }catch (Exception e) {
            logger.error("Can not found" + e);
            Assert.fail("Can not found" + e);
        }
        return this;
    }

}
