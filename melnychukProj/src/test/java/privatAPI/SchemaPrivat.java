package privatAPI;

import api.privatAPI.PrivatEndpoints;
import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class SchemaPrivat {
    Logger logger = Logger.getLogger(getClass());

    @Test
    public void getAllCurrenciesSchema() {
        given()
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
                .assertThat().body(matchesJsonSchemaInClasspath("responsePrivat.json"));
    }

}
