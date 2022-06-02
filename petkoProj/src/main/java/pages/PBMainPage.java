package pages;

import libs.PBTestData;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
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

    public void getUIRates(String currency){

        String locatorBuy = String.format(".//td[@id='%s_buy']", currency);
        String locatorSale = String.format(".//td[@id='%s_sell']", currency);

        PBTestData.UI_BUY_RATE = webDriver.findElement(By.xpath(locatorBuy)).getText().trim();
        PBTestData.UI_SALE_RATE = webDriver.findElement(By.xpath(locatorSale)).getText().trim();

    }

    public void compareCurrencyRates(){
        Assert.assertEquals("Buy rates for currency are not match", PBTestData.UI_BUY_RATE, PBTestData.API_BUY_RATE);
        Assert.assertEquals("Sale rates for currency are not match", PBTestData.UI_SALE_RATE, PBTestData.API_SALE_RATE);
        logger.info("Rates for currency are matched");
    }

}
