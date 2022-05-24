package StepDefinitions;



import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import pages.HomePage;
import pages.LoginPage;

import static libs.DriverHelper.getWebDriver;

public class HomePage_StepDefinition {
//    @Given("")
//    public void dfh(){
//
//    } //чтоб идея поняла что мы определияем хоп пейдж степ дефинишн степ

//}
    LoginPage loginPage = new LoginPage(getWebDriver());
    HomePage homePage = new HomePage(getWebDriver());

@Given("^User opens 'Home' page$")
public void userOpensHomePage() {
    loginPage.loginWithValidCred()
            .isButtonSignOutDisplayed();



}

    @When("^User click on 'Profile' button on 'Home' page$")
    public void userClickOnProfileButtonOnHomePage() {
    homePage.clickOnMyProfileButton();
    }
}
