package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MyProfilePage extends  ParentPageWithHeader {
   private String postTiteLocator = ".//*[text()='%s']";
    public MyProfilePage(WebDriver webDriver) {
        super(webDriver);
    }

    @FindBy(xpath =".//*[text()='G4-OlhaM 02-06']")
    private WebElement postWithTitle;

    @Override
    String getRelativeUrl() {
        return "/profile/";
    }

    public MyProfilePage checkPostWasCreated(String title) {
        List<WebElement> postsList = webDriver.findElements(
                By.xpath(String.format(postTiteLocator, title)));
        Assert.assertEquals("Number of posts with title " + title,
                1, postsList.size());

        return  this;
    }

    public MyProfilePage checkIsRedirectToMyProfilePage() {
        checkUrlWithPattern();
        waitChatToBeHide();
        return this;

    }

    public MyProfilePage deletePostWithTitleWhilePresent(String title ) {
 List<WebElement> listOfPost = webDriver.findElements(
         By.xpath(String.format(postTiteLocator,title)));
 //TODO
        return this;
    }

    public PostPage clickOnCreatedPost() {
  clickOnElement(postWithTitle);
        return  new PostPage(webDriver);
    }
}
