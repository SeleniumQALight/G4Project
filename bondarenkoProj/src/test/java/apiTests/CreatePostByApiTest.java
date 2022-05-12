package apiTests;

import api.ApiHelper;
import org.junit.Test;

public class CreatePostByApiTest {
    final String userName = "anbondarenko";
    final String password = "nasty2886nasty2886";

    ApiHelper apiHelper = new ApiHelper();

    @Test
    public void createPostByApi(){
        String token = apiHelper.getToken(userName, password);
    }
}
