package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Arrays;
import java.util.List;

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

    @FindBy(xpath = ".//option[@value='One Person']")
    private WebElement dropdownText;

    private String dropDown = ".//option[text()='%s']";


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

    //выбор текста в DropDown
//    public CreatePostPage selectTextInDropDownByUI(String text){
//        List<WebElement> dropdownOption =  webDriver.findElements(By.xpath(String.format(dropDown, text)));
//        Assert.assertEquals("Text in Drop Down " + text + " not find", 1, dropdownOption.size());
//       selectTextInDropDown(dropDownRole, text);
//        clickOnElement(dropDownRole);
//       // clickOnElement(dropdownOption);
//        return  this;
//    }
    public CreatePostPage selectTextInDropDownByUI(){
        clickOnElement(dropDownRole);
        clickOnElement(dropdownText);
        return  this;
    }


    public CreatePostPage setCheckboxValueCreatePost (String value){
        setCheckboxValue(value);
        return this;
    }


    public PostPage clickOnButtonSaveNewPost() {
        clickOnElement(buttoSaveNewPost);
        return new PostPage(webDriver);
    }
}
