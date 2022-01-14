package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MyProfilePage extends ParrentPageWithHeader{
    private String postTitleLocator = ".//*[text()='%s']";

    public MyProfilePage(WebDriver driver) {
        super(driver);
    }

    public MyProfilePage checkPostWasCreated(String title) {
        List<WebElement> postsList = driver.findElements(By.xpath(String.format(postTitleLocator, title)));
        Assert.assertEquals("Number of post with title " + title, 1, postsList.size());
        return this;
    }
}
