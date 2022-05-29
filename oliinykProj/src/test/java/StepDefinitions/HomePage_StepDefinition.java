package StepDefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import pages.HomePage;
import pages.LoginPage;

import static libs.DriverHelper.getDriver;

public class HomePage_StepDefinition {
//    @Given("")
//    public void fasgh(){
//
//    }

    LoginPage loginPage = new LoginPage(getDriver());
    HomePage homePage = new HomePage(getDriver());

    @Given("^User opens 'Home' page$")
    public void userOpensHomePage() {
        loginPage.logedInHomepage()
                .checkIsButtonSingOutDisplayed();
    }

    @When("^User click on 'Profile' button on 'Home' page$")
    public void userClickOnProfileButtonOnHomePage() {
        homePage.clickOnMyProfileButton();
    }

}
