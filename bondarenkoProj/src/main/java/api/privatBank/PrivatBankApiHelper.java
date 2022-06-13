package api.privatBank;

import io.restassured.http.ContentType;
import org.junit.Assert;
import static io.restassured.RestAssured.given;

public class PrivatBankApiHelper {

    public static String ExchangeRateBuyAPI;
    public static String ExchangeRateSaleAPI;

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



     //   for (int i = 0; i < responseBody.length; i++) {
            //if(Assert.assertEquals(responseBody[i].getCcy(), currency)){
           //     ExchangeRateBuyAPI = String.format((responseBody[i].getBuy()));
          //      ExchangeRateSaleAPI = String.format((responseBody[i].getSale()));
          //  }
      // }

    }
}
