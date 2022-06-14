package pages;

import api.privatBank.PrivatBankApiHelper;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class PrivatBankHomePage {

    Logger logger = Logger.getLogger(getClass());
    WebDriver webDriver;

    public static String exchangeRateBuyUI;
    public static String exchangeRateSaleUI;

    PrivatBankApiHelper privatBankApiHelper = new PrivatBankApiHelper();

    private String linkToHomePage = "https://privatbank.ua/";

    public void openPrivatBankHomePage() {
        try {
            webDriver.get(linkToHomePage);
            logger.info("Privat Bank Home Page was opened" + linkToHomePage);
        } catch (Exception e) {
            logger.error("Can not open Privat Bank Home Page" + e);
            Assert.fail("Can not open Privat Bank Home Page" + e);
        }
    }

    public void getCurrencyExchangeRateFromUI(String currency){

        String exchangeRateBuy = String.format(".//*[@id='%s_buy']", currency);
        String exchangeRateSale = String.format(".//*[@id='%s_sell']", currency);

        exchangeRateBuyUI = webDriver.findElement(By.xpath(exchangeRateBuy)).getText();
        exchangeRateSaleUI = webDriver.findElement(By.xpath(exchangeRateSale)).getText();

    }

    public void checkExchangeRatesAreTheSame(){
        Assert.assertEquals("Buy exchange rates for aren't the same", exchangeRateBuyUI, privatBankApiHelper.exchangeRateBuyAPI);
        Assert.assertEquals("Sale exchange rates for aren't the same", exchangeRateSaleUI, privatBankApiHelper.exchangeRateSaleAPI);

    }
}
