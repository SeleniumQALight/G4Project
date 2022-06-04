package api.PrivatBank;

import lombok.*;

import com.google.gson.annotations.SerializedName;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder

public class CurrencyPrivatDTO {
    @SerializedName("ccy")
    String ccy;
    @SerializedName("base_ccy")
    String base_ccy;
    @SerializedName("buy")
    String buy;
    @SerializedName("sale")
    String sale;

}
