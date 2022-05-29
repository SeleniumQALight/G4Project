package StepDefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import libs.DriverHelper;
import pages.HomePage;
import pages.LoginPage;

public class HomePage_StepDefinition {

    LoginPage loginPage = new LoginPage(DriverHelper.getWebDriver());
    HomePage homePage = new HomePage(DriverHelper.getWebDriver());

    @Given("^User opens 'Home' page$")
    public void userOpensHomePage() {
        loginPage.loginWithValidCred().checkIsButtonSignOutDisplayed();
    }

    @When("^User click on 'Profile' button on 'Home' page$")
    public void userClickOnProfileButtonOnHomePage() {
        homePage.clickOnMyProfileButton();
    }
}
