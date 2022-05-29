package StepDefinitions;

import api.ApiHelper;
import cucumber.api.java.en.Given;
import libs.TestData;

public class API_StepDefinition {
    final String DEFAULT = "default";
    private ApiHelper apiHelper = new ApiHelper();

    @Given("^Create (\\d+) new post via API for '(.*)' user and '(.*)' password$")
    public void createNewPostViaAPIForDefaultUserAndDefaultPassword
            (int numberOfPosts, String userName, String password) {
        if(DEFAULT.equalsIgnoreCase(userName)){
            userName = TestData.VALID_LOGIN;
        }
        if (DEFAULT.equalsIgnoreCase(password)){
            password = TestData.VALID_PASS;
        }
        for (int i = 0; i < numberOfPosts; i++) {
            apiHelper.createPostByApi("This post from api number " + i, userName, password);
        }

    }
}
