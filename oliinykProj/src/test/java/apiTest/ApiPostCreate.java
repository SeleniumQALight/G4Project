package apiTest;

import api.ApiHelper;
import org.junit.Test;

public class ApiPostCreate {
    ApiHelper apiHelper = new ApiHelper();
    final String userName = "AOliinyk";
    final String password = "qwerty123456";

    @Test
    public void createPostByApi(){
        String token = apiHelper.getToken(userName, password);
    }

}
