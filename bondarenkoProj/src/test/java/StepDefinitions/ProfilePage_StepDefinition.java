package StepDefinitions;


import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import pages.MyProfilePage;

import static libs.DriverHelper.getWebDriver;


public class ProfilePage_StepDefinition {

        private MyProfilePage myProfilePage = new MyProfilePage(getWebDriver());

        @Then("^User is redirect to Profile page$")
        public void userIsRedirectToProfilePage() {
                myProfilePage.checkIsRedirectToMyProfilePage();
        }

        @And("^User sees (\\d+) posts in Post list on Profile page$")
        public void userSeesPostsInPostListOnProfilePage(int expectedNumberOfPosts) {
                myProfilePage.checkNumberOfPosts(expectedNumberOfPosts);
        }
}
