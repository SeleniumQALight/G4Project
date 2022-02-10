package pages;
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

    @FindBy(xpath = "//select[@name='select1']/option[@value='Group Message']")
    private WebElement groupSelectorInDropDown;

    @FindBy(xpath = "//*[text() = 'Save New Post']")
    private WebElement buttonSaveNewPost;

    @FindBy(xpath = "//input[@type='checkbox']")
    private WebElement Checkbox;
    @FindBy (xpath = ".//a[@data-original-title='Edit']")
    private WebElement editButton;
    @FindBy(xpath = ".//*[@class='alert alert-success text-center']")
    private WebElement alertUpdate;

    @FindBy(xpath = "//button[@class='btn btn-primary']")
    private WebElement saveUpdateButton;

    @FindBy(xpath = ".//*[text()='%s']")
    public WebElement postTitleLocator;

    public CreatePostPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/create-post";
    }

    public CreatePostPage checkIsRedirectToCreatePostPage() {
        Assert.assertTrue("input Title in not found", isElementDisplayed(inputTitle));
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

    public CreatePostPage selectValueInDropDownRole(String valueForSelect) {
        selectValueInDropDown(dropDownRole, valueForSelect);
        return this;
    }

    public CreatePostPage selectTextInDropDownByUI() {
        clickOnElement(dropDownRole);
        clickOnElement(groupSelectorInDropDown);
        return this;
    }

    public PostPage clickOnButtonSaveNewPost() {
        clickOnElement(buttonSaveNewPost);
        return new PostPage(webDriver);
    }

    public CreatePostPage selectValueInCheckBox() {
        clickOnElement(Checkbox);
        return this;
    }
    ///private void clickOnOldTitle(){
      //      clickOnElement(By.xpath(String.format(String.valueOf(postTitleLocator))));
       // return this;
   // }


    public CreatePostPage clickOnEdit(){
        clickOnElement(editButton);
        return this;
    }

    public CreatePostPage enterNewTextInToTitleInput(String title) {
        inputTitle.clear();
        enterTextInToElement(inputTitle, title);
        logger.info("The title has value " + title);
        return this;
    }
    public CreatePostPage clickOnSaveUpdatesButton() {
        clickOnElement(saveUpdateButton);
        logger.info("The Save Updates button was clicked");
        return this;
    }

    public CreatePostPage checkTextInUpdateAlert(String text) {
        Assert.assertEquals("Text in Alert", text, alertUpdate.getText());
        return this;
    }

    }











