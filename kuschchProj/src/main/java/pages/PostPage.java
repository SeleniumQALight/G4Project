package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PostPage extends ParentPageWithHeader{

    @FindBy(xpath = ".//*[@class='alert alert-success text-center']")
    private WebElement alertSucces;
    @FindBy(xpath = ".//a[@data-original-title='Edit']")
    private WebElement editButton;

    @FindBy(xpath = ".//button[@data-original-title='Delete']")
    private WebElement buttonDelete;

    public PostPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRalativeUrl() {
        return "/post/";
    }

    public PostPage checkIsRedirectToPostPage(){
        waitChatTobeHide();
        checkUrlWithPattern();
        Assert.assertTrue("Edit Button is notDisplayed", isElementDisplayed(editButton));
        return this;
    }

    public PostPage checkTestInAlert(String text){
        Assert.assertEquals("Text in Alert ", text, alertSucces.getText());
        return this;
    }

    public MyProfilePage clickOnDeleteButton() {
        clickOnElement(buttonDelete);
        return new MyProfilePage(webDriver);
    }

    public EditPostPage clickOnEditBtn(){
        clickOnElement(editButton);
        return new  EditPostPage(webDriver);
    }

}
