package api;

import com.google.gson.annotations.SerializedName;

public class PrivateCurrencyCourseDTO {
    @SerializedName("ccy")
    String ccy;

    @SerializedName("base_ccy")
    String base_ccy;

    @SerializedName("buy")
    String buy;

    @SerializedName("sale")
    String sale;

    public PrivateCurrencyCourseDTO(String ccy, String base_ccy) {
        this.ccy = ccy;
        this.base_ccy = base_ccy;
    }

    public PrivateCurrencyCourseDTO(){

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
        return "PrivateCurrencyCourseDTO{" +
                "ccy='" + ccy + '\'' +
                ", base_ccy='" + base_ccy + '\'' +
                ", buy='" + buy + '\'' +
                ", sale='" + sale + '\'' +
                '}';
    }
}
