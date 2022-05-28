package pages;

import libs.PBTestData;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PBMainPage {

    private WebDriver webDriver;

    protected String linkMainPagePB = "https://privatbank.ua/";
    Logger logger = Logger.getLogger(getClass());

    public PBMainPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(this.webDriver, this);
    }

    @FindBy(xpath = ".//td[@id='EUR_buy']")
    public WebElement rateEurBuy;

    @FindBy(xpath = ".//td[@id='EUR_sell']")
    public WebElement rateEurSale;

    @FindBy(xpath = ".//td[@id='USD_buy']")
    public WebElement rateUsdBuy;

    @FindBy(xpath = ".//td[@id='USD_sell']")
    public WebElement rateUsdSale;

    @FindBy(xpath = ".//a[@class='cookies-btn btn-success']")
    public WebElement buttonAcceptCookies;



    public void openMainPage(){
        try {
            webDriver.get(linkMainPagePB);
            logger.info("PB Main page was opened");
        }catch (Exception e){
            logger.error("Can not open PB Main Page" + e);
            Assert.fail("Can not open PB Main Page" + e);
        }
    }

    public void clickOnAcceptCookies() throws Exception{
            buttonAcceptCookies.click();
    }

    public void getUIRates(){
        PBTestData.UI_USD_BUY_RATE = rateUsdBuy.getText().trim();
        PBTestData.UI_USD_SALE_RATE = rateUsdSale.getText().trim();
        PBTestData.UI_EUR_BUY_RATE = rateEurBuy.getText().trim();
        PBTestData.UI_EUR_SALE_RATE = rateEurSale.getText().trim();

    }

    public void compareUsdRates(){
        Assert.assertEquals("Buy rates for USD are not match", PBTestData.UI_USD_BUY_RATE, PBTestData.API_USD_BUY_RATE);
        Assert.assertEquals("Sale rates for USD are not match", PBTestData.UI_USD_SALE_RATE, PBTestData.API_USD_SALE_RATE);
        logger.info("Rates for USD are matched");
    }

    public void compareEurRates(){
        Assert.assertEquals("Buy rates for EUR are not match", PBTestData.UI_EUR_BUY_RATE, PBTestData.API_EUR_BUY_RATE);
        Assert.assertEquals("Sale rates for EUR are not match", PBTestData.UI_EUR_SALE_RATE, PBTestData.API_EUR_SALE_RATE);
        logger.info("Rates for EUR are matched");
    }

}
