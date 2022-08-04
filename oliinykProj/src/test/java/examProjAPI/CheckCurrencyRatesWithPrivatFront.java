package examProjAPI;

import api.EndPoints;
import api.ExchangeRatesDTO;
import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class CheckCurrencyRatesWithPrivatFront {
    Logger logger = Logger.getLogger(getClass());

    @Test
    public void getExchangeRates(){
        ExchangeRatesDTO[] responseBody = given().contentType(ContentType.JSON)
                .log().all()
                .when().get(EndPoints.exchangeURL).then().statusCode(200)
                .log().all()
                .extract().response().as(ExchangeRatesDTO[].class);
        logger.info("Quantity of exchange rates: " + responseBody.length);

        //HashMap<String, String> currency = new HashMap<>();
        HashMap<String ,String> currencyRatesSale = new HashMap<>();
        HashMap<String ,String> currencyRatesBuy = new HashMap<>();
        for (int i = 0; i < responseBody.length; i++) {
            currencyRatesSale.put(responseBody[i].getCcy(), responseBody[i].getSale());
            currencyRatesBuy.put(responseBody[i].getCcy(), responseBody[i].getBuy());
        }

        System.out.println("Sale course " + currencyRatesSale);
        System.out.println("Buy cours " + currencyRatesBuy);
    }
}
