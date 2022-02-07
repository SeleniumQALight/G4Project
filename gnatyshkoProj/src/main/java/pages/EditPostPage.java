package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EditPostPage extends ParentPageWithHeader {

    @FindBy(xpath = ".//input[@id='post-title']")
    private WebElement postTitle;

    @FindBy(xpath = ".//*[text()='Save Updates']")
    private WebElement buttonSaveUpdates;

    @FindBy(xpath = ".//*[@class='alert alert-success text-center']")
    private WebElement alertUpdateSuccess;

    public EditPostPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/edit/";
    }

    public EditPostPage editPostTitle(String text){
        enterTextIntoElement(postTitle, text);
        return this;
    }

    public EditPostPage clickOnButtonSaveEditedPost() {
        clickOnElement(buttonSaveUpdates);
        return this;
    }

    public EditPostPage checkTextInAlert(String text) {
        Assert.assertEquals("Text in Alert ", text, alertUpdateSuccess.getText());
        return this;
    }
}
