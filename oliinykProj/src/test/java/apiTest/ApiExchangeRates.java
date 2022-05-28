package apiTest;


import api.EndPoints;
import api.ExchangeRatesDTO;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.junit.Test;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;


public class ApiExchangeRates {
    String BASE_CCY = "UAH";
    Logger logger = Logger.getLogger(getClass());

    @Test
    public void getExchangeRates(){
        ExchangeRatesDTO[] responseBody = given().contentType(ContentType.JSON)
                .log().all()
                .when().get(EndPoints.exchangeURL).then().statusCode(200)
                .log().all()
                .extract().response().as(ExchangeRatesDTO[].class);
        logger.info("Quantity of exchange rates: " + responseBody.length);

        for (int i = 0; i < responseBody.length-1; i++) {
            Assert.assertEquals("Base currency", BASE_CCY, responseBody[i].getBase_ccy());
            logger.info("Base currency " + responseBody[i].getBase_ccy());
        }
        Assert.assertEquals("Base currency", "USD", responseBody[responseBody.length-1].getBase_ccy());
        logger.info("Base currency for BC: " + responseBody[responseBody.length-1].getBase_ccy());

        ExchangeRatesDTO[] exchangeRatesDTOS = {
                new ExchangeRatesDTO("USD", BASE_CCY),
                new ExchangeRatesDTO("EUR", BASE_CCY),
                new ExchangeRatesDTO("RUR", BASE_CCY),
                new ExchangeRatesDTO("BTC", "USD")
        };

        Assert.assertEquals(exchangeRatesDTOS.length, responseBody.length);

        SoftAssertions softAssertions = new SoftAssertions();

        for (int i = 0; i < exchangeRatesDTOS.length; i++) {
            softAssertions.assertThat(responseBody[i]).isEqualToIgnoringGivenFields(exchangeRatesDTOS[i], "buy", "sale");
        }
        softAssertions.assertAll();
    }

    @Test
    public void privatSchemaValidation(){
        given()
                .contentType(ContentType.JSON).log().all()
                .when().get(EndPoints.exchangeURL)
                .then().statusCode(200).log().all().assertThat().body(matchesJsonSchemaInClasspath("schemaprivat.json"));
    }
}
