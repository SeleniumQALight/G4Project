package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

abstract public class ParrentPageWithHeader extends ParentPage{
    @FindBy(xpath = ".//a[text()='Create Post']")
    private WebElement buttonCreatePost;

    @FindBy(xpath = ".//*[@data-original-title='My Profile']")
    private WebElement buttonMyProfile;

    public ParrentPageWithHeader(WebDriver driver) {
        super(driver);
    }

   public CreatePostPage clickOnCreatePostButton(){
        clickOnElement(buttonCreatePost);
        return new CreatePostPage(driver);
    }

    public MyProfilePage clickOnMyProfileButton() {
        waitChatToBeHide();
        clickOnElement(buttonMyProfile);
        return new MyProfilePage(driver);
    }
}
