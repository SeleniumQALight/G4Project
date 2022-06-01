package StepDefinitions;

import api.privatbank.ApiHelperPrivatBank;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import libs.DriverHelper;
import pages.privatbank.PrivatbankPage;

public class Privatbank_StepDefinition {
    ApiHelperPrivatBank apiHelperPrivatBank = new ApiHelperPrivatBank();
    PrivatbankPage privatbankPage = new PrivatbankPage(DriverHelper.getWebDriver());

    @Given("User receives '(.*)' currency value from api")
    public void userReceivesCurrencyCurrencyValueFromApi(String currency) {
        apiHelperPrivatBank.getApiValueForCurrency(currency);
    }

    @Then("^User receives '(.*)' currency value from ui$")
    public void userReceivesCurrencyCurrencyValueFromUi(String currency) {
        privatbankPage.getCurrencyBuyValue(currency);
        privatbankPage.getCurrencySaleValue(currency);
    }

    @And("^User compares value from api with ui$")
    public void userComparesValueFromApiWithUi() {
    privatbankPage.compareApiCurrencyWithUi();
    }

    @When("^User opens 'Privatbank' page$")
    public void userOpensPrivatbankPage() {
        privatbankPage.openPrivatbankPage();
    }
}
