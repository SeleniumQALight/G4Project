package apiTests;

import org.json.JSONObject;

public class APIHelper {

    public String getToken(String userName, String password) {
        JSONObject requestParams = new JSONObject();
        requestParams.put("username", userName);
        requestParams.put("password",password);


        return null;
    }
}
