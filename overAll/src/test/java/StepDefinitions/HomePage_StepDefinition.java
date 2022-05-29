package StepDefinitions;

import static libs.DriverHelper.getWebDriver;


import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import pages.HomePage;
import pages.LoginPage;

public class HomePage_StepDefinition {
//    @Given("")
//    public void dfh(){
//
//    }
    LoginPage loginPage = new LoginPage(getWebDriver());
    HomePage homePage = new HomePage(getWebDriver());

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
