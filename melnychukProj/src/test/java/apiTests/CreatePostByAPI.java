package apiTests;

import org.junit.Test;

public class CreatePostByAPI {

    final String  userName = "olhaTest";
    final String password = "qwerty123456";

    APIHelper apiHelper  = new APIHelper();

    @Test
    public void createPostByAPI(){
        String token  = apiHelper.getToken(userName,password);


    }
}
