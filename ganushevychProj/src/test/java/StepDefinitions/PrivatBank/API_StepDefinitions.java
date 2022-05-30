package StepDefinitions.PrivatBank;

import api.PrivatBank.PrivatBankApiHelper;
import cucumber.api.java.en.Given;

public class API_StepDefinitions {
    PrivatBankApiHelper privatBankApiHelper = new PrivatBankApiHelper();

    @Given("^Get exchange rate via API for currency (.*) and save needed courses$")
    public void getExchangeRateViaAPIForCurrencyAndSaveNeededCourses(String currency) {
        privatBankApiHelper.getExchangeRateByCurrency(currency);
    }
}
