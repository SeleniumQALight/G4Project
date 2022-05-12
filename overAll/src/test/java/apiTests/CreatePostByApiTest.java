package apiTests;

import org.junit.Test;

import api.ApiHelper;

public class CreatePostByApiTest {
    final String userName = "autotaras";
    final String passWord = "123456qwerty";

    ApiHelper apiHelper = new ApiHelper();

    @Test
    public void createPostByApi(){
        String token = apiHelper.getToken(userName, passWord);

    }
}
