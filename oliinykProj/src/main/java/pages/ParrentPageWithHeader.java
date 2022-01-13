package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ParrentPageWithHeader extends ParentPage{
    @FindBy(xpath = ".//a[text()='Create Post']")
    private WebElement buttonCreatePost;

    public ParrentPageWithHeader(WebDriver driver) {
        super(driver);
    }

    public CreatePostPage clickOnCreatePostButton(){
        clickOnElement(buttonCreatePost); return new CreatePostPage(driver);
    }
}
