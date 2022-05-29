package pages;

import libs.Util;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MyProfilePage extends ParentPageWithHeader {
    private String postTitleLocator = ".//*[text()='%s']";
    @FindBy(xpath = ".//*[text()='Post successfully deleted']")
    private WebElement successDeletedPostMessage;
    @FindBy(name = "title")
    private WebElement inputTitle;
    @FindBy(xpath = ".//a[@class='list-group-item list-group-item-action']")
    private List<WebElement> postsList;

    public MyProfilePage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/profile/";
    }

    public MyProfilePage checkPostWasCreated(String title) {
        List<WebElement> postsList = webDriver.findElements(By.xpath(String.format(postTitleLocator, title)));
        Assert.assertEquals("Number of posts with title " + title, 1, postsList.size());
        return this;
    }

    public MyProfilePage checkIsRedirectToMyProfilePage() {
        checkUrlWithPattern();
        waitChatTobeHide();
        return this;
    }

    public MyProfilePage deletePostWithTitleWhilePresent(String title) {
        List<WebElement> listOfPost = webDriver.findElements(
                By.xpath(String.format(postTitleLocator, title)));
        int counter = 0;
        while (!listOfPost.isEmpty() && counter < 10) {
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

    public MyProfilePage editPostWithTitle(String title) {
        WebElement editablePost = webDriver.findElement(
                By.xpath(String.format(postTitleLocator, title)));
        editablePost.click();
        new PostPage(webDriver)
                .checkIsRedirectToPostPage()
                .clickOnEditButton()
                .enterTextInToElementEdit(inputTitle, "EDIT")
                .clickOnButtonSaveNewPost()
        ;



        logger.info("Post with title '" + title + "' was edited");
        Util.waitABit(5);
        return this;
    }

    private CreatePostPage enterTextInToElementEdit(WebElement inputTitle, String edit) {
        inputTitle.sendKeys(edit);
        logger.info(edit + " was inputted");
        return new CreatePostPage(webDriver);
    }

    private MyProfilePage checkIsSuccessDeletedPostMessagePresent() {
        Assert.assertTrue("Element is not present ", isElementDisplayed(successDeletedPostMessage));

        return this;
    }

    public void checkNumberOfPosts(int expectedNumberOfPosts) {
        Assert.assertEquals("Number of posts ", expectedNumberOfPosts, postsList.size());

    }
}
