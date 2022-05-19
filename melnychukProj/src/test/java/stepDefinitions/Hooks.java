package StepDefinitions;

import cucumber.api.java.Before;
import io.cucumber.java.After;
import libs.DriverHelper;


public class Hooks {
    DriverHelper driverHelper= new DriverHelper();

    @Before
    public void setUp(){
        driverHelper.createDriver();

    }

    @After
    public void tearDown(){
        driverHelper.closeDriver();

    }

}
