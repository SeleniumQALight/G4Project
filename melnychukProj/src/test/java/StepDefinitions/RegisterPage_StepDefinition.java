package StepDefinitions;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import libs.DriverHelper;
import pages.LoginPage;

public class RegisterPage_StepDefinition {
    private LoginPage loginPage = new LoginPage(DriverHelper.getWebDriver());


    @When("^User enters   '(.*)' into 'Register name' input on 'Login' page$")
    public void user_enters_Username_into_Register_name_input_on_Login_page(String userName) {
        loginPage.enterLoginRegistration(userName);
    }

    @When("^User enters '(.*)' into 'Register password' input on 'Login' page$")
    public void user_enters_Password_into_Register_password_input_on_Login_page(String password) {
        loginPage.enterPasswordForRegistration(password);
    }

    @When("^User enters '(.*)' into 'Register Email' input on 'Login' page$")
    public void user_enters_Email_into_Register_Email_input_on_Login_page(String email) {
        loginPage.enterEmailForRegistration(email);

    }

    @When("^User click on 'SingUp' button on 'Login' page$")
    public void user_click_on_SingUp_button_on_Login_page() {
        loginPage.clickOnSignUpButton();
    }

    @Then("^User sees alert message  for the wrong username with text '(.*)'$")
    public void user_sees_alert_message_for_the_wrong_username_with_text_Username_must_be_at_least_characters(String message) {
        loginPage.checkTextInValidationUsername(message);
    }


    @Then("^User sees alert message  for the wrong password with text '(.*)'$")
    public void userSeesAlertMessageForTheWrongPasswordWithTextPasswordMustBeAtLeastCharacters(String message) {
        loginPage.checkTextInValidationPassword(message);
    }

    @Then("User sees alert message  for the wrong email with text '(.*)'$")
    public void userSeesAlertMessageForTheWrongEmailWithTextYouMustProvideAValidEmailAddress(String message) {
        loginPage.checkTextInValidationMail(message);
    }
}
