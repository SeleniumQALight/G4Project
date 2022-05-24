package stepDefinitions;

import cucumber.api.java.en.Given;

import cucumber.api.java.en.When;
import pages.HomePage;
import pages.LoginPage;

import static libs.DriverHelper.getWebDriver;

public class HomePage_StepDefitions {
//    @Given("")
//    public void dfg(){
//
//    }

    LoginPage loginPage = new LoginPage(getWebDriver());
    HomePage homePage = new HomePage(getWebDriver());

    @Given("^User opens 'Home' page$")
    public void userOpensHomePage() {
        loginPage.loginWithValidCred()
                .checkIsButtonSingOutDisplayed();
    }

    @When("^User click on 'Profile' button on 'Home' page$")
    public void userClickOnProfileButtonOnHomePage() {

        homePage.clickOfMyProfileButton();

    }
}
