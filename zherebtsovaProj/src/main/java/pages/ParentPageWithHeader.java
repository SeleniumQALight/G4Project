package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ParentPageWithHeader extends ParentPage  {
    @FindBy(xpath = "//a[text()='Create Post']")
    private WebElement buttonCreatePost;

    public ParentPageWithHeader(WebDriver webDriver) {
        super(webDriver);
    }

    public boolean isButtonSignOutDisplayed(){
        try{
            return webDriver.findElement(By.xpath(".//button[text()='Sign Out']")).isDisplayed();
        }catch (Exception e){
            return false;
        }
    }

    public HomePage checkIsButtonSignOutDisplayed(){
        Assert.assertTrue("Button SignOut is not displayed", isButtonSignOutDisplayed());
        return (HomePage) this;
    }

    public PostPage clickButtonCreatePost() {
        clickOnElement(buttonCreatePost);
        return new PostPage(webDriver);
    }
    }

