package StepDefinitions;

import cucumber.api.java.en.Then;
import libs.DriverHelper;
import pages.HomePage;

public class HomePage_StepDefinition {

    private HomePage homePage = new HomePage(DriverHelper.getWebDriver());

    @Then("^User sees 'Sign Out' button$")
    public void user_sees_Sign_Out_button(){
        homePage.checkIsButtonSignOutDisplayed();
    }
}
