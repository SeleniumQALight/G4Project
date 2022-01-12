package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreatePostPage extends ParentPageWithHeader{

    //.//*[@name='title']
    @FindBy(name = "title")
    private WebElement inputTitle;

    @FindBy(id = "post-body")
    private WebElement inputBody;

    @FindBy(tagName = "select")
    private  WebElement dropDownRole;

    @FindBy(xpath = ".//*[text()='Save New Post']")
    private WebElement buttoSaveNewPost;



    public CreatePostPage(WebDriver webDriver) {
        super(webDriver);
    }
    public CreatePostPage checkIsRedirectToCreatePostPage(){
        waitChatTobeHide();
        Assert.assertTrue("InputTitle is not displayed", isElementDispleid(inputTitle));// проверить есть ли елемент
        return this;
    }

    public CreatePostPage entrTextInToTitleInput(String text) {
        enterTextInToElement(inputTitle, text);
        return this;
    }
    public CreatePostPage enterTextInBodyInput (String text){
        enterTextInToElement(inputBody, text);
        return this;
    }

    public CreatePostPage selectTextInDropDownRole(String textForSelect){
        selectTextInDropDown(dropDownRole, textForSelect);
        return  this;
    }

    public CreatePostPage selectValueInDropDown(String valueForSelect) {
        selectValueInDropDown(dropDownRole, valueForSelect);
        return this;
    }

    public PostPage clickOnButtonSaveNewPost() {
        clickOnElement(buttoSaveNewPost);
        return new PostPage(webDriver);
    }
}
