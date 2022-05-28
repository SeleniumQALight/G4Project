package api.privatAPI;

import com.google.gson.annotations.SerializedName;

public class PrivatDTO {
    @SerializedName("ccy")
    String ccy;
    @SerializedName("base_ccy")
    String base_ccy;
    @SerializedName("buy")
    double buy;
    @SerializedName("sale")
    double sale;

    public PrivatDTO(String ccy, String base_ccy) {
        this.ccy = ccy;
        this.base_ccy = base_ccy;
    }
    // для ресашурд нужен дефолтный конструктор!
    public PrivatDTO(){

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

    public double getBuy() {
        return buy;
    }

    public void setBuy(double buy) {
        this.buy = buy;
    }

    public double getSale() {
        return sale;
    }

    public void setSale(double sale) {
        this.sale = sale;
    }

    @Override
    public String toString() {
        return "PrivatDTO{" +
                "ccy='" + ccy + '\'' +
                ", base_ccy='" + base_ccy + '\'' +
                '}';
    }
}
