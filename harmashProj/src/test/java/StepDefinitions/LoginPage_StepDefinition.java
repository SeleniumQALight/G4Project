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
    public void user_opens_Login_page()  {
        loginPage.openLoginPage();
    }

    @When("^User enters '(.*)' login into 'Login' input on 'Login' page$")
    public void user_enters_Wrong_login_login_into_Login_input_on_Login_page(String userName) {
        loginPage.enterLoginIntoInputLogin(userName);
    }

    @When("^User enters '(.*)' passWord into 'PassWord' input on 'Login' page$")
    public void user_enters_passWord_passWord_into_PassWord_input_on_Login_page(String password) {
        loginPage.enterPasswordIntoInputPassWord(password);
    }

    @When("^User click on 'SingIn' button on 'Login' page$")
    public void user_click_on_SingIn_button_on_Login_page() {
        loginPage.clickOnButtonSignIn();
    }

    @Then("^User sees alert message with text '(.*)'$")
    public void user_sees_alert_message_with_text_Invalid_username_password(String message) {
        loginPage.checkAlertMessageText(message);

    }
    @Then("^User sees avatar$")
    public void user_sees_avatar() {
        homePage.checkIsButtonSignOutDisplayed();
    }


}
