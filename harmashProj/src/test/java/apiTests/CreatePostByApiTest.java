package apiTests;

import api.ApiHelper;
import org.junit.Test;

public class CreatePostByApiTest {
    final String userName = "fai";
    final String passWord = "fai123456789";
    ApiHelper apiHelper = new ApiHelper();
    @Test
    public void createPostByApi(){
        String token = apiHelper.getToken(userName, passWord);


    }
}
