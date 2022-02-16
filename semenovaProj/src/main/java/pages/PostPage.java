package pages;

import org.junit.Assert;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class PostPage extends ParentPageWithHeader {


    @FindBy(xpath = ".//*[@class='alert alert-success text-center']")
    private WebElement alertSuccess;

    @FindBy(xpath = ".//a[@data-original-title='Edit']")
    private WebElement editButton;

    @FindBy(xpath = ".//button[@data-original-title='Delete']")
    private WebElement buttonDelete;

    @FindBy(xpath = ".//*[@class='btn btn-primary']")
    private WebElement saveUpdates;

    @FindBy(name = "title")
    private WebElement inputTitle;

    public PostPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/post/";
    }

    public PostPage checkIsRedirectToPostPage() {
        waitChatTobeHide();
        checkUrlWithPattern();
        Assert.assertTrue("Edit Button is notDisplayed."
                , isElementDisplayed(editButton));
        return this;
    }

    public PostPage checkTextInAlert(String text) {
        Assert.assertEquals("Text in Alert ", text, alertSuccess.getText());
        return this;
    }

    public MyProfilePage clickOnDeleteButton() {
        clickOnEltment(buttonDelete);
        return new MyProfilePage(webDriver);
    }

    public PostPage clickOnEditButton() {
        clickOnEltment(editButton);
        return this;
    }


    public PostPage clickOnSaveUpdates() {
        isElementDisplayed(saveUpdates);
        clickOnEltment(saveUpdates);
        waitChatTobeHide();
        return this;
    }

    public PostPage changeTitlePost(String changeTitle) {
        enterTextIntoElement(inputTitle, changeTitle);
        return this;
    }
}
