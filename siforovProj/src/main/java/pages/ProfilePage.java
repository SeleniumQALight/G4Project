package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ProfilePage extends ParentPageWithHeader{
    private String postTitleLocator = ".//*[text()='%s']";

    @FindBy(xpath = ".//a[contains(text(),'Posts')]")
    private WebElement postsTab;

    @FindBy(xpath = ".//*[@class='list-group']/a")
    private List<WebElement> postsInTheProfile;

    public ProfilePage(WebDriver webDriver) {
        super(webDriver);
    }

    //homework1
    public boolean postTabIsVisible(){
        return elementIsVisible(postsTab);
    }

    public ProfilePage checkPostWasCreated(String title) {
        List<WebElement> postsList = webDriver.findElements(By.xpath(String.format(postTitleLocator, title)));
        Assert.assertEquals("Number of posts with title "+title, 1,postsList.size());
        return this;
    }
}
