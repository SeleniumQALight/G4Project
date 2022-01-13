package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ParentPageWithHeader extends ParentPage{
    @FindBy(xpath = ".//button[text()='Sign Out']")
    protected WebElement buttonSignOut;

    @FindBy(xpath = ".//a[text()='Create Post']")
    private WebElement buttonCreatePost;

    public ParentPageWithHeader(WebDriver webDriver) {
        super(webDriver);
    }

    public CreatePostPage clickOnCreatePostButton(){
        clickOnElement(buttonCreatePost);
        return new CreatePostPage(webDriver);
    }


}
