package pages;

import libs.TestData;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ProfilePage extends ParentPageWithHeader {
    private String postTitleLocator = ".//*[text()='%s']";

    @FindBy(xpath = ".//a[contains(text(),'Posts')]")
    private WebElement postsTab;

    @FindBy(xpath = ".//*[@class='list-group']/a")
    private List<WebElement> postsInTheProfile;

    @FindBy(xpath = ".//*[text()='Post successfully deleted']")
    private WebElement deletePostSuccessText;

    @FindBy(xpath = ".//a[@class='list-group-item list-group-item-action']/strong")
    private List<WebElement> postsList;

    @FindBy(xpath = ".//a[@class='list-group-item list-group-item-action']")
    private WebElement somePost;

    public ProfilePage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/profile/";
    }

    //homework1
    public boolean postTabIsVisible() {
        return elementIsVisible(postsTab);
    }

    public ProfilePage checkPostWasCreated(String title) {
        List<WebElement> postsList = webDriver.findElements(By.xpath(String.format(postTitleLocator, title)));
        Assert.assertEquals("Number of posts with title " + title, 1, postsList.size());
        return this;
    }

    public ProfilePage checkIsRedirectToProfilePage() {
        waitChatToBeHide();
        checkUrlWithPattern();
        return this;
    }
    // Added safe clickOnElement method
    public ProfilePage deletePostWithTitleWhilePresent(String title) {
        List<WebElement> webElementList = webDriver.findElements(By.xpath(String.format(postTitleLocator, title)));

        int counter = 0;
        while(!webElementList.isEmpty() && counter < 20){
            clickOnElement(String.format(postTitleLocator,title));
            new PostPage(webDriver)
                    .checkIsRedirectToPostPage()
                    .clickOnDeleteButton()
                    .checkIsSuccessDeletedPostMessagePresent();
            logger.info("Post was deleted");
            webElementList = webDriver.findElements(By.xpath(String.format(postTitleLocator, title)));
            counter++;
        }
        logger.info("All posts were deleted with title " + title);
        return this;
    }

    private ProfilePage checkIsSuccessDeletedPostMessagePresent() {
        Assert.assertTrue("Element isn't present", elementIsVisible(deletePostSuccessText));
        return this;
    }

    public PostPage clickOnAvailablePost() {

        if(elementIsVisible(somePost)) {
            postTitle = somePost.getText();
            somePost.click();
            return new PostPage(webDriver);
        }else{
            createPost();
            postTitle = somePost.getText();
            somePost.click();
        }
        return new PostPage(webDriver);
    }

    public ProfilePage checkIfUpdatedPostDisplayedInThePostsList() {
        List<WebElement> postsList = webDriver.findElements(By.xpath(".//a[@class='list-group-item list-group-item-action']"));
        for (WebElement element : postsList) {
            if (element.getText().contains(TestData.VALID_POST_TITLE_AFTER_UPDATE)) {
                return this;
            }
        }
        Assert.fail("There is no update post");
        return this;
    }

    public PostPage clickOnEditedPost() {
        clickOnElement(webDriver.findElement(By.xpath(String.format(postTitleLocator,TestData.VALID_POST_TITLE_AFTER_UPDATE))));
        return new PostPage(webDriver);
    }
}
