package api.privatBank;

import io.restassured.http.ContentType;
import libs.PBTestData;


import java.util.Objects;

import static io.restassured.RestAssured.given;

public class PBApiHelper {

    public void getCurrencyRate() {
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

            if(Objects.equals(responseBody[i].getCcy(), "USD")){
                PBTestData.API_USD_BUY_RATE = responseBody[i].getBuy().substring(0,2);
                PBTestData.API_USD_SALE_RATE = responseBody[i].getSale().substring(0,4);
            }

            if(Objects.equals(responseBody[i].getCcy(), "EUR")){
                PBTestData.API_EUR_BUY_RATE = responseBody[i].getBuy().substring(0,4);
                PBTestData.API_EUR_SALE_RATE = responseBody[i].getSale().substring(0,4);
            }
        }
    }
}
