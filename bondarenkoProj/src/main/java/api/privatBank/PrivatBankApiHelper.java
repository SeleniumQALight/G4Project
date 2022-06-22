package api.privatBank;

import io.restassured.http.ContentType;

import java.util.Objects;

import static io.restassured.RestAssured.given;

public class PrivatBankApiHelper {

    public static String exchangeRateBuyAPI;
    public static String exchangeRateSaleAPI;

    public void getCurrencyExchangeRateFromAPI(String currency) {
        ExchangeRateDTO[] responseBody =
                given()
                        .contentType(ContentType.JSON)
                        .param("exchange")
                        .param("json")
                        .param("coursid", 5)
                        .log().all()
                        .when()
                        .get(EndPointsPrivatBank.EXCHANGE_RATE)
                        .then()
                        .statusCode(200)
                        .log().all()
                        .extract()
                        .response().as(ExchangeRateDTO[].class);



       for (int i = 0; i < responseBody.length; i++) {
            if(Objects.equals(responseBody[i].getCcy(), currency)){
                exchangeRateBuyAPI = String.format((responseBody[i].getBuy()).substring(0,4));
              exchangeRateSaleAPI = String.format((responseBody[i].getSale()).substring(0,4));
           }
      }

    }
}
