package apiTests;

import api.ApiHelper;
import org.junit.Test;

public class CreatePostByApiTest {

    final String userName = "ivantau777";
    final String password = "123456789123";

    ApiHelper apiHelper = new ApiHelper();

    @Test
    public void createPostByApi(){
        String token = apiHelper.getToken(userName, password);
    }
}
