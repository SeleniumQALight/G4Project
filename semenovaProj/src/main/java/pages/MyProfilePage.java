package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MyProfilePage extends ParentPageWithHeader {
    String postTitleLocator = ".//*[text()='%s']";

    @FindBy(xpath = ".//*[text()='Post successfully deleted']")
    private WebElement successDeletedPostMessage;


    public MyProfilePage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/profile/";
    }

    public MyProfilePage checkPostWasCreated(String title) {
        List<WebElement> postsList = webDriver.findElements(
                By.xpath(String.format(postTitleLocator, title)));
        Assert.assertEquals("Number of posts with title " + title, 1, postsList.size());
        return this;
    }
    public PostPage enterToPost (String title){
        clickOnElement(String.format(postTitleLocator, title));
        return new PostPage(webDriver);
    }

    public MyProfilePage checkIsRedirectToMyProfilePage() {
        checkUrlWithPattern();
        waitChatTobeHide();
        return this;
    }

    public MyProfilePage deletePostWithTitleWhilePresent(String title) {
        List<WebElement> listOfPosts = webDriver.findElements(
                By.xpath(String.format(postTitleLocator, title)));
        int counter = 0;
        while (!listOfPosts.isEmpty() && counter < 10) {
            clickOnEltment(webDriver.findElement(By.xpath(String.format(postTitleLocator, title))));
            new PostPage(webDriver)
                    .checkIsRedirectToPostPage()
                    .clickOnDeleteButton()
                    .checkIsSuccedDeletedPostMessagePresent();
            logger.info("Post was deleted");
            listOfPosts = webDriver.findElements(By.xpath(String.format(postTitleLocator, title)));
            counter++;
        }
        logger.info("All posts were deleted with title" + title);
        return this;
    }

    private MyProfilePage checkIsSuccedDeletedPostMessagePresent() {
        Assert.assertTrue("Element is not present", isElementDisplayed(successDeletedPostMessage));
        return this;
    }
}
