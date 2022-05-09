package apiTests;


import api.CurrencyPrivatDTO;
import api.EndPointsPrivatBank;
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
        CurrencyPrivatDTO[] responseBody = given()
                .contentType(ContentType.JSON)
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
                new CurrencyPrivatDTO("USD", "UAH", "29.25490", "32.05128"),
                new CurrencyPrivatDTO("EUR", "UAH", "30.86100", "33.78378"),
                new CurrencyPrivatDTO("RUR", "UAH", "0.32000", "0.35001"),
                new CurrencyPrivatDTO("BTC", "USD", "29727.0595", "32856.2237")


        };
        Assert.assertEquals(expectedCurrencyPrivatDTO.length, responseBody.length);
        SoftAssertions softAssertions = new SoftAssertions();
        for (int i = 0; i < expectedCurrencyPrivatDTO.length; i++) {
            softAssertions.assertThat(responseBody[i])
                    .isEqualToIgnoringGivenFields(expectedCurrencyPrivatDTO[i], "buy", "sale");

        }
        softAssertions.assertAll();

    }


}
