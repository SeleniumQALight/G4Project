package StepDefinitions;

import api.ApiHelper;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import gherkin.lexer.De;
import libs.TestData;

public class API_StepDefinitions {
    final String DEFAULT = "default";
    private ApiHelper apiHelper = new ApiHelper();

    @Given("^Create (\\d+) posts via API for '(.*)' user and '(.*)' password$")
    public void createPostsViaAPIForDefaultUserAndDefaultPassword(int numberOfPosts, String userName, String password) {
        if (DEFAULT.equalsIgnoreCase(userName)) {
            userName = TestData.VALID_LOGIN;
        }
        if (DEFAULT.equalsIgnoreCase(password)) {
            password = TestData.VALID_PASS;
        }

        for (int i = 0; i < numberOfPosts; i++) {
            apiHelper.createPost("Post from API " + i, userName, password);
        }
    }
}
