package StepDefinitions;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import libs.DriverHelper;
import pages.HomePage;
import pages.LoginPage;

public class HomePage_StepDefinition {

    private HomePage homePage = new HomePage(DriverHelper.getWebDriver());
    private LoginPage loginPage = new LoginPage(DriverHelper.getWebDriver());

    @Then("^User sees 'Sign Out' button$")
    public void user_sees_Sign_Out_button(){
        homePage.checkIsButtonSignOutDisplayed();
    }


    @Given("^User opens 'Home' page$")
    public void userOpensHomePage() {
        loginPage.loginWithValidCred()
                .checkIsButtonSignOutDisplayed();
    }

    @When("^User click on 'Profile' button on 'Home' page$")
    public void userClickOnProfileButtonOnHomePage() {
        homePage.clickOnMyProfileButton();
    }


}
