package StepDefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import pages.HomePage;
import pages.LoginPage;

import static libs.DriverHelper.getWebDriver;

public class HomePage_StepDefinition {

    LoginPage loginPage = new LoginPage(getWebDriver());
    HomePage homePage = new HomePage(getWebDriver());

    @Given("^User opens 'Home' page$")
    public void userOpensHomePage() {
        loginPage
                .loginWithValidCredentials()
                .checkIsButtonSignOutDisplayed();
    }

    @When("^User clicks on 'Profile' button in the 'Home' page$")
    public void userClicksOnProfileButtonInTheHomePage() {
        homePage
                .clickOnProfileLink();
    }

}
