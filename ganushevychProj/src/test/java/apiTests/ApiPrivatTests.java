package apiTests;

import api.EndPoints;

import api.PrivatDTO;
import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class ApiPrivatTests {
    Logger logger = Logger.getLogger(getClass());

    @Test
    public void getPrivatPosts() {
        PrivatDTO[] responseBody = given()
                .contentType(ContentType.JSON)
                .log().all()
                .when()
                .get(EndPoints.privatUrl)
                .then()
                .statusCode(200)
                .log().all()
                .extract()
                .response().as(PrivatDTO[].class);

        logger.info("Number of posts: " + responseBody.length);

        PrivatDTO[] expectedPrivatDTO = {
                new PrivatDTO("USD", "UAH"),
                new PrivatDTO("EUR", "UAH"),
                new PrivatDTO("RUR", "UAH"),
                new PrivatDTO("BTC", "USD")
        };

        Assert.assertEquals("Length doesn`t match expected one", expectedPrivatDTO.length, responseBody.length);

        SoftAssertions softAssertions = new SoftAssertions();

        for (int i = 0; i < expectedPrivatDTO.length; i++) {
            softAssertions.assertThat(responseBody[i]).isEqualToIgnoringGivenFields(expectedPrivatDTO[i],
                    "buy", "sale");
        }
        softAssertions.assertAll();
    }
}
