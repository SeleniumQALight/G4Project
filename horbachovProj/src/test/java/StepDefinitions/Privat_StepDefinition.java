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


    @Given("^User sends request to Privat Bank API$")
    public ExchangeRatesDTO[] userSendsRequestToPrivatBankAPI() {

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
            if (exchangeRatesDTO.getCcy().equals("USD")) {
                TestData.apiUsdRateBuy = Float.parseFloat(exchangeRatesDTO.getBuy());
                TestData.apiUsdRateSell = Float.parseFloat(exchangeRatesDTO.getSale());
            } else if (exchangeRatesDTO.getCcy().equals("EUR")) {
                TestData.apiEurRateBuy = Float.parseFloat(exchangeRatesDTO.getBuy());
                TestData.apiEurRateSell = Float.parseFloat(exchangeRatesDTO.getSale());
            } else {
                logger.info("there is no such ccy on ui");
            }

        }

//        logger.info(TestData.apiUsdRateBuy);
//        logger.info(TestData.apiUsdRateSell);
//        logger.info(TestData.apiEurRateBuy);
//        logger.info(TestData.apiEurRateSell);


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

    @Then("^'(.*)' rate from api and ui are equal$")
    public void ccyRateFromApiAndUiAreEqual(String ccy) {

        if (ccy.equals("USD")) {
            Assert.assertTrue(apiUsdRateBuy==uiUsdRateBuy);
            Assert.assertTrue(apiUsdRateSell==uiUsdRateSell);



        } else if(ccy.equals("EUR")) {
//            logger.info("apiEurRateBuy is " + apiEurRateBuy);
//            logger.info("apiEurRateSell is " + apiEurRateSell);
//            logger.info("uiEurRateBuy is " + uiEurRateBuy);
//            logger.info("uiEurRateSell is " + uiEurRateSell);
            Assert.assertTrue(apiEurRateBuy==uiEurRateBuy);
            Assert.assertTrue(apiEurRateSell==uiEurRateSell);


        }
    }
}
