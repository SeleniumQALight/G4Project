package pages.privatbank;

import libs.TestData;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
public class PrivatbankPage extends ParentPage {
   private String uiBuyCurrency = ".//*[@id = \"%s_buy\"]";
   private String uiSaleCurrency = ".//*[@id = \"%s_sell\"]";
   public PrivatbankPage(WebDriver webDriver) {
        super(webDriver);
    }
    public void openPrivatbankPage() {
        try {
            webDriver.get(baseUrl);
            logger.info("PrivatbankPage was open on " + baseUrl);
        } catch (Exception e) {
            logger.error("Cannot open PrivatbankPage " + e);
            Assert.fail("Cannot open PrivatbankPage " + e);
        }
    }
    public void getCurrencyBuyValue(String currency) {
        WebElement uiBuyСurrency = webDriver
                .findElement(
                        By.xpath(String.format(uiBuyCurrency, currency))
                );
        TestData.UI_BUY_CURRENCY = uiBuyСurrency.getText();
    }
    public void compareApiCurrencyWithUi(){
        SoftAssertions softAssertions = new SoftAssertions();

        softAssertions.assertThat(TestData.UI_BUY_CURRENCY).isEqualTo(TestData.API_BUY_CURRENCY);
        softAssertions.assertThat(TestData.UI_SALE_CURRENCY).isEqualTo(TestData.API_SALE_CURRENCY);

        softAssertions.assertAll();
    }

    public void getCurrencySaleValue(String currency) {
       WebElement uiSellCurrency = webDriver
               .findElement(
                       By.xpath(String.format(uiSaleCurrency, currency))
               );

        TestData.UI_SALE_CURRENCY = uiSellCurrency.getText();
    }
}
