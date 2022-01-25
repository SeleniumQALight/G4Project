package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ParentPageWithHeader extends ParentPage{
    @FindBy(xpath = ".//a[text()='Create Post']")
    private WebElement buttonCreatePost;

    @FindBy(xpath = ".//*[@data-original-title='My Profile']")
    private WebElement buttonMyProfile;

    public ParentPageWithHeader(WebDriver webDriver) {
        super(webDriver);
    }

    public CreatePostPage clickOnCreatePostButton(){
        clickElement(buttonCreatePost);
        return new CreatePostPage(webDriver);
    }
//    public MyProfilePage clickOnMyProfileButton(){
//        clickElement(MyProfilePage);
//        return new MyProfilePage(webDriver);
//    }

}
