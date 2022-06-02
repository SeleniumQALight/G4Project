package api.privatbank;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import libs.TestData;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;
public class ApiHelperPrivatBank {
    final String COURSE_ID = "5";

    RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setContentType(ContentType.JSON)
            .addQueryParam("exchange")
            .addQueryParam("json")
            .addQueryParam("coursid", COURSE_ID)
            .log(LogDetail.ALL)
            .build();
    public ValidatableResponse getRateByCurrency() {
        return given()
                .spec(requestSpecification)
                .when()
                .get(PrivatEndPoints.GET_EXCHANGE_RATE)
                .then()
                .statusCode(200)
                .log().all()
                ;
    }

    public List<String> getBuy() {
        return getRateByCurrency().extract().response().jsonPath().getList("buy", String.class);
    }
    public List<String> getSale() {
        return getRateByCurrency().extract().response().jsonPath().getList("sale", String.class);
    }

    public ExchangeRateDTO[] getListofItems (){
        return
                given()
                .contentType(ContentType.JSON)
                .queryParam("exchange")
                .queryParam("json")
                .queryParam("coursid", COURSE_ID)
                .when()
                .get(PrivatEndPoints.GET_EXCHANGE_RATE)
                .then()
                .statusCode(200)
                .log().all()
                .extract()
                .response().as(ExchangeRateDTO[].class);
    }
    public void getApiValueForCurrency(String currency) {
        ExchangeRateDTO[] response = getListofItems();
        String apiBuyValue = Arrays.stream(response)
                .filter(p -> p.getCcy().name().equals(currency))
                .findAny().get().getBuy();

        String apiSaleValue = Arrays.stream(response)
                .filter(p -> p.getCcy().name().equals(currency))
                .findAny().get().getSale();

        TestData.API_BUY_CURRENCY = String.valueOf(Double.parseDouble(apiBuyValue));
        TestData.API_SALE_CURRENCY = String.valueOf(Double.parseDouble(apiSaleValue));
    }






}