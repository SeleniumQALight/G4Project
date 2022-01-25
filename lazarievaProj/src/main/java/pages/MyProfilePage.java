package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class MyProfilePage extends ParentPageWithHeader {
    final   String postTitleLocator = ".//*[text()='%s']" ;
    //%S дає можливість джаві підставляти значення

    public MyProfilePage(WebDriver webDriver) {
        super(webDriver);
    }

       public MyProfilePage checkPostWasCreated(String title) {
        List<WebElement> postLists = webDriver.findElements(
                By.xpath(String.format(postTitleLocator, title)));
        Assert.assertEquals("Number of posts with title  " +title, 1, postLists.size());
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
        //TODO
        return this;
    }
}
