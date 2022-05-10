package api;

import org.json.JSONObject;

public class ApiHelper {

    public String getToken(String username, String password) {
        JSONObject requestParams = new JSONObject();
        requestParams.put("username", username);
        requestParams.put("password", password);

        

        return null;
    }
}
