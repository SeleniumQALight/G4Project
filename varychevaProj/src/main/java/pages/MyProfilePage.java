package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class MyProfilePage extends ParentPageWithHeader {
    private final String postTitleLocator = ".//*[text()='%s']";

    public MyProfilePage(WebDriver webDriver) {
        super(webDriver);
    }

    public MyProfilePage checkPostWasCreated(String title) {
        List<WebElement> postsList = webDriver.findElements(
                By.xpath(String.format(postTitleLocator, title)));
        assertEquals("Number of posts with title " + title, 1, postsList.size());
        return this;
    }
}
