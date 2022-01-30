package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PostPage extends ParrentPageWithHeader{
    @FindBy(xpath = ".//*[@class='alert alert-success text-center']")
    WebElement alertSuccess;

    @FindBy(xpath = ".//a[@data-original-title='Edit']")
    WebElement editButton;

    @FindBy(xpath = ".//button[@data-original-title='Delete']")
    private WebElement deleteButton;

    public PostPage(WebDriver driver) {
        super(driver);
    }

    @Override
    String getRelativeUrl() {
        return "/post/";
    }

    public PostPage checkIsRedirectToPostPage(){
        waitChatToBeHide();
        checkUrlWithPattern();
        Assert.assertTrue("Edit button isn't displayed", isElementDisplayed(editButton));
        return this;
    }

    public PostPage checkTextInAlert(String text){
        Assert.assertEquals("Text in Alert", text, alertSuccess.getText());
        return this;
    }

    public MyProfilePage clickOnDeleteButton() {
        clickOnElement(deleteButton);
        return new MyProfilePage(driver);
    }
}
