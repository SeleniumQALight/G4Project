package StepDefinitions;

import api.privatBank.PrivatBankApiHelper;
import cucumber.api.java.en.And;

public class PrivatBankAPI_StepDefinition {
    PrivatBankApiHelper privatBankApiHelper = new PrivatBankApiHelper();

    @And("^User gets '(.*)' exchange rate from API$")
    public void userGetsCurrencyExchangeRateFromAPI(String currency) {
        privatBankApiHelper.getCurrencyExchangeRateFromAPI(currency);
    }
}
