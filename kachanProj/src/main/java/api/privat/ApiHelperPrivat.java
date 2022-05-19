package api.privat;

import io.restassured.http.ContentType;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class ApiHelperPrivat {


    public void getCurrencyRate() {
        PrivatBankDTO[] response = given()
                .contentType(ContentType.JSON)
                .when()
                .get(EndPointsPrivatBank.CURRENCY_EXCHANGE)
                .then()
                .extract().response()
                .as(PrivatBankDTO[].class);

        Map<Currency, PrivatBankDTO> currencyMap = new HashMap<>();

        for (PrivatBankDTO privatBankDTO : response) {
            currencyMap.put(Currency.valueOf(privatBankDTO.getCcy()), privatBankDTO);
        }

    }
}
