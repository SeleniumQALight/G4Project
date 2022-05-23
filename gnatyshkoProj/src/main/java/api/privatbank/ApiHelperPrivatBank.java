package api.privatbank;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import java.util.List;
import static io.restassured.RestAssured.given;
public class ApiHelperPrivatBank {
    final String COURSE_ID = "11";
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
                .get(PrivatEndPoins.GET_EXCHANGE_RATE)
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
}