package apiTest;

import api.ApiHelper;
import org.junit.Test;

public class CreatePostByApiTest {


        final String userName = "yuliiak";
        final String passWord = "1234567qwerty";

        ApiHelper apiHelper = new ApiHelper();

        @Test
        public void createPostByApi(){
            String token = apiHelper.getToken(userName, passWord);
        }

}
