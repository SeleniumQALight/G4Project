package api.privatBank;

import io.restassured.http.ContentType;
import libs.PBTestData;


import java.util.Objects;

import static io.restassured.RestAssured.given;

public class PBApiHelper {

    public void getCurrencyRate(String currency) {
        PBExchangeRateDTO[] responseBody = given()
                .contentType(ContentType.JSON)
                .param("exchange")
                .param("json")
                .param("coursid", 5)
                .log().all()
                .when()
                .get(PBEndPoints.EXCHANGE_RATE)
                .then()
                .statusCode(200)
                .log().all()
                .extract().response().as(PBExchangeRateDTO[].class);

        for (int i = 0; i < responseBody.length; i++) {
            if(Objects.equals(responseBody[i].getCcy(), currency)){
                PBTestData.CURRENCY = currency;
                PBTestData.API_BUY_RATE = responseBody[i].getBuy().substring(0,4);
                PBTestData.API_SALE_RATE = responseBody[i].getSale().substring(0,4);
            }
        }
    }
}
