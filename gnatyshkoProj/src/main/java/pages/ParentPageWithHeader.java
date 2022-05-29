package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

abstract public class ParentPageWithHeader extends ParentPage{
    @FindBy(xpath = ".//button[text()='Sign Out']")
    protected WebElement buttonSignOut;

    @FindBy(xpath = ".//a[text()='Create Post']")
    private WebElement buttonCreatePost;

    @FindBy(xpath = ".//*[@data-original-title='My Profile']")
    private WebElement buttonMyProfile;

    public ParentPageWithHeader(WebDriver webDriver) {
        super(webDriver);
    }

    public CreatePostPage clickOnCreatePostButton(){
        clickOnElement(buttonCreatePost);
        return new CreatePostPage(webDriver);
    }

    public MyProfilePage clickOnMyProfileButton (){
        waitChatToBeHide();
        clickOnElement(buttonMyProfile);
        return new MyProfilePage(webDriver);
    }


}
