package apiTestsPrivatBank;

import api.privatBank.PrivatBankEndpoints;
import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class ApiTestsPrivatBank {

    Logger logger = Logger.getLogger(getClass());

    @Test
    public void exchangeRatesJsonSchema(){
        given()
                .contentType(ContentType.JSON)
                .param("exchange")
                .param("json")
                .param("coursid", 11)
                .log().all()
                .when()
                .get(PrivatBankEndpoints.GET_CURRENCY_EXCHANGE_RATE)
                .then()
                .statusCode(200)
                .log().all()
                .assertThat().body(matchesJsonSchemaInClasspath("privat_json_schema.json"));
    }

}
