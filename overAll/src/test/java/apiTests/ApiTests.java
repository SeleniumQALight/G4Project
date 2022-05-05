package apiTests;

import static io.restassured.RestAssured.given;

import org.junit.Test;

import api.EndPoints;
import io.restassured.http.ContentType;

public class ApiTests {
    final String USER_NAME = "autoapi";

    @Test
    public void getAllPostsByUser(){
          given()
                  .contentType(ContentType.JSON)
                  .log().all()
          .when()
                  .get(EndPoints.POST_BY_USER, USER_NAME)
          .then()
                  .statusCode(200)
                  .log().all();
    }
}
