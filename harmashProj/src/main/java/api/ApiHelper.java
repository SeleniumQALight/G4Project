package api;

import org.json.JSONObject;

public class ApiHelper {

    public String getToken(String userName, String passWord) {
        JSONObject requestParams = new JSONObject();
        requestParams.put("username", userName);
        requestParams.put("password", passWord);

        return null;
    }
}
