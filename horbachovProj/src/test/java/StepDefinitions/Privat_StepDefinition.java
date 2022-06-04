package StepDefinitions;

import api.ApiHelper;
import api.ExchangeRatesDTO;
import api.privatBank.PrivatBankEndpoints;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.http.ContentType;
import libs.DriverHelper;
import libs.TestData;
import org.apache.log4j.Logger;
import org.junit.Assert;
import pages.PrivatBankPage;


import static io.restassured.RestAssured.given;
import static libs.TestData.*;

public class Privat_StepDefinition {
    private ApiHelper apiHelper = new ApiHelper();
    Logger logger = Logger.getLogger(getClass());
    private PrivatBankPage privatBankPage = new PrivatBankPage(DriverHelper.getWebDriver());


    @Given("^User sends request to Privat Bank API for '(.*)'$")
    public ExchangeRatesDTO[] userSendsRequestToPrivatBankAPI(String ccy) {


        ExchangeRatesDTO[] responseBody =
                given()
                        .contentType(ContentType.JSON)
                        .param("exchange")
                        .param("json")
                        .param("coursid", 5)
                        .log().all()
                        .when()
                        .get(PrivatBankEndpoints.GET_CURRENCY_EXCHANGE_RATE)
                        .then()
                        .statusCode(200)
                        .log().all()
                        .extract()
                        .response().as(ExchangeRatesDTO[].class);


        for (ExchangeRatesDTO exchangeRatesDTO : responseBody) {
            if (exchangeRatesDTO.getCcy().equals(ccy)) {
                TestData.apiRateBuy = Float.parseFloat(exchangeRatesDTO.getBuy());
                TestData.apiRateSell = Float.parseFloat(exchangeRatesDTO.getSale());
            }
        }

        return responseBody;
    }

    @When("^User opens pb.ua$")
    public void userOpensPbUa() {
        privatBankPage.openPbUa();
    }


    @And("^User checks '(.*)' rate on ui$")
    public void userChecksCcyRateOnUi(String ccy) {
        privatBankPage.readAndSaveUiRates(ccy);
    }

    @Then("Api and ui rates are equal$")
    public void ccyRateFromApiAndUiAreEqual() {


            Assert.assertTrue(apiRateBuy == uiRateBuy);
            Assert.assertTrue(apiRateSell == uiRateSell);

        }
    }

