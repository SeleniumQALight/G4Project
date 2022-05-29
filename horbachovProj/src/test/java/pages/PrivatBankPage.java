package pages;

import io.qameta.allure.Step;
import libs.TestData;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PrivatBankPage extends ParentPage {

//    @FindBy(xpath = "//*[@id='USD_buy']")
//    private WebElement ccyUsdBuyRate;
//
//    @FindBy(xpath = "//*[@id='USD_sell']")
//    private WebElement ccyUsdSellRate;
//
//    @FindBy(xpath = "//*[@id='EUR_buy']")
//    private WebElement ccyEurBuyRate;
//
//    @FindBy(xpath = "//*[@id='EUR_sell']")
//    private WebElement ccyEurSellRate;



    public PrivatBankPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/";
    }

    @Step

    public void openPbUa() {
        try {
            webDriver.get("https://pb.ua");
            logger.info("pb.ua was opened ");
        } catch (Exception e) {
            logger.error("Cannot open pb.ua " + e);
            Assert.fail("Cannot open pb.ua" + e);
        }
    }

    public void readAndSaveUiRates(String ccy) {
        if (ccy.equals("USD")) {
            String ccyUsdBuyRate = webDriver.findElement(By.xpath("//*[@id='USD_buy']")).getText();
            String ccyUsdSellRate = webDriver.findElement(By.xpath("//*[@id='USD_sell']")).getText();
            TestData.uiUsdRateBuy = Float.parseFloat(ccyUsdBuyRate);
            TestData.uiUsdRateSell = Float.parseFloat(ccyUsdSellRate);


            logger.info("uiUsdRateBuy is " + TestData.uiUsdRateBuy);
            logger.info("uiUsdRateSell is " + TestData.uiUsdRateSell);
        } else if (ccy.equals("EUR")) {
            String ccyEurBuyRate = webDriver.findElement(By.xpath("//*[@id='EUR_buy']")).getText();
            String ccyEurSellRate = webDriver.findElement(By.xpath("//*[@id='EUR_sell']")).getText();
            TestData.uiEurRateBuy = Float.parseFloat(ccyEurBuyRate);
            TestData.uiEurRateSell = Float.parseFloat(ccyEurSellRate);

            logger.info("uiEurRateBuy is " + TestData.uiEurRateBuy);
            logger.info("uiEurRateSell is " + TestData.uiEurRateSell);

        } else {
            logger.info("there is no such ccy on ui");
        }
    }
    }

