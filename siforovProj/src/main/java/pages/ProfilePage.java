package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProfilePage extends ParentPage{
    public ProfilePage(WebDriver webDriver) {
        super(webDriver);
    }

    @FindBy(xpath = ".//a[contains(text(),'Posts')]")
    private WebElement postsTab;

    //homework1
    public boolean postTabIsVisible(){
        return elementIsVisible(postsTab);
    }
}
