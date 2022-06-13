package pages;

import io.qameta.allure.Step;
import libs.TestData;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static libs.TestData.apiPrivatRateBuy;
import static libs.TestData.apiPrivatRateSell;

public class PrivatRatesPage extends ParentPage {

    String ccyBuyRateLocator = ".//td[@id='%s_buy']";
    String ccySellRateLocator = ".//td[@id='%s_sell']";

    public PrivatRatesPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/";
    }
    @Step

    public void openPrivatSite() {
        try {
            webDriver.get("https://pb.ua");
            logger.info("Site was opened ");
        } catch (Exception e) {
            logger.error("Cannot open privat site " + e);
            Assert.fail("Cannot open privat site" + e);
        }
    }

    public void getUiRates(String ccy) {
        String ccyBuyRate = webDriver.findElement(By.xpath(String.format(ccyBuyRateLocator, ccy))).getText();

        String ccySellRate = webDriver.findElement(By.xpath(String.format(ccySellRateLocator, ccy))).getText();


        logger.info("uiRateBuy is " + TestData.uiPrivatRateBuy);
        logger.info("uiRateSell is " + TestData.uiPrivatRateSell);
        logger.info("apiRateBuy is " + apiPrivatRateBuy);
        logger.info("apiRateSell is " + apiPrivatRateSell);

    }

}



