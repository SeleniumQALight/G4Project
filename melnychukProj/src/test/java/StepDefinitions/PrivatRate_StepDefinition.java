package StepDefinitions;

import api.ExchangeRateDTO;
import api.privatAPI.PrivatEndpoints;
import apiTests.APIHelper;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.http.ContentType;
import libs.DriverHelper;
import libs.TestData;
import org.apache.log4j.Logger;

import org.junit.Assert;
import pages.PrivatRatesPage;

import static io.restassured.RestAssured.given;
import static libs.TestData.*;


public class PrivatRate_StepDefinition {
  //  DriverHelper driverHelper = new DriverHelper();
    private APIHelper apiHelper = new APIHelper();
    Logger logger = Logger.getLogger(getClass());
    private PrivatRatesPage privatRatesPage = new PrivatRatesPage(DriverHelper.getWebDriver());

    /*
      Given User sends request  anfd get rate '<ccy>'
    When  User opens private site
    And User checks '<ccy>' rate on UI
    Then API and UI rates are equal
     */


    @Given("^User sends request  anfd get rate '(.*)'$")
    public ExchangeRateDTO[] userSendsRequestToPrivatBankAPI(String ccy) {


        ExchangeRateDTO[] responseBody =
                given()
                        .contentType(ContentType.JSON)
                        .param("exchange")
                        .param("json")
                        .param("coursid", 5)
                        .log().all()
                        .when()
                        .get(PrivatEndpoints.EXHANGE_BY_COURSEID)
                        .then()
                        .statusCode(200)
                        .log().all()
                        .extract()
                        .response().as(ExchangeRateDTO[].class);


        for (ExchangeRateDTO exchangeRatesDTO : responseBody) {
            if (exchangeRatesDTO.getCcy().equals(ccy)) {
                apiPrivatRateBuy = (exchangeRatesDTO.getBuy());
                apiPrivatRateSell = (exchangeRatesDTO.getSale());
            }
        }

        return responseBody;
    }

    @When("^User opens private site$")
    public void user_opens_private_site()  {
        privatRatesPage.openPrivatSite();
    }

    @And("^User checks '(.*)' rate on UI$")
    public void user_checks_rate_on_UI(String ccy) {
        privatRatesPage.getUiRates(ccy);
    }

    @Then("^API and UI rates are equal$")
    public void api_and_UI_rates_are_equal() {

        Assert.assertEquals(apiPrivatRateBuy, uiPrivatRateBuy);
        Assert.assertEquals(apiPrivatRateSell, uiPrivatRateSell);

    }

}
