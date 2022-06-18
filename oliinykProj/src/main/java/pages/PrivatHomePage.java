package pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import static libs.TestData.*;

public class PrivatHomePage{
    Logger logger = Logger.getLogger(getClass());
    WebDriver driver;

    @FindBy (xpath = ".//article[@class='block_content courses']")
    private WebElement courseBlock;

    @FindBy(id = "EUR_buy")
    private WebElement buyEurCourse;

    @FindBy(id = "USD_buy")
    private WebElement buyUsdCourse;

    @FindBy(id = "EUR_sell")
    private WebElement sellEurCourse;

    @FindBy(id = "USD_sell")
    private WebElement sellUsdCourse;

    public PrivatHomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void openPrivatHomePage(){
        driver.get("https://privatbank.ua");
    }

    public void checkPrivatPageIsOpen(){
        Assert.assertTrue("Page was not open", courseBlock.isDisplayed());
    }

    public void webCourseBuy(){
        String eurCourseText = buyEurCourse.getText();
        String usdCourseText = buyUsdCourse.getText();
        buyCourseFromPrivatePage.put("EUR", Double.parseDouble(eurCourseText));
        buyCourseFromPrivatePage.put("USD", Double.parseDouble(usdCourseText));
        logger.info("Buy course web " + buyCourseFromPrivatePage);
    }

    public void webCourseSell() {
        String eurCourseText = sellEurCourse.getText();
        String usdCourseText = sellUsdCourse.getText();
        sellCourseFromPrivatePage.put("EUR", Double.parseDouble(eurCourseText));
        sellCourseFromPrivatePage.put("USD", Double.parseDouble(usdCourseText));
        logger.info("Sale course web " + sellCourseFromPrivatePage);
    }
}
