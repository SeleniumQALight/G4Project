package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MyProfilePage extends ParentPageWithHeader{
   private String postTitleLocator = ".//*[text()='%s']";

   @FindBy (xpath = ".//*[text()='Post successfully deleted']")
    private WebElement successfulDeletedPostMessage;

   @FindBy(xpath = ".//a[@class='list-group-item list-group-item-action']")
    private List<WebElement> postsList;

    public MyProfilePage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/profile/";
    }


    public MyProfilePage checkPostIsInList(String title) {
        List<WebElement> postList = webDriver.findElements(By.xpath(String.format(postTitleLocator, title)));
        Assert.assertEquals("Number of posts with title " + title, 1, postList.size());
        return this;
    }

    public MyProfilePage checkIsRedirectToMyProfilePage() {
        checkUrlWithPattern();
        waitChatToBeHidden();
        return  this;
    }

    public MyProfilePage deletePostWithTitleWhilePresent(String title) {
        List<WebElement> listOfPost = webDriver.findElements(By.xpath(String.format(postTitleLocator, title)));

        int counter = 0;
        while (!listOfPost.isEmpty() && counter<10){
            clickOnElement(webDriver.findElement(By.xpath(String.format(postTitleLocator, title))));
            new PostPage(webDriver)
                    .checkIsRedirectToPostPage()
                    .clickOnDeleteButton()
                    .checkIsSuccessfulDeletedPostMessagePresent();
            logger.info("Post was deleted");
            listOfPost = webDriver.findElements(By.xpath(String.format(postTitleLocator, title)));
            counter++;

        }
        logger.info("All posts were deleted with title " + title);
        return this;
    }

    public PostPage clickOnPostTitle (String title) {
        clickOnElement(String.format(postTitleLocator, title));
        return new PostPage (webDriver);
    }



    private MyProfilePage checkIsSuccessfulDeletedPostMessagePresent() {
        Assert.assertTrue("Element is not present", isElementDisplayed(successfulDeletedPostMessage));
        return this;
    }

    public void checkNumberOfPosts(int expectedNumberOfPosts) {
        Assert.assertEquals("Number of posts ", expectedNumberOfPosts, postsList.size());
    }
}
