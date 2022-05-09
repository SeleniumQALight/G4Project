package api.privatBank;

import com.google.gson.annotations.SerializedName;

public class ExchangeRateDTO {
    @SerializedName("ccy")
    String ccy;
    @SerializedName("base_ccy")
    String base_ccy;
    @SerializedName("buy")
    Float buy;
    @SerializedName("sale")
    Float sale;

    public ExchangeRateDTO(String ccy, String base_ccy) {
        this.ccy = ccy;
        this.base_ccy = base_ccy;
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

    public Float getBuy() {
        return buy;
    }

    public void setBuy(Float buy) {
        this.buy = buy;
    }

    public Float getSale() {
        return sale;
    }

    public void setSale(Float sale) {
        this.sale = sale;
    }

    @Override
    public String toString() {
        return "ExchangeRateDTO{" +
                "ccy='" + ccy + '\'' +
                ", base_ccy='" + base_ccy + '\'' +
                ", buy=" + buy +
                ", sale=" + sale +
                '}';
    }
}
