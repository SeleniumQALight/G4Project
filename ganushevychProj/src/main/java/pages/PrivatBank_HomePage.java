package pages;

import io.qameta.allure.Step;
import libs.DriverHelper;
import org.apache.commons.math3.util.Precision;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
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

    @Step
    public void openHomePage(){
        try{
            webDriver.get(BASE_URL);
            logger.info("PrivatBank Home page was opened");
        }catch (Exception e){
            logger.error("Can not open PrivatBank Home Page" + e);
            Assert.fail("Can not open PrivatBank Home Page" + e);
        }
        Assert.assertTrue(webDriver.getTitle().contains("ПриватБанк"));
    }

    @Step
    public void getExchangeRateOnHomePageAndSave(String currency) {
        WebElement fieldBuy = webDriver.findElement(By.id(String.format("%s_buy", currency)));
        WebElement fieldSell = webDriver.findElement(By.id(String.format("%s_sell", currency)));
        COURSE_BUY_UI = Precision.round(Double.parseDouble(fieldBuy.getText()), 2);
        COURSE_SALE_UI = Precision.round(Double.parseDouble(fieldSell.getText()), 2);
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
