package pages;

import libs.TestData;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

abstract public class ParentPageWithHeader extends ParentPage{
    @FindBy (xpath = ".//a[text()='Create Post']")
    private WebElement createPostButton;

    @FindBy(xpath = ".//*[@data-original-title='My Profile']")
    protected WebElement buttonProfile;

    public static String postTitle = "";

    public ParentPageWithHeader(WebDriver webDriver) {
        super(webDriver);
    }

    public CreatePostPage clickOnCreatePostButton(){
        clickOnElement(createPostButton);
        return new CreatePostPage(webDriver);
    }

    public ProfilePage clickOnProfile(){
        clickOnElement(buttonProfile);
        return new ProfilePage(webDriver);
    }

    public void createPost(){
        clickOnCreatePostButton()
                .checkIsRedirectToCreatePostPage()
                .enterTextIntoTitleInput(TestData.VALID_POST_TITLE)
                .enterTextIntoBody(TestData.VALID_TEXT)
                .selectValueInDDUsingList("One Person")
                .clickOnSaveNewPostButton()
                .clickOnProfile();
    }
}
