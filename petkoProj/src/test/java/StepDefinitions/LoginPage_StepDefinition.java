package StepDefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import libs.DriverHelper;
import pages.LoginPage;

public class LoginPage_StepDefinition {

    private LoginPage loginPage = new LoginPage(DriverHelper.getWebDriver());

    @Given("^User opens 'Login' page$")
    public void user_opens_Login_page(){
        loginPage.openLoginPage();
    }

    @When("^User enters '(.*)' login into 'Login' input on 'Login' page$")
    public void user_enters_Wrong_login_login_into_Login_input_on_Login_page(String username){
        loginPage.enterLoginIntoInputLogin(username);
    }

    @When("^User enters '(.*)' passWord into 'PassWord' input on 'Login' page$")
    public void user_enters_passWord_passWord_into_PassWord_input_on_Login_page(String password){
        loginPage.enterPassWordIntoInputPassWord(password);
    }

    @When("^User click on 'SingIn' button on 'Login' page$")
    public void user_click_on_SingIn_button_on_Login_page(){
        loginPage.clickOnButtonSignIn();
    }

    @Then("^User sees alert message with text '(.*)'$")
    public void user_sees_alert_message_with_text_Invalid_username_password(String message){
        loginPage.checkAlertMessageText(message);

    }
    @When("^User enters '(.*)' into 'Login' input in registration form on 'Login' page$")
    public void user_enters_name_into_Login_input_in_registration_form_on_Login_page(String username){
        loginPage.enterLoginRegistration(username);
    }

    @Then("^User sees alert message for login '(.*)'$")
    public void user_sees_alert_message_for_login(String error) {
        loginPage.checkAlertRegistrationUsernameMessage(error);
    }

    @When("^User enters '(.*)' into 'Email' input in registration form on 'Login' page$")
    public void user_enters_email_into_Email_input_in_registration_form_on_Login_page(String email){
        loginPage.enterEmailRegistration(email);
    }

    @Then("^User sees alert message for email '(.*)'$")
    public void user_sees_alert_message_for_email(String error){
        loginPage.checkAlertRegistrationEmailMessage(error);
    }

    @When("^User enters '(.*)' into 'Password' input in registration form on 'Login' page$")
    public void user_enters_pass_into_Password_input_in_registration_form_on_Login_page(String password){
        loginPage.enterPasswordRegistration(password);
    }

    @Then("^User sees alert message for password '(.*)'$")
    public void user_sees_alert_message_for_password(String error){
        loginPage.checkAlertRegistrationPassMessage(error);
    }

    @When("^User click on the 'Sign Up' button$")
    public void user_click_on_the_Sign_Up_button(){
        loginPage.clickOnButtonSignUp();
    }

    @Then("^User sees errors messages '(.*)'$")
    public void user_sees_errors_messages_errors(String errors){
        loginPage.checkErrorsMessages(errors);
    }




}
