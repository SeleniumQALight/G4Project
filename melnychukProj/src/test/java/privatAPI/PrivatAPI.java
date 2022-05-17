package privatAPI;

import api.EndPoints;
import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.junit.Test;
import api.privatAPI.PrivatDTO;
import api.privatAPI.PrivatEndpoints;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;


public class PrivatAPI {

    Logger logger = Logger.getLogger(getClass());

    @Test
    public void getCurrencyByPrivat() {

        PrivatDTO[] responceBody = given()
                .contentType(ContentType.JSON)
                .log().all()
                .param("exchange")
                .param("json")
                .param("coursid", 11)
                .when()
                .get(PrivatEndpoints.EXHANGE_BY_COURSEID)
                .then()
                .statusCode(200)
                .log().all()
                .extract()
                .response()
                .as(PrivatDTO[].class);

        logger.info("number of currencies" + responceBody.length);
        logger.info("currensy " + responceBody[0].getCcy());

        // home work
        PrivatDTO[] expectedPrivatDTO = {
                new PrivatDTO("USD", "UAH"),
                new PrivatDTO("EUR", "UAH"),
                new PrivatDTO("RUR", "UAH"),
                new PrivatDTO("BTC", "USD"),
        };
        //проверяем, что количество филдов єкспектед = количеству филдов что пришли
        Assert.assertEquals(expectedPrivatDTO.length, responceBody.length);

        //  после проверки по количеству проверяем сами филды
        SoftAssertions softAssertions = new SoftAssertions();
        for (int i = 0; i < expectedPrivatDTO.length; i++) {
            softAssertions
                    .assertThat(responceBody[i])
                    .isEqualToIgnoringGivenFields(expectedPrivatDTO[i], "buy", "sale");
        }

        softAssertions.assertAll();

    }

}
