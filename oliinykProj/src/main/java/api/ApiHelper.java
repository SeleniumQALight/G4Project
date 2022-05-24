package api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.junit.Assert;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class ApiHelper {
    Logger logger = Logger.getLogger(getClass());
    public static final String userName = "aoliinyk";
    private final String password = "qwerty123456";

    RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setContentType(ContentType.JSON).log(LogDetail.ALL).build();

    public void deletePostsTillPresent() {
        deletePostsTillPresent(userName, password);
    }

    public void deletePostsTillPresent(String userName, String password) {
        PostDTO[] listOfPosts = allPostsByUser(userName);

        for (int i = 0; i < listOfPosts.length; i++) {

            deletePostByID(userName, password, listOfPosts[i].get_id());
            logger.info(String.format("Post with ID %s and title %s was deleted", listOfPosts[i].get_id(), listOfPosts[i].getTitle()));

        }
        Assert.assertEquals("Quantity of posts ", 0, allPostsByUser(userName).length);
    }

    private void deletePostByID(String userName, String password, String id) {
        JSONObject bodyParams = new JSONObject();
        bodyParams.put("token", getToken(userName, password));

        given().spec(requestSpecification).body(bodyParams.toMap())
                .when().delete(EndPoints.DELETE_POST, id)
                .then().statusCode(200).log().all();
    }

    public String getToken() {
        return getToken(userName, password);
    }

    public String getToken(String userName, String password) {
        JSONObject requestParams = new JSONObject();
        requestParams.put("username", userName);
        requestParams.put("password", password);

        ResponseBody responseBody = given()
                .spec(requestSpecification).body(requestParams.toMap())
                .when().post(EndPoints.LOGIN)
                .then().statusCode(200).log().all().extract().response().getBody();

        return responseBody.asString().replace("\"", "");
    }

    public PostDTO[] allPostsByUser() {
        return allPostsByUser(userName);
    }

    public PostDTO[] allPostsByUser(String userName) {
        return given()
                .spec(requestSpecification)
                .when().get(EndPoints.POST_BY_USER, userName)
                .then().statusCode(200).log().all().extract().response().as(PostDTO[].class);
    }

    public void createPostByApi(String title, String userName, String password) {
        String token = getToken(userName.toLowerCase(), password);

        HashMap<String, String> requestParams = new HashMap<>();
        requestParams.put("title", title);
        requestParams.put("body", "Post Body Andrii");
        requestParams.put("select1", "One Person");
        requestParams.put("token", token);

        given()
                .contentType(ContentType.JSON).body(requestParams).log().all()
                .when()
                .post(EndPoints.CREATE_POST)
                .then().statusCode(200);


    }
}