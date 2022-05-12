package apiTests;

import api.ApiHelper;
import org.junit.Test;

public class CreatePostByApiTest {
    final String username = "serge";
    final String password = "qwerty123456";

    ApiHelper apiHelper = new ApiHelper();

    @Test
    public void createPostByApi(){
        String token = apiHelper.getToken(username, password);
    }
}
