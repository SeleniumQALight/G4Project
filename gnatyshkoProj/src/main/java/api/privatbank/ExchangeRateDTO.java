package api.privatbank;
import com.google.gson.annotations.SerializedName;
public class ExchangeRateDTO {
    @SerializedName("ccy")
    Currency ccy;
    @SerializedName("base_ccy")
    Currency base_ccy;
    @SerializedName("buy")
    String buy;
    @SerializedName("sale")
    String sale;
    public Currency getCcy() {
        return ccy;
    }
    public void setCcy(Currency ccy) {
        this.ccy = ccy;
    }
    public Currency getBase_ccy() {
        return base_ccy;
    }
    public void setBase_ccy(Currency base_ccy) {
        this.base_ccy = base_ccy;
    }
    public String getBuy() {
        return buy;
    }
    public void setBuy(String buy) {
        this.buy = buy;
    }
    public String getSale() {
        return sale;
    }
    public void setSale(String sale) {
        this.sale = sale;
    }
    @Override
    public String toString() {
        return "ExchangeRateDTO{" +
                "ccy='" + ccy + '\'' +
                ", base_ccy='" + base_ccy + '\'' +
                ", buy='" + buy + '\'' +
                ", sale='" + sale + '\'' +
                '}';
    }
    public ExchangeRateDTO(Currency ccy, Currency base_ccy) {
        this.ccy = ccy;
        this.base_ccy = base_ccy;
    }
    public ExchangeRateDTO() {
    }
}



