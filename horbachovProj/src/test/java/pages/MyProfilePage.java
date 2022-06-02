package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MyProfilePage extends ParentPageWithHeader{

    private String postTitleLocator = ".//*[text()='%s']";


    @FindBy (xpath=".//*[text()='Post successfully deleted']")
    private WebElement successDeletedPostMessage;

    @FindBy(xpath = ".//a[@class='list-group-item list-group-item-action']")
    private List<WebElement> postsList;


    public MyProfilePage(WebDriver webDriver) {super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/profile/";
    }

    public MyProfilePage checkPostWasCreated (String title) {
        List<WebElement> postList = webDriver.findElements(By.xpath(String.format(postTitleLocator, title)));
        Assert.assertEquals("Number of posts with title " + title, 1, postList.size());
        return this;
    }
    public boolean checkUpdatedPost(String newTitle) {
        try {
            String xpath = String.format(postTitleLocator, newTitle);
            WebElement webElement =  webDriver.findElement(By.xpath(xpath));
           if(isElementDisplayed(webElement)) {
            logger.info("---Updated post is displayed. Passed!!!!---");
            return true;
           }
            }  catch (Exception e) {
            printErrorAndStopTest(e);
        }
        return false;
    }


    public MyProfilePage checkIsRedirectToMyProfilePage() {
        checkUrlWithPattern();
        waitChatToBeHide();
        return this;
    }

    public MyProfilePage deletePostWithTitleWhilePresent(String title) {
        List<WebElement> listOfPost = webDriver.findElements(
                By.xpath(String.format(postTitleLocator,title))
                                                );
        int counter = 0;
        while(!listOfPost.isEmpty() && counter<10){
            clickOnElement(webDriver.findElement(By.xpath(String.format(postTitleLocator, title))));
            new PostPage(webDriver)
                    .checkIsRedirectToPostPage()
                    .clickOnDeleteButton()
                    .checkIsSuccessDeletedPostMessagePresent();
            logger.info("Post was deleted");
            listOfPost = webDriver.findElements(By.xpath(String.format(postTitleLocator, title)));
            counter++;
        }
        logger.info("All posts were deleted with title " + title);
        return this;
    }

    private MyProfilePage checkIsSuccessDeletedPostMessagePresent() {
        Assert.assertTrue("Element is not present", isElementDisplayed(successDeletedPostMessage));
        return this;
    }

    public PostPage openMyLastPost(String title) {
        String xpath = String.format(postTitleLocator, title);
        clickOnElement(xpath);
        return new PostPage(webDriver);
    }

    public void checkNumberOfPosts(int expectedNumberOfPosts) {
        Assert.assertEquals("Number of posts ", expectedNumberOfPosts, postsList.size());
    }
}
