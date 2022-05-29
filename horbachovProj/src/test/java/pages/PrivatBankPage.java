package pages;

import io.qameta.allure.Step;
import libs.TestData;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static libs.TestData.apiRateBuy;
import static libs.TestData.apiRateSell;

public class PrivatBankPage extends ParentPage {

    String ccyBuyRateLocator = "//*[@id='%S_buy']";
    String ccySellRateLocator = "//*[@id='%S_sell']";



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
            String ccyBuyRate = webDriver.findElement(By.xpath(String.format(ccyBuyRateLocator, ccy))).getText();
            TestData.uiRateBuy = Float.parseFloat(ccyBuyRate);
            String ccySellRate = webDriver.findElement(By.xpath(String.format(ccySellRateLocator, ccy))).getText();
            TestData.uiRateSell = Float.parseFloat(ccySellRate);

            logger.info("uiRateBuy is " + TestData.uiRateBuy);
            logger.info("uiRateSell is " + TestData.uiRateSell);
            logger.info("apiRateBuy is " + apiRateBuy);
            logger.info("apiRateSell is " + apiRateSell);

    }
    }

