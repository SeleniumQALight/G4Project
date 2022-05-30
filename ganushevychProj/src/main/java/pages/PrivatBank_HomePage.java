package pages;

import io.qameta.allure.Step;
import libs.DriverHelper;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementLocatorFactory;

import static libs.TestData.*;

public class PrivatBank_HomePage {
    private final String BASE_URL = "https://privatbank.ua/";
    Logger logger = Logger.getLogger(getClass());
    WebDriver webDriver;

    public PrivatBank_HomePage(){
        this.webDriver = DriverHelper.getWebDriver();
        PageFactory.initElements(
                new HtmlElementDecorator(
                        new HtmlElementLocatorFactory(webDriver))
                ,this);
    }

    @FindBy(id = "EUR_buy")
    private WebElement fieldEUR_buy;

    @FindBy(id = "EUR_sell")
    private WebElement fieldEUR_sale;

    @FindBy(id = "USD_buy")
    private WebElement fieldUSD_buy;

    @FindBy(id = "USD_sell")
    private WebElement fieldUSD_sell;

    @Step
    public void openHomePage(){
        try{
            webDriver.get(BASE_URL);
            logger.info("Login page was opened");
        }catch (Exception e){
            logger.error("Can not open Login Page" + e);
            Assert.fail("Can not open Login Page" + e);
        }
        Assert.assertTrue(webDriver.getTitle().contains("ПриватБанк"));
    }

    @Step
    public void getExchangeRateOnHomePageAndSave(String currency) {
        if (currency.equals("USD")){
            COURSE_BUY_UI = Double.parseDouble(fieldUSD_buy.getText());
            COURSE_SALE_UI = Double.parseDouble(fieldUSD_sell.getText());
        }else if (currency.equals("EUR")){
            COURSE_BUY_UI = Double.parseDouble(fieldEUR_buy.getText());
            COURSE_SALE_UI = Double.parseDouble(fieldEUR_sale.getText());
        }
        logger.info("------------------------------------");
        logger.info("Курс купівлі валюти (UI): " + COURSE_BUY_UI);
        logger.info("Курс продажу валюти (UI): " + COURSE_SALE_UI);
    }

    @Step
    public void checkExchangeRateMatching() {
        Assert.assertEquals("Курс купівлі валюти не співпадає", COURSE_BUY_API, COURSE_BUY_UI);
        Assert.assertEquals("Курс продажу валюти не співпадає", COURSE_SALE_API, COURSE_SALE_UI);
        logger.info("Курси продажу та купівлі валюти спіпадає");
    }
}
