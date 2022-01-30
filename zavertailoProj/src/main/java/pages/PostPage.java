package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class PostPage extends ParentPageWithHeader{
    @FindBy(xpath = ".//*[@class='alert alert-success text-center']")
    private WebElement alertSuccess;

    @FindBy(xpath = ".//a[@data-original-title='Edit']")
    private WebElement editButton;

    @FindBy(xpath = ".//button[@data-original-title='Delete']")
    private WebElement buttonDelete;

    private String nameTitle = ".//*[text()='%s']"; //%s- подставляем значения

    @FindBy(xpath = ".//*[@class = 'btn btn-primary']")
    private WebElement buttonSaveUpdates;

    @FindBy(xpath = ".//*[@name= 'title']")
    private WebElement titleInput;


    public PostPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/post/";
    }

    public PostPage checkIsRedirectToPostPage(){
        waitChatTobeHide();
        checkUrlWithPattern();
        Assert.assertTrue("Edit Button is not Displayed. ",
                isElementDispleid(editButton));
        return this;
    }

    public PostPage checkTextInAlert(String text){
        Assert.assertEquals("Text is Alert", text, alertSuccess.getText() );// текст не совпал но мы получили такой текс
        return this;
    }

    public MyProfilePage clickOnDeleteButton() {
        clickOnElement(buttonDelete);
        return new MyProfilePage(webDriver);
    }

    public PostPage checkOpenedPost(String title) {
        waitChatTobeHide();
        checkUrlWithPattern();
        isElementDispleid(webDriver.findElement(By.xpath(String.format(nameTitle, title))));
        return this;
    }

    public PostPage clickOnEdit() {
        clickOnElement(editButton);
        return this;
    }

    public PostPage checkIsButtonSaveUpdates() {
        isElementDispleid(buttonSaveUpdates);
        return this;
    }

    public PostPage clickOnTitle() {
        clickOnElement(titleInput);
        return this;
    }

    public PostPage cleanerTitleInPost (String titleNew) {
        enterTextInToElement(titleInput, titleNew);
        return this;
    }

    public PostPage clickOnSaveUpdates() {
        clickOnElement(buttonSaveUpdates);
        return this;
    }
}
