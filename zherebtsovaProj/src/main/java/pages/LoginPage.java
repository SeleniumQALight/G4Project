package pages;


import libs.TestData;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.TextInput;


import java.util.ArrayList;
import java.util.List;


public class LoginPage extends ParentPage {

    @FindBy(xpath = ".//input[@name='username' and @placeholder='Username']")
   @Name("Input Login")
    private TextInput inputLoginSingIn;

    @FindBy(xpath = ".//input[@placeholder='Password']")
    private WebElement inputPasswordSignIn;
    @FindBy(xpath = ".//button[text()='Sign In']")
    private Button buttonSingIn;
    @FindBy(xpath = ".//button[@type='submit']")
    private WebElement buttonSingUp;
    @FindBy(id = "username-register")
    private WebElement inputLoginRegistration;
    @FindBy(id = "email-register")
    private WebElement inputEmailRegistration;
    @FindBy(id = "password-register")
    private WebElement inputPassWordRegistration;
    private String listErrorsLocators = ".//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']";
    @FindBy(xpath = ".//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']")
    private List<WebElement> listOfErrors;


    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/";
    }

    public void openLoginPage() {
        try {
            webDriver.get(baseUrl + "/");
            logger.info("Login page was opened");
        } catch (Exception e) {
            logger.error("Can not open Login Page" + e);
            Assert.fail("Can not open Login Page" + e);
        }
    }
    public void enterLoginIntoInputLogin(String login) {
        enterTextInToElement(inputLoginSingIn, login);
    }

    public void enterPassWordIntoInputPassWord(String passWord) {
        enterTextInToElement(inputPasswordSignIn, passWord);
    }

    public void clickOnButtonSignIn() {
        clickOnElement(buttonSingIn);
    }

    public LoginPage clickOnButtonSignUp() {
                clickOnElement(buttonSingUp);
        return this;
    }


    public boolean isErrorFieldDisplayed() {
        try {
            return webDriver.findElement(By.xpath(".//div[@class='alert alert-danger text-center']")).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    public HomePage loginWithValidCred(){
        openLoginPage();
        enterLoginIntoInputLogin(TestData.VALID_LOGIN);
        enterPassWordIntoInputPassWord(TestData.VALID_PASS);
        clickOnButtonSignIn();
        return new HomePage(webDriver);
    }


    public LoginPage enterLoginRegistration(String login) {
        enterTextInToElement(inputLoginRegistration, login);
        return this;
    }
    public LoginPage enterEmailRegistration(String email) {
        enterTextInToElement(inputEmailRegistration, email);
        return this;
    }
    public LoginPage enterPasswordRegistration(String password) {
        enterTextInToElement(inputPassWordRegistration, password);
        return this;
    }

    public LoginPage checkErrorsMessages(String expectedErrors) {
        String []expectedErrorsArray = expectedErrors.split(";"); // рассплитить эрроры
        webDriverWait10.withMessage("Number Of messages ").until(ExpectedConditions.numberOfElementsToBe(By.xpath(listErrorsLocators)
                ,expectedErrorsArray.length)); // передаем длину эрроров
        ArrayList<String> actualTextFromErrors = new ArrayList<>();
        for(WebElement element: listOfErrors){
            actualTextFromErrors.add(element.getText()); //получить текст с эрроров
        }
        SoftAssertions softAssertions = new SoftAssertions();
        for (int i =0; i< expectedErrorsArray.length; i++){
            softAssertions.assertThat(expectedErrorsArray[i]).isIn(actualTextFromErrors);
        }

        softAssertions.assertAll();
        return this;
    }

}













