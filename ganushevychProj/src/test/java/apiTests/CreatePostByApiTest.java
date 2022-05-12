package apiTests;

import api.ApiHelper;
import org.junit.Test;

public class CreatePostByApiTest {
    final String userName = "sasha";
    final String password = "Qwerty1234567";
    ApiHelper apiHelper = new ApiHelper();

    @Test
    public void createPostByApi(){
        String token = apiHelper.getToken(userName, password);

    }
}
