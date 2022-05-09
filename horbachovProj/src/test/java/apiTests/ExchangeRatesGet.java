package apiTests;


import api.EndPoints;
import api.ExchangeRatesDTO;
import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class ExchangeRatesGet {
    final String COURSE_ID = "11";
    Logger logger = Logger.getLogger(getClass());

    @Test
    public void getExchangeRates() {
        ExchangeRatesDTO[] responseBody = given()
                .contentType(ContentType.JSON)
                .log().all()
                .when()
                .get(EndPoints.EXCHANGE_RATES, COURSE_ID)
                .then()
                .statusCode(200)
                .log().all()
                .extract()
                .response().as(ExchangeRatesDTO[].class);
        logger.info("Number of rates " + responseBody.length);
        logger.info("CCY #1 " + responseBody[0].getCcy());

        for (int i = 0; i < responseBody.length; i++) {
            Assert.assertEquals("Number of rates  ", 4, responseBody.length);
        }

        ExchangeRatesDTO[] expectedExchangeRatesDTO = {
                new ExchangeRatesDTO("USD", "UAH", "29.25490", "31.34796"),
                new ExchangeRatesDTO("EUR", "UAH", "30.85950", "33.11258"),
                new ExchangeRatesDTO("RUR", "UAH", "0.32000", "0.35001"),
                new ExchangeRatesDTO("BTC", "USD", "32861.3266", "36320.4136")

        };

        Assert.assertEquals(expectedExchangeRatesDTO.length, responseBody.length);
//        Assert.assertEquals("USD buy rate differs", expectedExchangeRatesDTO[0].getCcy(), responseBody[0].getCcy());

        SoftAssertions softAssertions = new SoftAssertions();

        for (int i = 0; i < expectedExchangeRatesDTO.length; i++) {
            softAssertions.assertThat(responseBody[i])
                    .isEqualToIgnoringGivenFields(expectedExchangeRatesDTO[i], "buy", "sale");
        }

        softAssertions.assertAll();
    }


}
