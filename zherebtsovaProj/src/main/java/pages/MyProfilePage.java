package pages;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;


public class MyProfilePage extends ParentPageWithHeader{
    public String postTitleLocator = ".//*[text()='%s']"; //%s-чтоб подставлять значения
    public MyProfilePage(WebDriver webDriver){
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/profile/";
    }

    public void checkPostWasCreated(String title) {
        List<WebElement> postList = webDriver.findElements(
                By.xpath(String.format(postTitleLocator, title)));
        Assert.assertEquals("Number of posts with title" +title, 1,postList.size());
    }

    public MyProfilePage checkIsRedirectToMyProfilePage() {
        checkUrlWithPattern();
        WaitChatToBeHide();
        return this;

    }

    public MyProfilePage deletePostWithTitleWhilePresent(String title) {
        List<WebElement>listOfPost = webDriver.findElements(By.xpath(String.format(postTitleLocator,title)));
       // clickOnElement();
        return this;
    }
}
