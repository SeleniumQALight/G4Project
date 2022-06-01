package StepDefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import libs.DriverHelper;
import org.openqa.selenium.WebDriver;
import pages.HomePage;
import pages.LoginPage;

public class LoginPage_StepDefinition {
    private LoginPage loginPage = new LoginPage(DriverHelper.getWebDriver());
    private HomePage homePage = new HomePage(DriverHelper.getWebDriver());
    @Given("^User opens 'Login' page$")
    public void user_opens_Login_page() {
        loginPage.openLoginPage();
    }
    @When("^User enters '(.*)' login into 'Login' input on 'Login' page$")
    public void user_enters_Wrong_login_login_into_Login_input_on_Login_page(String userName) {
        loginPage.enterLoginIntoInputLogin(userName);
    }
    @When("^User enters '(.*)' passWord into 'PassWord' input on 'Login' page$")
    public void user_enters_passWord_passWord_into_PassWord_input_on_Login_page(String password) {
        loginPage.enterLoginIntoInputPassword(password);
    }
    @When("^User click on 'SingIn' button on 'Login' page$")
    public void user_click_on_SingIn_button_on_Login_page() {
        loginPage.clickOnButtonSignIn();
    }

    @Then("^User sees alert message with text '(.*)'$")
    public void user_sees_alert_message_with_text_Invalid_username_password(String message) {
        loginPage.checkAlertMessageText(message);
    }
    @Then("^User opens 'Home' page and button 'signOut' is displayed$")
    public void user_opens_homepage_and_button_sign_out_is_displayed(){
        homePage.checkIsButtonSignOutDisplayed();
    }

    @When("^User enters '(.*)' username into input 'Username' signUp$")
    public void user_enters_username_into_input_username_signup(String username){
        loginPage.enterUsernameIntoInputUsernameSignUp(username);
    }

    @When("^User enters '(.*)' email into input 'Email' signUp$")
    public void user_enters_email_into_input_email_signup(String email){
        loginPage.enterEmailIntoInputEmailSignUp(email);
    }

    @When("^User enters '(.*)' passWord into input 'PassWord' signUp$")
    public void user_enters_password_into_input_password_signup(String password){
        loginPage.enterPasswordIntoInputPasswordSignUp(password);
    }

    @When("^User clicks on 'SignUp' button$")
    public void user_clicks_on_signUp_button(){
        loginPage.clickOnButtonSignUp();
    }

    @Then("^User sees alert message with '(.*)'$")
    public void user_sees_alert_message_with_text(String expectedErrors){
        loginPage.checkErrorsMessages(expectedErrors);
    }

}
