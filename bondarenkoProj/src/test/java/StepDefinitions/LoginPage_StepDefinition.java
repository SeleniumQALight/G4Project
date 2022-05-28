package StepDefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import libs.DriverHelper;
import pages.LoginPage;

public class LoginPage_StepDefinition {
    private LoginPage loginPage = new LoginPage(DriverHelper.getWebDriver());


    @Given("^User opens 'Login' page$")
    public void user_opens_Login_page() {
        loginPage.openLoginPage();
    }

    @When("^User enters '(.*)' login into 'Login' input on 'Login' page$")
    public void user_enters_Wrong_login_login_into_Login_input_on_Login_page(String userName) {
        loginPage.enterLoginIntoInputLogin(userName);

    }

    @When("^User enters '(.*)' passWord into 'PassWord' input on 'Login' page$")
    public void user_enters_passWord_passWord_into_PassWord_input_on_Login_page(String password)  {
        loginPage.enterPasswordIntoInputPassword(password);

    }

    @When("^User click on 'SingIn' button on 'Login' page$")
    public void user_click_on_SingIn_button_on_Login_page() {
        loginPage.clickOnButtonSignIn();

    }

    @Then("^User sees alert message with text '(.*)'$")
    public void user_sees_alert_message_with_text_Invalid_username_password(String message) {
        loginPage.checkAlertMessageText(message);

    }
    @When("^User enters '(.*)' username into 'UserName' input on 'Login' page$")
    public void user_enters_w_username_into_UserName_input_on_Login_page(String username) {
        loginPage.enterUsernameIntoInputUsernameSignUp(username);

    }

    @When("^User enters '(.*)' email into 'Email' input on 'Login' page$")
    public void user_enters_email_into_Email_input_on_Login_page(String email) {
        loginPage.enterEmailIntoInputEmailSignUp(email);
    }

    @When("^User enters '(.*)' password into 'Password' input on 'Login' page$")
    public void user_enters_yt_password_into_Password_input_on_Login_page(String password) {
      loginPage.enterPasswordIntoInputPasswordSignUp(password);
    }

    @When("^User click on 'SignUpForOurApp' button on 'Login' page$")
    public void user_click_on_SignUpForOurApp_button_on_Login_page() {
      loginPage.clickOnButtonSignUpForOurApp();
    }

    @Then("^User sees '(.*)' in the registration form$")
    public void user_sees_Username_must_be_at_least_characters_You_must_provide_a_valid_email_address_Password_must_be_at_least_characters_in_the_registration_form(String expectedMessage)  {
      loginPage.checkErrorsMessages(expectedMessage);
    }
}


