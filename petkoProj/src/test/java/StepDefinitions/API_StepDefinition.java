package StepDefinitions;

import api.ApiHelper;
import cucumber.api.java.en.Given;
import libs.TestData;

public class API_StepDefinition {

    final String DEFAULT = "default";
    private ApiHelper apiHelper = new ApiHelper();

    @Given("^Create (\\d+) new posts via API for '(.*)' user and '(.*)' password$")
    public void createNewPostsViaAPIForDefaultUserAndDefaultPassword(int numberOfPosts, String username, String password) {
        if(DEFAULT.equalsIgnoreCase(username)){
            username = TestData.VALID_LOGIN;
        }
        if(DEFAULT.equalsIgnoreCase(password)){
            password = TestData.VALID_PASS;
        }

        for (int i = 0; i < numberOfPosts; i++) {
            apiHelper.createPost("Post from API " + i, username, password);
        }
    }
}
