package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MyProfilePage extends ParentPageWithHeader {
   private String postTitleLocator= ".//*[text()='%s']";


   @FindBy(xpath = ".//*[text()='Post successfully deleted']")
    private WebElement successDeletedPostMassage;

    public MyProfilePage(WebDriver webDriver) {
        super(webDriver);
    }

    public MyProfilePage checkPostWasCreate(String title) {
        List<WebElement>postsList = webDriver.findElements(
                By.xpath(String.format(postTitleLocator, title)));
        Assert.assertEquals("Number of posts with title "+title,1,postsList.size());
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
        List<WebElement>listOfPost=webDriver.findElements(
                By.xpath(String.format(postTitleLocator,title)));
        int counter=0;
        while (listOfPost.isEmpty()&& counter<10){
            clickOnElement(webDriver.findElement(By.xpath(String.format(postTitleLocator,title))));
            new PostPage(webDriver)
                    .checkIsRedirectToPostPage()
                    .clickOnDeleteButton()
                    .checkIsSuccessDeletedPostMassagePresent();
            logger.info(" Post was deleted ");
            listOfPost= webDriver.findElements(By.xpath(String.format(postTitleLocator,title)));
            counter++;
        }
        logger.info(" All posts were deleted with " + title);
        return this;
    }

    private MyProfilePage checkIsSuccessDeletedPostMassagePresent() {
        Assert.assertTrue(" Element is not present ", isElementDisplayed(successDeletedPostMassage));
        return this;
    }
}
