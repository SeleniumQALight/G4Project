package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ParentPageWithHeader extends ParentPage{
    @FindBy(xpath = ".//a[text()='Create Post']")
    private WebElement buttonCreatePost;
    public ParentPageWithHeader(WebDriver webDriver) {
        super(webDriver);
    }
public void clickOnCreatePostButton(){
        clickOnElement(buttonCreatePost);

}

}
