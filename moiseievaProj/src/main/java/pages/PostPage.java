package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PostPage extends ParentPageWithHeader{

    @FindBy(xpath = "//*[@class='alert alert-success text-center']")
    private WebElement alertSuccess;

    @FindBy(xpath = "//a[@data-original-title='Edit']")
    private WebElement editButton;

    public PostPage(WebDriver webDriver) {
        super(webDriver);
    }

    public PostPage checkIsRedirectPostPage(){
        waitChatToBeHide();
        Assert.assertTrue("Edit button is not displayed." , isElementDisplayed(editButton));
        return this;
    }

    public PostPage checkTextInAlert(String text){
        Assert.assertEquals("Text in alert ", text, alertSuccess.getText());
        return this;
    }


}
