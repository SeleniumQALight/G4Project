package api.privatBank;

import io.restassured.http.ContentType;

import java.text.DecimalFormat;
import java.util.Objects;

import static io.restassured.RestAssured.given;

public class PrivatBankApiHelper {

    public static String exchangeRateBuyAPI;
    public static String exchangeRateSaleAPI;

    DecimalFormat df = new DecimalFormat("#.0");

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
                exchangeRateBuyAPI = df.format(Float.parseFloat(responseBody[i].getBuy())).replace(",",".");
              exchangeRateSaleAPI = df.format(Float.parseFloat(responseBody[i].getSale())).replace(",",".");
           }
      }

    }
}
