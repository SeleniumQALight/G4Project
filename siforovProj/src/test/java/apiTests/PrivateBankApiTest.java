package apiTests;

import api.PrivateBankEndpoints;
import io.restassured.http.ContentType;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class PrivateBankApiTest {

    @Test
    public void getCurrencyCourseTest() {
        PrivateCurrencyCourseDTO[] responseBody = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .log().all()
                .when()
                .get(PrivateBankEndpoints.GET_CURRENCY_COURSE)
                .then()
                .statusCode(200)
                .log().all()
                .extract()
                .response()
                .as(PrivateCurrencyCourseDTO[].class);

        PrivateCurrencyCourseDTO[] expectedResponseDTO = {
                new PrivateCurrencyCourseDTO("USD", "UAH" ),
                new PrivateCurrencyCourseDTO("EUR", "UAH" ),
                new PrivateCurrencyCourseDTO("RUR", "UAH" ),
                new PrivateCurrencyCourseDTO("BTC", "USD" )
        };

        Assert.assertEquals(expectedResponseDTO.length,responseBody.length);

        SoftAssertions softAssertions = new SoftAssertions();
        for (int i = 0; i < expectedResponseDTO.length; i++) {
            softAssertions
                    .assertThat(responseBody[i])
                    .isEqualToIgnoringGivenFields(expectedResponseDTO[i], "buy", "sale");
        }
        softAssertions.assertAll();
    }
}
