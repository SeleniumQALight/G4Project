package apiTests;


import api.PrivatBank.CurrencyPrivatDTO;
import api.PrivatBank.EndPointsPrivatBank;
import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.junit.Test;


import static io.restassured.RestAssured.given;


public class apiPrivatBankTest {
    Logger logger = Logger.getLogger(getClass());

    @Test
    public void getAllCurrency() {
        CurrencyPrivatDTO[] responseBody =
                given()
                        .contentType(ContentType.JSON)
                        .param("exchange")
                        .param("json")
                        .param("coursid", 11)
                        .log().all()
                        .when()
                        .get(EndPointsPrivatBank.EXCHANGE_CURRENCY)
                        .then()
                        .statusCode(200)
                        .log().all()
                        .extract()
                        .response()
                        .as(CurrencyPrivatDTO[].class);
        logger.info("Number of currencies is " + responseBody.length);
        logger.info("Currency title is " + responseBody[0].getCcy());

        for (int i = 0; i < responseBody.length; i++) {
            Assert.assertEquals("Base SSY - ", "UAH", responseBody[2].getBase_ccy());
        }


        String allAvailableCurrencies = "";

        for (int i = 0; i < responseBody.length; i++) {
            if (responseBody.length > i + 1) {
                allAvailableCurrencies = allAvailableCurrencies + responseBody[i].getCcy() + ", ";
            } else {
                allAvailableCurrencies = allAvailableCurrencies + responseBody[i].getCcy();
            }
        }

        logger.info("All currencies - " + allAvailableCurrencies);

        CurrencyPrivatDTO[] expectedCurrencyPrivatDTO = {
                CurrencyPrivatDTO.builder().ccy("USD").base_ccy("UAH")
                        .build(),
                CurrencyPrivatDTO.builder().ccy("EUR").base_ccy("UAH")
                        .build(),
                CurrencyPrivatDTO.builder().ccy("RUR").base_ccy("UAH")
                        .build(),
                CurrencyPrivatDTO.builder().ccy("BTC").base_ccy("USD")
                        .build()


        };
        Assert.assertEquals(expectedCurrencyPrivatDTO.length, responseBody.length);
        SoftAssertions softAssertions = new SoftAssertions();
        for (int i = 0; i < expectedCurrencyPrivatDTO.length; i++) {
            softAssertions.assertThat(responseBody[i])
                    .isEqualToIgnoringGivenFields(expectedCurrencyPrivatDTO[i], "buy", "sale");

        }
        softAssertions.assertAll();
    }

    @Test
    public void getAllCurrencyNegative() {
        String actualResponse =
                given()
                        .contentType(ContentType.JSON)
                        .param("exchange")
                        .param("json")
                        .param("coursid", "notValidCourseId")
                        .log().all()
                        .when()
                        .get(EndPointsPrivatBank.EXCHANGE_CURRENCY)
                        .then()
                        .statusCode(200)
                        .log().all()
                        .extract().response().getBody().asString();
        Assert.assertEquals("Message in response", "invalid request", actualResponse
                .replace("<error>", "").replace("</error>", ""));
    }


}
