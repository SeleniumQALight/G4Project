package StepDefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pages.HomePage;
import pages.LoginPage;

import static libs.DriverHelper.getWebDriver;


public class HomePage_StepDefinition {

        private HomePage homePage = new HomePage(getWebDriver());


        @Then("^User sees 'SignOut' button on 'Home' page$")
        public void user_sees_SignOut_button_on_Home_page() {
                homePage.isButtonSignOutDisplayed();
        }

       // @Given("")
       // public void ggg(){}

        LoginPage loginPage = new LoginPage(getWebDriver());


        @Given("^User opens 'Home' page$")
        public void userOpensHomePage() {
                loginPage.loginWithValidCred()
                        .checkIsButtonSignOutDisplayed();

        }

        @When("^User clicks on 'Profile' button on 'Home' page$")
        public void userClicksOnProfileButtonOnHomePage() {
            homePage.clickOnMyProfileButton();
        }
}
