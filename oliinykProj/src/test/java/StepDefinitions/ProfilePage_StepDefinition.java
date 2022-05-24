package StepDefinitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import pages.MyProfilePage;

import static libs.DriverHelper.getDriver;

public class ProfilePage_StepDefinition {
    private MyProfilePage myProfilePage = new MyProfilePage(getDriver());

    @Then("^User is redirect to Profile page$")
    public void userIsRedirectToProfilePage() {
        myProfilePage.checkIsRedirectToMyProfilePage();
    }

    @And("^User sees (\\d+) posts in Post list on Profile page$")
    public void userSeesPostsInPostListOnProfilePage(int expectedNumberOfPosts) {
        myProfilePage.checkNumberOfPosts(expectedNumberOfPosts);
    }
}
