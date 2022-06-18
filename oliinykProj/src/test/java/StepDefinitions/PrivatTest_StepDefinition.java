package StepDefinitions;

import api.examProject.ApiHelpForExchangeRates;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import libs.TestData;
import org.junit.Assert;
import pages.PrivatHomePage;

import static libs.DriverHelper.getDriver;
import static libs.TestData.*;

public class PrivatTest_StepDefinition {

    private ApiHelpForExchangeRates apiHelpForExchangeRates = new ApiHelpForExchangeRates();
    PrivatHomePage privatHomePage = new PrivatHomePage(getDriver());

    @Given("^Open main page$")
    public void openMainPage() {
        privatHomePage.openPrivatHomePage();
        privatHomePage.checkPrivatPageIsOpen();
    }

    @And("^Take take exchange rates from home page and set them into 'currency_buy' and 'currency_sale'$")
    public void takeTakeExchangeRatesFromHomePageAndSetThemIntoCurrency_buyAndCurrency_sale() {
        privatHomePage.webCourseBuy();
        privatHomePage.webCourseSell();
    }

    @And("^Take exchange rates from API request for exchange rates$")
    public void takeExchangeRatesFromAPIRequestForExchangeRates() {
        apiHelpForExchangeRates.getExchangeRates();
    }

    @Then("^Compare buy and sale exchange rates rates$")
    public void compareBuyAndSaleExchangeRatesRates() {
        Assert.assertTrue("Courses didn't match", buyCourseFromAPI.get("EUR").equals(buyCourseFromPrivatePage.get("EUR")));
        Assert.assertTrue("Courses didn't match", buyCourseFromAPI.get("USD").equals(buyCourseFromPrivatePage.get("USD")));
        Assert.assertTrue("Courses didn't match", sellCourseFromAPI.get("USD").equals(sellCourseFromPrivatePage.get("USD")));
        Assert.assertTrue("Courses didn't match", sellCourseFromAPI.get("EUR").equals(sellCourseFromPrivatePage.get("EUR")));
    }
}
