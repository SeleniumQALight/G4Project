package api;

import com.google.gson.annotations.SerializedName;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder

public class PrivatBankDTO {
    @SerializedName("ccy")
    String ccy;
    @SerializedName("base_ccy")
    String base_ccy;
    @SerializedName("buy")
    String buy;
    @SerializedName("sale")
    String sale;


}
