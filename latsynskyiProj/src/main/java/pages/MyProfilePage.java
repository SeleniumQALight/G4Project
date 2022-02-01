package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MyProfilePage extends ParentPageWithHeader {
    private String postTitleLocator = ".//*[text()='%s']";
    @FindBy(xpath = ".//*[text()='Post successfully deleted']")
    private WebElement succsesDeletedPostMesage;
    @FindBy(xpath = ".//*[text()='Post successfully updated.']")
    private WebElement succsesEditedPostMesage;
    @FindBy(xpath = ".//a[@data-original-title='Edit']")
    private WebElement editPostButton;
    @FindBy(name="title")
    private WebElement inputTitle;
    @FindBy(xpath = ".//button[text()='Save Updates']")
    private WebElement buttonSaveUpdates;
    public MyProfilePage(WebDriver webDriver) {
        super(webDriver);
    }
    public MyProfilePage checkPostWasCreated(String title) {
        List<WebElement> postsList = webDriver.findElements(
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
    waitChatTobeHide();
        return this;
    }

    public MyProfilePage deletePostWithTitleWhilePresent(String title) {
    List<WebElement> listOfPost = webDriver.findElements(
            By.xpath(String.format(postTitleLocator,title))
    );
    int counter = 0;
    while(!listOfPost.isEmpty()&& counter<100){
        clickOnElement(webDriver.findElement(By.xpath(String.format(postTitleLocator,title))));
        new PostPage(webDriver)
                .checkIsRedirectPostPage()
                .clickOnDeleteButton()
                .checkIsSuccessDeletedPostMessegePresent();
logger.info("Post was deleted");
        listOfPost = webDriver.findElements(By.xpath(String.format(postTitleLocator,title)));
counter++;
    }
        logger.info("All post was deleted with title"+ title);
        return this;
    }
    private MyProfilePage checkIsSuccessDeletedPostMessegePresent() {
        Assert.assertTrue("Element is not present",isElementDisplayed(succsesDeletedPostMesage));
    return this;
    }

    public  MyProfilePage editPostWithTitleWhilePresent(  String text, String setTextForEdit) {
        WebElement titleForEdit = webDriver.findElement(By.xpath(String.format(postTitleLocator,text)));
clickOnElement(titleForEdit);
        logger.info("Go to post that need deleted ");
new PostPage(webDriver)
        .checkIsRedirectPostPage()
        .clickOnEditButton();
new CreatePostPage(webDriver);
        enterTextInToElement(inputTitle,setTextForEdit);
        clickOnElement(buttonSaveUpdates);
        logger.info("Save update was clicked");
        checkIsisElementDisplayedPostMessegeEdit();
        clickOnMyProfileButton();
        checkIsRedirectToMyProfilePage();
        checkIsSuccessEditPost(setTextForEdit);
        logger.info("Post was edited");

    return this;
    }


    private MyProfilePage checkIsisElementDisplayedPostMessegeEdit() {
        Assert.assertTrue("Message is not present",isElementDisplayed(succsesEditedPostMesage));
        logger.info("Message Post was edit is visible");
        return this;
    }
    private MyProfilePage checkIsSuccessEditPost(String editedPost) {
        Assert.assertTrue("Post is not present",isElementDisplayed(webDriver.findElement(By.xpath(String.format(postTitleLocator,editedPost)))));
        logger.info("Edited post is visible");
        return this;
    }
}
