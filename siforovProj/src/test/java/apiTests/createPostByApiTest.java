package apiTests;

import api.ApiHelper;
import org.junit.Test;

public class createPostByApiTest {

    final String userName = "final";
    final String password = "111111111111";
    ApiHelper apiHelper = new ApiHelper();

    @Test
    public void createPostByApiTest() {
            String token = apiHelper.getToken(userName, password);
    }
}
