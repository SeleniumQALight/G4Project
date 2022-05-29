package api.examProject;

import api.EndPoints;
import api.ExchangeRatesDTO;
import io.restassured.http.ContentType;
import org.apache.log4j.Logger;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class ApiHelpForExchangeRates {

    Logger logger = Logger.getLogger(getClass());

    public void getExchangeRates() {
        ExchangeRatesDTO[] responseBody = given().contentType(ContentType.JSON)
                .log().all()
                .when().get(EndPoints.exchangeURL).then().statusCode(200)
                .log().all()
                .extract().response().as(ExchangeRatesDTO[].class);
        logger.info("Quantity of exchange rates: " + responseBody.length);

        HashMap<String, String> currencyRatesSale = new HashMap<>();
        HashMap<String, String> currencyRatesBuy = new HashMap<>();
        for (int i = 0; i < responseBody.length; i++) {
            currencyRatesSale.put(responseBody[i].getCcy(), responseBody[i].getSale());
            currencyRatesBuy.put(responseBody[i].getCcy(), responseBody[i].getBuy());
        }

    }
}
