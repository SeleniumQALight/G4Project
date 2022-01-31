package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegistrationPageHW3 extends ParentPage {

    @FindBy(xpath = ".//input[@id= 'username-register']")
    private WebElement inputUsernameRegister;
    @FindBy(xpath = ".//input[@id= 'email-register']")
    private WebElement inputEmailRegister;
    @FindBy(xpath = ".//input[@id= 'password-register']")
    private WebElement inputPasswordRegister;
    @FindBy(xpath = ".//div[text()='Username must be at least 3 characters.' and @class =  'alert alert-danger small liveValidateMessage liveValidateMessage--visible'] ")
    private WebElement invalidMassageUsername;
    @FindBy(xpath = ".//div[text()='You must provide a valid email address.' and @class =  'alert alert-danger small liveValidateMessage liveValidateMessage--visible'] ")
    private WebElement invalidMassageEmail;
    @FindBy(xpath = ".//div[text()='Password must be at least 12 characters.' and @class = 'alert alert-danger small liveValidateMessage liveValidateMessage--visible'] ")
    private WebElement invalidMassagePassword;
    @FindBy(xpath = ".//button[text()='Sign up for OurApp']")
    private WebElement buttonSingOurApp;

    public RegistrationPageHW3(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRalativeUrl() {
        return "/";
    }

    public RegistrationPageHW3 clickOnButtonSingOurApp() {
        clickOnElement(buttonSingOurApp);
        return new RegistrationPageHW3(webDriver);
    }

    public RegistrationPageHW3 checkIsInputUsernameRegisterDisplayed() {
        Assert.assertTrue("-----------------------Input username register is not displayed", isElementDisplayed(inputUsernameRegister));
        return this;
    }

    public RegistrationPageHW3 checkIsInputEmailRegisterDisplayed() {
        Assert.assertTrue("Input email register is not displayed", isElementDisplayed(inputEmailRegister));
        return this;
    }

    public RegistrationPageHW3 checkIsInputPasswordRegisterDisplayed() {
        Assert.assertTrue("Input password register is not displayed", isElementDisplayed(inputPasswordRegister));
        return this;
    }

    public RegistrationPageHW3 checkIsInvalidMassageUsernameDisplayed() {
        Assert.assertTrue("Invalid massage username is not displayed", isElementDisplayed(invalidMassageUsername));
        return this;
    }

    public RegistrationPageHW3 checkIsInvalidMassageEmailDisplayed() {
        Assert.assertTrue("Invalid massage email is not displayed", isElementDisplayed(invalidMassageEmail));
        return this;
    }

    public RegistrationPageHW3 checkIsInvalidMassagePasswordDisplayed() {
        Assert.assertTrue("Invalid massage password is not displayed", isElementDisplayed(invalidMassagePassword));
        return this;
    }

    public RegistrationPageHW3 enterTextIntoUsernameRegisterInput(String text) {
        enterTextIntoElement(inputUsernameRegister, text);
        return this;
    }

    public RegistrationPageHW3 enterTextIntoEmailRegisterInput(String text) {
        enterTextIntoElement(inputEmailRegister, text);
        return this;
    }

    public RegistrationPageHW3 enterTextIntoPasswordRegisterInput(String text) {
        enterTextIntoElement(inputPasswordRegister, text);
        return this;
    }

}
