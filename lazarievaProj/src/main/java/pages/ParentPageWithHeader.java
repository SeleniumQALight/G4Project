package pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

abstract public class ParentPageWithHeader extends ParentPage {
    //public class ParentPageWithHeader extends ParentPage {
    @FindBy(xpath = ".//a[@href ='/create-post']")
    private WebElement buttonCreatePost;
    @FindBy(xpath = ".//*[@data-original-title='My Profile']")
    private  WebElement buttonMyProfile;


    public ParentPageWithHeader(WebDriver webDriver) {
        super(webDriver);
    }

    public CreatePostPage clickOnCreatePostButton(){
        clickOnElement(buttonCreatePost);
        return new CreatePostPage(webDriver);
    }

    public MyProfilePage clickOnMyProfile()
    {
        clickOnElement(buttonMyProfile);
        return new MyProfilePage(webDriver);
    }

}
