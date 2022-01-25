package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CreatePostPage extends ParentPageWithHeader {
    // .//*[@name='title']
    @FindBy(name = "title")
    private WebElement inputTitle;

    @FindBy(id = "post-body")
    private WebElement inputBody;

    @FindBy(tagName = "select")
    private WebElement dropDownRole;

    @FindBy(xpath = ".//*[text()='Save New Post']")
    private WebElement buttonSaveNewPost;
    ////////////////////
    @FindBy(xpath = ".//*[@type='checkbox']")
    private WebElement checkBoxElement;


    public CreatePostPage(WebDriver webDriver) {
        super(webDriver);
    }


    public CreatePostPage checkIsRedirectToCreatePostPage() {
        //waitChatToBeHide();
        Assert.assertTrue("InputTitle is not displayed"
                , isElementDisplayed(inputTitle));
        return this;
    }


    public CreatePostPage enterTextInToTitleInput(String text) {
        enterTextInToElement(inputTitle, text);
        return this;
    }

    public CreatePostPage enterTextInToBodyInput(String text) {
        enterTextInToElement(inputBody, text);
        return this;
    }

    public CreatePostPage selectTextInDropDownRole(String textForSelect) {
        selectTextInDropDown(dropDownRole, textForSelect);
        return this;
    }

    public CreatePostPage selectValueInDropDownRole(String valueForSelect) {
        selectValueDropDown(dropDownRole, valueForSelect);
        return this;
    }

    public CreatePostPage selectTextInDropDownByUI(String textForSelect) {
        textForSelect(dropDownRole, textForSelect);
        return this;
    }

    private void textForSelect(WebElement dropDownRole, String textForSelect) {
        try {
            dropDownRole.click();
            List<WebElement> options = webDriver.findElements(By.xpath(".//select[@name='select1']/option"));
            for (WebElement option : options) {
                if (option.getText().contains(textForSelect)) {
                    option.click();
                    logger.info(textForSelect + " Element was selected in DD");
                    return;
                }
            }
            throw new IllegalArgumentException(" value missing id DD " + textForSelect);
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }

    }

    public CreatePostPage checkBoxCondition(String checkBox) {
        Assert.assertTrue("Чекбокс не отображается",checkBoxElement.isDisplayed());
        if (checkBox.equals("check") && checkBoxElement.isSelected()) {
            logger.info(" Чекбокс нажат и его ненужно нажимать ");
        } else if (checkBox.equals("check") && !checkBoxElement.isSelected()) {
            checkBoxElement.click();
            logger.info(" Чекбокс нажат ");
        } else if (checkBox.equals("uncheck") && checkBoxElement.isSelected()) {//чек бокс уже нажат и нужно его отжать
            checkBoxElement.click();
            logger.info("чек бокс уже нажат и нужно его отжать");
        } else if (checkBox.equals("uncheck") && !checkBoxElement.isSelected()) {//чек бок нажат и ненужно его нажимать
            logger.info("чек бок нажат и ненужно его нажимать");
        }
        return new CreatePostPage(webDriver);
    }


    public PostPage clickOnButtonSavePost() {
        clickElement(buttonSaveNewPost);
        return new PostPage(webDriver);
    }

}
