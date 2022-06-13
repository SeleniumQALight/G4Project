package apiTests;

import api.EndPoints;

import api.PrivatBank.PrivatBankDTO;
import api.PrivatBank.PrivatBankEndPoints;
import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class ApiPrivatBankTests {
    Logger logger = Logger.getLogger(getClass());

    @Test
    public void getPrivatPosts() {
        PrivatBankDTO[] responseBody = given()
                .contentType(ContentType.JSON)
                .param("exchange")
                .param("json")
                .param("coursid", 11)
                .log().all()
                .when()
                .get(PrivatBankEndPoints.EXCHANGE_COURSE)
                .then()
                .statusCode(200)
                .log().all()
                .extract()
                .response().as(PrivatBankDTO[].class);

        logger.info("Number of posts: " + responseBody.length);

        PrivatBankDTO[] expectedPrivatDTO = {
                new PrivatBankDTO("USD", "UAH"),
                new PrivatBankDTO("EUR", "UAH"),
                new PrivatBankDTO("RUR", "UAH"),
                new PrivatBankDTO("BTC", "USD")
        };

        Assert.assertEquals("Length doesn`t match expected one", expectedPrivatDTO.length, responseBody.length);

        SoftAssertions softAssertions = new SoftAssertions();

        for (int i = 0; i < expectedPrivatDTO.length; i++) {
            softAssertions.assertThat(responseBody[i]).isEqualToIgnoringGivenFields(expectedPrivatDTO[i],
                    "buy", "sale");
        }
        softAssertions.assertAll();
    }

    @Test
    public void getPrivatPostsBySchema(){
        given()
                .contentType(ContentType.JSON)
                .param("exchange")
                .param("json")
                .param("coursid", 11)
                .log().all()
                .when()
                .get(PrivatBankEndPoints.EXCHANGE_COURSE)
                .then()
                .statusCode(200)
                .log().all()
                .assertThat().body(matchesJsonSchemaInClasspath("privatBankResponse.json"));
    }
}
