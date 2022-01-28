package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MyProfilePage extends ParrentPageWithHeader{
    private String postTitleLocator = ".//*[text()='%s']";

    @FindBy(xpath = ".//*[text()='Post successfully deleted']")
    private WebElement PostDeleteMessage;

    public MyProfilePage(WebDriver driver) {
        super(driver);
    }

    public MyProfilePage checkPostWasCreated(String title) {
        List<WebElement> postsList = driver.findElements(By.xpath(String.format(postTitleLocator, title)));
        Assert.assertEquals("Number of post with title " + title, 1, postsList.size());
        return this;
    }

    @Override
    String getRelativeUrl() {
        return "/profile/";
    }

    public MyProfilePage checkIsRedirectToMyProfilePage() {
        checkUrlWithPattern();
        waitChatToBeHide();
        return this;
    }

    public MyProfilePage deletePostWithTitleWhilePresent(String title) {
        List<WebElement> listOfPosts = driver.findElements(By.xpath(String.format(postTitleLocator, title)));
        int counter = 0;
        while (!listOfPosts.isEmpty() && counter < 10){
            clickOnElement(driver.findElement(By.xpath(String.format(postTitleLocator, title))));
            new PostPage(driver).checkIsRedirectToPostPage().clickOnDeleteButton()
                    .checkPostDeleteMessagePresent();
            listOfPosts = driver.findElements(By.xpath(String.format(postTitleLocator, title)));
            counter++;
        }

        logger.info("All posts with title" + title + " was deleted ");
        return this;
    }

    private MyProfilePage checkPostDeleteMessagePresent() {
        Assert.assertTrue("Element is not present", isElementDisplayed(PostDeleteMessage));
        return this;
    }
}
