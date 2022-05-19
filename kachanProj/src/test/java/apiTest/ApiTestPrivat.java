package apiTest;

import api.privat.EndPointsPrivatBank;
import api.privat.PrivatBankDTO;
import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class ApiTestPrivat {

    Logger logger = Logger.getLogger(getClass());

    @Test
    public void getCurrencyExchange() {
        PrivatBankDTO[] responseBody = given()
                .contentType(ContentType.JSON)
                .log().all()
                .when()
                .get(EndPointsPrivatBank.CURRENCY_EXCHANGE)
                .then()
                .statusCode(200)
                .log().all()
                .extract()
                .response().as(PrivatBankDTO[].class);
        logger.info("Number of rates " + responseBody.length);


        PrivatBankDTO[] expectedPrivatBankDTO = {
                PrivatBankDTO.builder().ccy("USD").base_ccy("UAH").build(),
                PrivatBankDTO.builder().ccy("EUR").base_ccy("UAH").build(),
                PrivatBankDTO.builder().ccy("RUR").base_ccy("UAH").build(),
                PrivatBankDTO.builder().ccy("BTC").base_ccy("USD").build(),
        };

        Assert.assertEquals("Number of rates ", expectedPrivatBankDTO.length, responseBody.length);

        SoftAssertions softAssertions = new SoftAssertions();
        for (int i = 0; i < expectedPrivatBankDTO.length; i++) {
            softAssertions.assertThat(responseBody[i])
                    .isEqualToIgnoringGivenFields(expectedPrivatBankDTO[i], "buy", "sale");

        }
        softAssertions.assertAll();
    }

    @Test
    public void getCurrencyBySchema() {
        given()
                .contentType(ContentType.JSON)
                .log().all()
                .when()
                .get(EndPointsPrivatBank.CURRENCY_EXCHANGE)
                .then()
                .statusCode(200)
                .log().all()
               .assertThat().body(matchesJsonSchemaInClasspath("responsePrivat.json"));
    }

}
