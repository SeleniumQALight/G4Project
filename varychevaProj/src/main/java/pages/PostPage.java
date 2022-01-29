package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PostPage extends ParentPageWithHeader {
    @FindBy(xpath = ".//*[@class='alert alert-success text-center']")
    private WebElement alertSuccess;

    @FindBy(xpath = ".//a[@data-original-title='Edit']")
    private WebElement editButton;

    public PostPage(WebDriver webDriver) {
        super(webDriver);
    }

    public PostPage checkIsRedirectToPostPage() {
        waitChatTobeHide();
        checkUrlWithPattern();
        assertTrue("Edit Button is not Displayed."
                , isElementDisplayed(editButton));
        return this;
    }

    public PostPage checkTextInAlert(String text) {
        assertEquals("Text in Alert ", text, alertSuccess.getText());
        return this;
    }

    @Override
    String getRelativeUrl() {
        return "/post/";
    }
}
