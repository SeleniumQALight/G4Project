package StepDefinitions;

import static libs.DriverHelper.getWebDriver;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import pages.HomePage;
import pages.LoginPage;


public class HomePage_StepDefinition {
    LoginPage loginPage = new LoginPage(getWebDriver());
    HomePage homePage = new HomePage(getWebDriver());

    @Given("^User opens 'Home' page$")
    public void userOpensHomePage() {
        loginPage.loginWithValidCred().checkIsButtonSignOutDisplayed();
    }

    @When("^User clicks on 'Profile' button on 'Home' page$")
    public void userClicksOnProfileButtonOnHomePage() {
        homePage.clickOnMyProfileButton();

    }


}
