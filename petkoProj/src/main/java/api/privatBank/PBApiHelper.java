package api.privatBank;

import io.restassured.http.ContentType;
import libs.PBTestData;

import java.text.DecimalFormat;
import java.util.Objects;

import static io.restassured.RestAssured.given;

public class PBApiHelper {

    DecimalFormat decimalFormat = new DecimalFormat( "#.#" );

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
                PBTestData.API_BUY_RATE = decimalFormat.format(Double.valueOf(responseBody[i].getBuy())).replace(",",".");
                PBTestData.API_SALE_RATE = decimalFormat.format(Double.valueOf(responseBody[i].getSale())).replace(",",".");
            }
        }
    }
}
