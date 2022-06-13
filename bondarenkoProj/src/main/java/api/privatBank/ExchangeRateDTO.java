package api.privatBank;

import com.google.gson.annotations.SerializedName;

public class ExchangeRateDTO {
    @SerializedName("ccy")
    String ccy;
    @SerializedName("base_ccy")
    String base_ccy;
    @SerializedName("buy")
    String buy;
    @SerializedName("sale")
    String sale;

    public ExchangeRateDTO(String ccy, String base_ccy, String buy, String sale) {
        this.ccy = ccy;
        this.base_ccy = base_ccy;
        this.buy = buy;
        this.sale = sale;
    }

    public ExchangeRateDTO(){

    }

    public String getCcy() {
        return ccy;
    }

    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

    public String getBase_ccy() {
        return base_ccy;
    }

    public void setBase_ccy(String base_ccy) {
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
                ", buy=" + buy + '\'' +
                ", sale=" + sale + '\'' +
                '}';
    }
}
