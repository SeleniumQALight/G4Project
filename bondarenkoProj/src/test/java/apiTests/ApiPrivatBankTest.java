package apiTests;

import api.privatBank.EndPointsPrivatBank;
import api.privatBank.ExchangeRateDTO;
import io.restassured.http.ContentType;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class ApiPrivatBankTest {

    @Test
    public void getAllPostsByUser(){
        ExchangeRateDTO[] responseBody =  given()
                .contentType(ContentType.JSON)
                .log().all()
                .when()
                .get(EndPointsPrivatBank.EXCHANGE_RATE)
                .then()
                .statusCode(200)
                .log().all()
                .extract()
                .response().as(ExchangeRateDTO[].class);


        ExchangeRateDTO[] expectedExchangeRateDTO = {
                new ExchangeRateDTO("USD", "UAH"),
                new ExchangeRateDTO("EUR", "UAH"),
                new ExchangeRateDTO("RUR", "UAH"),
                new ExchangeRateDTO("BTC", "USD"),
        };

        Assert.assertEquals(expectedExchangeRateDTO.length, responseBody.length);

        SoftAssertions softAssertions = new SoftAssertions();

        for (int i = 0; i < expectedExchangeRateDTO.length; i++) {
            softAssertions.assertThat(responseBody[i])
                    .isEqualToIgnoringGivenFields(expectedExchangeRateDTO[i], "buy", "sale");
        }

            softAssertions.assertAll();
        }
}
