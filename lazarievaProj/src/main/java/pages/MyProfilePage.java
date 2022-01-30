package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MyProfilePage extends ParentPageWithHeader {
    final String postTitleLocator = ".//*[text()='%s']";
    @FindBy(xpath = ".//*[text()='Post successfully deleted']")
    private WebElement successDeletedPostMessage;
    //%S дає можливість джаві підставляти значення

    public MyProfilePage(WebDriver webDriver) {
        super(webDriver);
    }

    public MyProfilePage checkPostWasCreated(String title) {
        List<WebElement> postLists = webDriver.findElements(
                By.xpath(String.format(postTitleLocator, title)));
        Assert.assertEquals("Number of posts with title  " + title, 1, postLists.size());
        return this;
    }

    @Override
    String getRelativeUrl() {
        return "/profile";
    }


    public MyProfilePage checkIsRedirectToMyProfilePage() {
        checkUrlWithPattern();
        waitChatTobeHide();
        return this;
    }

    public MyProfilePage deletePostWithTitleWhilePresent(String title) {
        List<WebElement> listOfPosts = webDriver.findElements(
                By.xpath(String.format(postTitleLocator, title))
        );
        //!!!
        int counter =0;
        while (!listOfPosts.isEmpty() && counter<10) {
            clickOnElement(webDriver.findElement(By.xpath(String.format(postTitleLocator, title))));
            new PostPage(webDriver)
                    .checkIsRedirectToPostPage()
                    .clickDeleteButton()
                    .checkIsSuccessDeletedPostMessagePresent();
            logger.info("Post was deleted");
            // провіряємо чи умова актуальна
            listOfPosts = webDriver.findElements(By.xpath(String.format(postTitleLocator, title)));

        }
        logger.info("All posts were deleted with " + title);
        return this;
    }

    private MyProfilePage checkIsSuccessDeletedPostMessagePresent() {
        Assert.assertTrue("Element is not present", isElementDisplayed(successDeletedPostMessage));
        return this;
    }
}
