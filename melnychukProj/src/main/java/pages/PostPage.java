package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PostPage extends ParentPageWithHeader{
    @FindBy(xpath=".//*[@class='alert alert-success text-center']")
    private WebElement alertSuccess;
    @FindBy(xpath = ".//a[@data-original-title='Edit']")
    private WebElement editButton;

    public PostPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/post/";
    }

    public  PostPage checkIsRedirectToPostPage(){
        waitChatToBeHide();
        checkUrlWithPattern();
        Assert.assertTrue("Edit Button is not displayed. ", isElementDisplayed(editButton));
    return this;
    }
     public PostPage checkTextInAlert(String text){
        Assert.assertEquals("Text in Alert ", text, alertSuccess.getText()); ;
        return this;
     }
}
