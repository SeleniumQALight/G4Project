package apiTests;

import api.privatbank.ApiHelperPrivatBank;
import api.privatbank.Currency;
import api.privatbank.ExchangeRateDTO;
import api.privatbank.PrivatEndPoints;
import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class PrivatApiTests {
    final String COURSE_ID = "5";
    Logger logger = Logger.getLogger(getClass());

    ApiHelperPrivatBank apiHelperPrivatBank = new ApiHelperPrivatBank();

    @Test
    public void allRatesToUahCurrency() {
        ExchangeRateDTO[] responseBody = given()
                .contentType(ContentType.JSON)
                .queryParam("exchange")
                .queryParam("json")
                .queryParam("coursid", COURSE_ID)
                .when()
                .get(PrivatEndPoints.GET_EXCHANGE_RATE)
                .then()
                .statusCode(200)
                .log().all()
                .extract()
                .response().as(ExchangeRateDTO[].class);


        logger.info("The number of exchange rates is: " + responseBody.length);

        ExchangeRateDTO[] expectedDTO = {
                new ExchangeRateDTO(Currency.USD, Currency.UAH),
                new ExchangeRateDTO(Currency.EUR, Currency.UAH),
                new ExchangeRateDTO(Currency.RUR, Currency.UAH),
                new ExchangeRateDTO(Currency.BTC, Currency.USD)
        };

        Assert.assertEquals(expectedDTO.length, responseBody.length);

        SoftAssertions softAssertions = new SoftAssertions();

        for (int i = 0; i < expectedDTO.length; i++) {
            softAssertions.assertThat(responseBody[i])
                    .isEqualToIgnoringGivenFields(expectedDTO[i], "buy", "sale");
        }
        softAssertions.assertAll();
    }

    @Test
    public void checkSchema() {
        apiHelperPrivatBank.getRateByCurrency()
                .assertThat().body(matchesJsonSchemaInClasspath("responsePrivatBank.json"));
    }

    @Test
    public void checkBuyAndSale() {
        List<String> actualBuyValueList = apiHelperPrivatBank.getBuy();
        List<String> actualSaleValueList = apiHelperPrivatBank.getSale();

        SoftAssertions softAssertions = new SoftAssertions();

        for (int i = 0; i <actualBuyValueList.size(); i++) {
            softAssertions.assertThat(actualBuyValueList.get(i))
                    .isGreaterThan("0");
        }

        for (int i = 0; i < actualSaleValueList.size(); i++) {
            softAssertions.assertThat(actualSaleValueList.get(i))
                    .isGreaterThan("0");
        }
        softAssertions.assertAll();
    }

}
