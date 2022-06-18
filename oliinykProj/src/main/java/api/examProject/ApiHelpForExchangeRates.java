package api.examProject;

import api.EndPoints;
import api.ExchangeRatesDTO;
import io.restassured.http.ContentType;
import org.apache.log4j.Logger;


import static io.restassured.RestAssured.given;
import static libs.TestData.*;

public class ApiHelpForExchangeRates {

    Logger logger = Logger.getLogger(getClass());

    public void getExchangeRates() {
        ExchangeRatesDTO[] responseBody = given().contentType(ContentType.JSON)
                .log().all()
                .when().get(EndPoints.exchangeURL).then().statusCode(200)
                .log().all()
                .extract().response().as(ExchangeRatesDTO[].class);
        logger.info("Quantity of exchange rates: " + responseBody.length);

        for (int i = 0; i < responseBody.length; i++) {
            String sellCourseText = responseBody[i].getSale();
            String buyCourseText = responseBody[i].getBuy();
            sellCourseFromAPI.put(responseBody[i].getCcy(), Double.parseDouble(sellCourseText));
            buyCourseFromAPI.put(responseBody[i].getCcy(), Double.parseDouble(buyCourseText));
        }
        logger.info("Sell course API " + sellCourseFromAPI);
        logger.info("Buy course API " + buyCourseFromAPI);

    }
}
