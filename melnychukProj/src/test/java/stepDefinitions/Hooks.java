package StepDefinitions;

import apiTests.APIHelper;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

import libs.DriverHelper;
import libs.TestData;
import org.apache.log4j.Logger;


public class Hooks {
    DriverHelper driverHelper = new DriverHelper();
    private APIHelper apiHelper = new APIHelper();
    Logger logger = Logger.getLogger(getClass());

    @Before(order = 0)
    public void setUp(Scenario scenario) {
        logger.info(scenario.getName() + " was started");
        driverHelper.createDriver();
    }

    @After(order = 0)
    public void tearDown(Scenario scenario) {
        driverHelper.closeDriver();
        logger.info(scenario.getName() + " was ended with status " + scenario.getStatus());
    }

    @Before(value = "@BeforeDeletingAllPostsForDefaultUser", order = 100)
    @After(value = "@AfterDeletingAllPostsForDefaultUser", order = 50)

    public void deletingAllPostsForDefaultUser(){
        apiHelper.deletePostsTillPresent(TestData.VALID_LOGIN, TestData.VALID_PASS);

    }


}