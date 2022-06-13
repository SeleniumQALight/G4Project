package StepDefinitions.PrivatBank;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pages.PrivatBank_HomePage;

public class HomePage_StepDefinitions {

    PrivatBank_HomePage privatBank_homePage = new PrivatBank_HomePage();

    @When("^User opens PrivatBank 'Home' page$")
    public void userOpensPrivatBankHomePage() {
        privatBank_homePage.openHomePage();
    }

    @And("^Get exchange rate for currency (.*) on 'Home' page and save needed courses$")
    public void getExchangeRateForCurrencyOnHomePageAndSaveNeededCourses(String currency) {
        privatBank_homePage.getExchangeRateOnHomePageAndSave(currency);
    }

    @Then("^Check if exchange rate is matched$")
    public void checkIfExchangeRateIsMatched() {
        privatBank_homePage.checkExchangeRateMatching();
    }
}
