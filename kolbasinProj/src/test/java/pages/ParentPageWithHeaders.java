package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ParentPageWithHeaders extends ParentPage{
    @FindBy(xpath = ".//a[text()='Create Post']")
    private WebElement buttonCreatePost;

    public ParentPageWithHeaders(WebDriver webDriver) {
        super(webDriver);
    }

    public void clickOnCreatePostButton(){
        clickOnElement(buttonCreatePost);
    }
}
