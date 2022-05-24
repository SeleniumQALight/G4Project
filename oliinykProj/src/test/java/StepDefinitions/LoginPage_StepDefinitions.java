package StepDefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import libs.DriverHelper;
import pages.LoginPage;
import pages.HomePage;

public class LoginPage_StepDefinitions {

    private LoginPage loginPage = new LoginPage(DriverHelper.getDriver());
    private HomePage homePage = new HomePage(DriverHelper.getDriver());

    @Given("^User opens 'Login' page$")
    public void user_opens_Login_page(){
        loginPage.openLoginPage();
    }

    @When("^User enters '(.*)' login into 'Login' input on 'Login' page$")
    public void user_enters_Wrong_login_login_into_Login_input_on_Login_page(String username){
        loginPage.enterLoginIntoInputLogin(username);
    }

    @When("^User enters '(.*)' passWord into 'PassWord' input on 'Login' page$")
    public void user_enters_passWord_passWord_into_PassWord_input_on_Login_page(String password) {
        loginPage.enterPasswordIntoInputPassword(password);
    }

    @When("^User click on 'SingIn' button on 'Login' page$")
    public void user_click_on_SingIn_button_on_Login_page() {
        loginPage.clickOnSignIn();
    }

    @Then("^User sees alert message with text '(.*)'$")
    public void user_sees_alert_message_with_text_Invalid_username_password(String message){
        loginPage.checkAlertMessageText(message);
    }

    @Then("^User sees 'Sign Out' button$")
    public void user_sees_Sign_Out_button(){
        homePage.checkIsButtonSingOutDisplayed();
    }

    @When("^User enters '(.*)' login into 'Login' input in registration form on 'Login' page$")
    public void user_enters_a_login_into_Login_input_in_registration_form_on_Login_page(String username){
        loginPage.enterLoginSignUp(username);
    }


    @When("^User enters '(.*)' \\(not valid\\) email into 'Email' input in registration form on 'Login' page$")
    public void user_enters_test_not_valid_email_into_Email_input_in_registration_form_on_Login_page(String email){
        loginPage.enterMailSignUp(email);
    }


    @When("^User enters '(.*)' password into 'Password' input in registration form on 'Login' page$")
    public void user_enters_qwert_password_into_Password_input_in_registration_form_on_Login_page(String password){
        loginPage.enterPasswordSignUp(password);
    }

    @Then("^User sees pop-out alert message for login '(.*)'$")
    public void user_sees_pop_out_alert_message_for_login_Username_must_be_at_least_characters(String text){
        loginPage.checkTextAlertSignUpLogin(text);
    }

    @Then("^User sees another alert message for email '(.*)'$")
    public void user_sees_another_alert_message_for_email_You_must_provide_a_valid_email_address(String text){
        loginPage.checkTextAlertSignUpEmail(text);
    }

    @Then("^User sees even other alert message for password '(.*)'$")
    public void user_sees_even_other_alert_message_for_password_Password_must_be_at_least_characters(String text){
        loginPage.checkTextAlertSignUpPassword(text);
    }

    @Then("^User could see other alert message that pop-ups '(.*)'$")
    public void user_could_see_other_alert_message_that_pop_ups_That_username_is_already_taken(String text){
        loginPage.checkTextAlertSignUpLoginExisting(text);
    }


}
