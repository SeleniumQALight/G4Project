package StepDefinitions;

import api.privatBank.PBApiHelper;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import libs.DriverHelper;
import pages.PBMainPage;

public class PBMainPage_StepDefinitions {

    PBMainPage pbMainPage = new PBMainPage(DriverHelper.getWebDriver());
    PBApiHelper pbApiHelper = new PBApiHelper();


    @Given("^User opens 'Main' page and accept cookies$")
    public void userOpensMainPage() throws Exception {
        pbMainPage.openMainPage();
        pbMainPage.clickOnAcceptCookies();
    }

    @And("^User gets currency rates by API for '(.*)'$")
    public void userGetsCurrencyRatesByAPI(String currency) {
        pbApiHelper.getCurrencyRate(currency);
    }

    @When("^User checks currency rates on the UI for '(.*)'$")
    public void userChecksCurrencyRatesOnTheUI(String currency) {
        pbMainPage.getUIRates(currency);
    }

    @Then("^User compares rates from UI and API$")
    public void userComparesRateForCurrency() {
        pbMainPage.compareCurrencyRates();
    }
}
