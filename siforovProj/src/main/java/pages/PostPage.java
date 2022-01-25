package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PostPage extends ParentPageWithHeader{

    @FindBy(xpath = ".//*[@class='alert alert-success text-center']")
    private WebElement successTextPostCreated;

    @FindBy(xpath = ".//a[@class='text-primary mr-2']")
    private WebElement editButton;

    public PostPage(WebDriver webDriver) {
        super(webDriver);
    }

    public PostPage checkIsRedirectToPostPage(){
        Assert.assertTrue("The Edit button is not displayed", elementIsVisible(editButton));
        return this;
    }

    public PostPage checkTextInAlert(String text){
        Assert.assertEquals("Text in Alert ",text, successTextPostCreated.getText());
        return this;
    }

}
