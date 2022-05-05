package apiTests;

import api.Endpoints;
import io.restassured.http.ContentType;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class ApiTests {

    final String USER_NAME = "autoapi";

    @Test
    public void getAllPostsByUser() {
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .log().all()
        .when()
                .get(Endpoints.POST_BY_USER, USER_NAME)
        .then()
                .statusCode(200)
                .log().all();
    }
}
