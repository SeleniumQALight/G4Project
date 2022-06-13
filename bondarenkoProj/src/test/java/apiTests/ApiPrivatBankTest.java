package apiTests;

import api.privatBank.EndPointsPrivatBank;
import api.privatBank.ExchangeRateDTO;
import io.restassured.http.ContentType;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class ApiPrivatBankTest {

    @Test
    public void getNonCashExchangeRates(){
        ExchangeRateDTO[] responseBody =
        given()
                .contentType(ContentType.JSON)
                .param("exchange")
                .param("json")
                .param("coursid", 11)
                .log().all()
        .when()
                .get(EndPointsPrivatBank.EXCHANGE_RATE)
        .then()
                .statusCode(200)
                .log().all()
                .extract()
                .response().as(ExchangeRateDTO[].class);


        ExchangeRateDTO[] expectedExchangeRateDTO = {
                new ExchangeRateDTO("USD", "UAH", "29.25490", "32.78689"),
                new ExchangeRateDTO("EUR", "UAH", "30.93270", "34.36426"),
                new ExchangeRateDTO("RUR", "UAH", "0.32000", "0.35001"),
                new ExchangeRateDTO("BTC", "USD", "23555.0129", "26034.4879"),
        };

        Assert.assertEquals(expectedExchangeRateDTO.length, responseBody.length);

        SoftAssertions softAssertions = new SoftAssertions();

        for (int i = 0; i < expectedExchangeRateDTO.length; i++) {
            softAssertions.assertThat(responseBody[i])
                    .isEqualToIgnoringGivenFields(expectedExchangeRateDTO[i], "buy", "sale");
        }

            softAssertions.assertAll();
        }

        @Test
        public void getNonCashExchangeRatesSchema(){
            given()
                    .contentType(ContentType.JSON)
                    .param("exchange")
                    .param("json")
                    .param("coursid", 11)
                    .log().all()
            .when()
                    .get(EndPointsPrivatBank.EXCHANGE_RATE)
            .then()
                    .statusCode(200)
                    .log().all()
                    .assertThat().body(matchesJsonSchemaInClasspath("responsePrivat.json"));
        }
}

