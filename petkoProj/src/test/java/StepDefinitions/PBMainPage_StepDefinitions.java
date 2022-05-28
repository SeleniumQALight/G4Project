package StepDefinitions;

import api.privatBank.PBApiHelper;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import libs.DriverHelper;
import pages.PBMainPage;
import java.util.Objects;

public class PBMainPage_StepDefinitions {

    PBMainPage pbMainPage = new PBMainPage(DriverHelper.getWebDriver());
    PBApiHelper pbApiHelper = new PBApiHelper();


    @Given("^User opens 'Main' page and accept cookies$")
    public void userOpensMainPage() throws Exception {
        pbMainPage.openMainPage();
        pbMainPage.clickOnAcceptCookies();
    }

    @And("^User gets currency rates by API$")
    public void userGetsCurrencyRatesByAPI() {
        pbApiHelper.getCurrencyRate();
    }

    @When("^User checks currency rates on the UI$")
    public void userChecksCurrencyRatesOnTheUI() {
        pbMainPage.getUIRates();
    }

    @Then("^User compares rate for '(.*)'$")
    public void userComparesRateForCurrency(String currency) {
        if(Objects.equals(currency, "USD")){
            pbMainPage.compareUsdRates();
        }
        if(Objects.equals(currency, "EUR")){
            pbMainPage.compareEurRates();
        }
    }
}
