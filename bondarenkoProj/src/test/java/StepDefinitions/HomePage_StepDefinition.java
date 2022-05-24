package StepDefinitions;

import cucumber.api.java.en.Then;
import libs.DriverHelper;
import pages.HomePage;


public class HomePage_StepDefinition {

        private HomePage homePage = new HomePage(DriverHelper.getWebDriver());


        @Then("^User sees 'SignOut' button on 'Home' page$")
        public void user_sees_SignOut_button_on_Home_page() {
                homePage.isButtonSignOutDisplayed();
        }

}
