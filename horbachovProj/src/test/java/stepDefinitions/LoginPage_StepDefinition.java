package StepDefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import libs.DriverHelper;
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
    public void user_enters_Wrong_login_login_into_Login_input_on_Login_page(String username) {
        loginPage.enterLoginIntoInputLogin(username);

    }

    @When("^User enters '(.*)' passWord into 'PassWord' input on 'Login' page$")
    public void user_enters_passWord_passWord_into_PassWord_input_on_Login_page(String password) {
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

    @Then("^User sees 'Sign Out' button$")
    public void user_sees_sign_out_button() {
        homePage.isButtonSignOutDisplayed();

    }

    @When("^User enters '(.*)' into 'Username' input on 'Login' page$")
    public void user_enters_username_into_input_on_login_page_registration(String username) {
        loginPage.enterLoginRegistration(username);

    }

    @When("^User enters '(.*)' into 'Email' input on 'Login' page$")
    public void user_enters_email_into_input_on_login_page_registration(String email) {
        loginPage.enterEmailRegistration(email);

    }

    @When("^User enters '(.*)' into 'Password' input on 'Login' page$")
    public void user_enters_password_into_input_on_login_page_registration(String password) {
        loginPage.enterPasswordRegistration(password);

    }

    @When("^User click on 'Sign Up for OurApp' button on 'Login' page$")
    public void user_click_on_SingUp_button_on_Login_page() {
        loginPage.clickOnButtonSignUp();

    }

    @Then("^User sees 'That username is already taken.' error message")
    public void user_sees_that_username_is_already_taken_error_message() {
        loginPage.checkErrorsMessages("That username is already taken.");

    }

    @Then("^User sees 'You must provide a valid email address.' error message")
    public void user_sees_you_must_provide_valid_email_error_message() {
        loginPage.checkErrorsMessages("You must provide a valid email address.");

    }

    @Then("^User sees 'Password must be at least 12 characters.' error message")
    public void user_sees_password_must_be_at_least_12_characters_error_message() {
        loginPage.checkErrorsMessages("Password must be at least 12 characters.");

    }
}



