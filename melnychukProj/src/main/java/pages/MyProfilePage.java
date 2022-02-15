package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MyProfilePage extends  ParentPageWithHeader {
   private String postTiteLocator = ".//*[text()='%s']";

   @FindBy(xpath =".//*/strong[contains(text(),'G4-OlhaM edit post title test')]")
   private WebElement  postTiteToClick;

    public MyProfilePage(WebDriver webDriver) {
        super(webDriver);
    }

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
    public MyProfilePage checkPostWithEditedTitle(String title) {
        List<WebElement> postsList = webDriver.findElements(
                By.xpath(String.format(postTiteLocator, title)));
        Assert.assertEquals("Number of posts with title " + title,
                1, postsList.size());

        return  this;
    }

       public PostPage clickOnRecentlyCreatedPost() {
                clickOnElement(postTiteToClick);
        return new PostPage(webDriver);
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


}
