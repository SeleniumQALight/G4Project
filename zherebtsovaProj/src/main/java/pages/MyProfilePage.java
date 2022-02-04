package pages;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;


public class MyProfilePage extends ParentPageWithHeader {
    public String postTitleLocator = ".//*[text()='%s']"; //%s-чтоб подставлять значения
    @FindBy(xpath = ".//*[text()='Post successfully deleted']")
    private WebElement successDeletedPostMessage;


    public MyProfilePage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/profile/";
    }

    public void checkPostWasCreated(String title) {
        List<WebElement> postList = webDriver.findElements(
                By.xpath(String.format(postTitleLocator, title)));
        Assert.assertEquals("Number of posts with title" + title, 1, postList.size());
    }

    public MyProfilePage checkIsRedirectToMyProfilePage() {
        checkUrlWithPattern();
        WaitChatToBeHide();
        return this;

    }

    public MyProfilePage deletePostWithTitleWhilePresent(String title) {
        List<WebElement> listOfPost = webDriver.findElements(By.xpath(String.format(postTitleLocator, title)));
        int counter = 0;
        while (!listOfPost.isEmpty() && counter < 10) { //выполняется пока не станет пустой список
            clickOnElement(webDriver.findElement(By.xpath(String.format(postTitleLocator, title))));
            new PostPage(webDriver)
                    .checkIsRedirectToPostPage()
                    .clickOnDeleteButton()
                    .checkIsSuccessDeletedPostMessage();
            logger.info("Post was deleted");
            listOfPost = webDriver.findElements(By.xpath(String.format(postTitleLocator, title)));
            counter++;
        }
        logger.info("All posts were deleted" + title);
        return this;
    }

    private MyProfilePage checkIsSuccessDeletedPostMessage() {
        Assert.assertTrue("Element is not displayed", isElementDisplayed(successDeletedPostMessage));
        return this;
    }

}




