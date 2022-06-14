package StepDefinitions;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.java.en.Given;
import pages.PrivatBankHomePage;

public class PrivatBankHomePage_StepDefinition {
    PrivatBankHomePage privatBankHomePage = new PrivatBankHomePage();

    @Given("^User opens 'PrivatBankHome' page$")
    public void userOpensPrivatBankHomePage() throws Exception {
        privatBankHomePage.openPrivatBankHomePage();
    }

    @When("^User gets '(.*)' exchange rate from UI$")
    public void userGetsCurrencyExchangeRateFromUI(String currency) {
        privatBankHomePage.getCurrencyExchangeRateFromUI(currency);
    }

    @Then("^User sees the exchange rate is the same from UI and API$")
    public void userSeesTheExchangeRateIsTheSameFromUIAndAPI() {
        privatBankHomePage.checkExchangeRatesAreTheSame();
    }
}
