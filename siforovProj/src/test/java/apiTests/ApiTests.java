package apiTests;

import api.Endpoints;
import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class ApiTests {

    final String USER_NAME = "autoapi";
    Logger logger = Logger.getLogger(getClass());

    @Test
    public void getAllPostsByUser() {
        PostDTO[] responseBody =
                given()
                        .contentType(ContentType.JSON)
                        .accept(ContentType.JSON)
                        .log().all()
                        .when()
                        .get(Endpoints.POST_BY_USER, USER_NAME)
                        .then()
                        .statusCode(200)
                        .log().all()
                        .extract()
                        .response()
                        .as(PostDTO[].class);

        logger.info("Number of messages is " + responseBody.length);
        logger.info("Post name is " + responseBody[0].title);
        logger.info("Username " + responseBody[0].author.username);
    }
}
