package StepDefinitions;

import apiTests.APIHelper;
import cucumber.api.java.en.Given;

import libs.TestData;

public class API_Step_Definition {

 final String DEFAULT = "default";
private APIHelper  apiHelper = new APIHelper();

    @Given("^Create (\\d+) new posts via API for '(.*)' user and '(.*)' password$")
    public void createNewPostsViaAPIForDefoultUserAndDefoultPassword(int numberOfPosts, String userName, String password) {
         if (DEFAULT.equalsIgnoreCase(userName)){
             userName = TestData.VALID_LOGIN;
         }
         if (DEFAULT.equalsIgnoreCase(password)){
             password = TestData.VALID_PASS;
         }
        for (int i = 0; i <numberOfPosts ; i++) {
            apiHelper.createPost("Post from API "+i, userName, password);

        }
    }
}
