package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PostPage extends ParentPageWithHeader{
    @FindBy (xpath = ".//a[@data-original-title='Edit']")
    private WebElement editButton;

    @FindBy(xpath = ".//*[@class='alert alert-success text-center']")
    private WebElement alertSuccess;

    public PostPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/post/";
    }

    public PostPage checkIsRedirectToPostPage() {
        WaitChatToBeHide();
        checkUrlWithPattern();
        Assert.assertTrue("Edit Button is not Displayed", isElementDisplayed(editButton));

    return this;
    }


    public PostPage checkTextInAlert(String text){
        Assert.assertEquals("Text in Alert",text, alertSuccess.getText());
        return this;
    }
}
