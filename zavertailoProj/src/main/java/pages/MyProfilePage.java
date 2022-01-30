package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MyProfilePage extends ParentPageWithHeader{
    private String postTitleLocator = ".//*[text()='%s']"; //%s- подставляем значения

    @FindBy(xpath = ".//*[text()='Post successfully deleted']")
    private WebElement SuccessDeletePostMessage;

    public MyProfilePage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/profile/";
    }

    public MyProfilePage checkPostWasCreated (String title){
        List<WebElement> postsList = webDriver.findElements(
                By.xpath(String.format(postTitleLocator, title)));
        Assert.assertEquals("Number of posts with title " + title, 1, postsList.size());
        return this;
    }

    public MyProfilePage checkIsRedirectToMyProfilePage() {
        checkUrlWithPattern();
        waitChatTobeHide();
        return  this;
    }

    public MyProfilePage deletePostWithTitleWilePresent(String title) {
        List<WebElement> listOfPost = webDriver.findElements(
                By.xpath(String.format(postTitleLocator, title)));

        int counter = 0;
        while (!listOfPost.isEmpty() && counter<10){                //цыкл пока не пустой
//            clickOnElement(webDriver.findElement(By.xpath(String.format(postTitleLocator, title))));
            clickOnPost(title);
            new PostPage(webDriver)// перейти на постпейдж
                    .checkIsRedirectToPostPage()// проверка страницы на которой мы
                    .clickOnDeleteButton()
                    .checkIsSuccessDeletePostMessagePresent();
            logger.info("Post was delete"); //сообщение
            listOfPost = webDriver.findElements(By.xpath(String.format(postTitleLocator, title))); //обновление списка
            counter++;
        }
        logger.info("All post were delete with title " + title);
        return this;

    }

    private MyProfilePage checkIsSuccessDeletePostMessagePresent() {
        Assert.assertTrue("Element is not present",isElementDispleid(SuccessDeletePostMessage));
        return  this;
    }

    public PostPage clickOnPost(String title) {
        clickOnElement(webDriver.findElement(By.xpath(String.format(postTitleLocator, title))));
        return new PostPage(webDriver);
    }

    public MyProfilePage checkPostWasUpdates(String titleNew) {
        List<WebElement> postsList = webDriver.findElements(
                By.xpath(String.format(postTitleLocator, titleNew)));
        Assert.assertEquals("Number of posts with title " + titleNew, 1, postsList.size());
        return this;
    }
}
