package api.PrivatBank;

import io.restassured.http.ContentType;
import org.apache.log4j.Logger;

import static io.restassured.RestAssured.given;
import static libs.TestData.COURSE_BUY_API;
import static libs.TestData.COURSE_SALE_API;

public class PrivatBankApiHelper {
    Logger logger = Logger.getLogger(getClass());

    public void getExchangeRateByCurrency(String currency) {

        PrivatBankDTO[] responseBody = given()
                .contentType(ContentType.JSON)
                .param("exchange")
                .param("json")
                .param("coursid", 5)
                .log().all()
                .when()
                .get(PrivatBankEndPoints.EXCHANGE_COURSE)
                .then()
                .statusCode(200)
                .extract()
                .response().as(PrivatBankDTO[].class);

        for (int i = 0; i < responseBody.length; i++) {
            if (responseBody[i].getCcy().equals(currency)){
                COURSE_BUY_API = Double.parseDouble(responseBody[i].getBuy());
                COURSE_SALE_API = Double.parseDouble(responseBody[i].getSale());
                logger.info("Валюта " + currency);
                logger.info("Курс купівлі валюти (API): " + COURSE_BUY_API);
                logger.info("Курс продажу валюти (API): " + COURSE_SALE_API);
            }
        }
    }
}
