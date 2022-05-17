package apiTests;

import api.PrivateBankEndpoints;
import io.restassured.http.ContentType;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class getAllCoursesBySchema {

    @Test
    public void getAllCoursesBySchema(){
        given()
                .contentType(ContentType.JSON)
                .param("exchange")
                .param("json")
                .param("coursid", 11)
                .log().all()
        .when()
                .get(PrivateBankEndpoints.GET_CURRENCY_COURSE)
        .then()
                .statusCode(200)
                .log().all()
                .assertThat().body(matchesJsonSchemaInClasspath("privateBankApiResponse.json"));
    }
}
