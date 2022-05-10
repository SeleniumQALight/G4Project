package apiTests;

import api.PBEndPoints;
import api.PBExchangeRateDTO;
import io.restassured.http.ContentType;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class PBExchangeApiTests {

    @Test
    public void getCurrencyRate() {
        PBExchangeRateDTO[] responseBody = given()
                .contentType(ContentType.JSON)
                .log().all()
                .when()
                .get(PBEndPoints.EXCHANGE_RATE)
                .then()
                .statusCode(200)
                .log().all()
                .extract().response().as(PBExchangeRateDTO[].class);

        PBExchangeRateDTO[] expectedExchangeRateDTO = {
                new PBExchangeRateDTO("USD", "UAH", "29.25490", "31.34796"),
                new PBExchangeRateDTO("EUR", "UAH", "30.90630", "33.11258"),
                new PBExchangeRateDTO("RUR", "UAH", "0.32000", "0.35001"),
                new PBExchangeRateDTO("BTC", "USD", "31911.6146", "35270.7320")

        };

        Assert.assertEquals(expectedExchangeRateDTO.length, responseBody.length);

        SoftAssertions softAssertions = new SoftAssertions();

        for (int i = 0; i < expectedExchangeRateDTO.length; i++) {
            softAssertions.assertThat(responseBody[i])
                    .isEqualToIgnoringGivenFields(expectedExchangeRateDTO[i],"buy", "sale");
        }

        softAssertions.assertAll();
    }
}
