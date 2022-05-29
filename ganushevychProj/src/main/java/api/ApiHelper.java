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

    public final String userName = "sasha";
    private final String password = "Qwerty1234567";

    Logger logger = Logger.getLogger(getClass());

    RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    public String getToken(){
        return getToken(userName, password);
    }

    public String getToken(String userName, String password) {
        JSONObject requestParams = new JSONObject();
        requestParams.put("username", userName);
        requestParams.put("password", password);

        ResponseBody responseBody =
                given()
                        .spec(requestSpecification)
                        .body(requestParams.toMap())
                .when()
                        .post(EndPoints.LOGIN)
                        .then()
                        .statusCode(200)
                        .log().all()
                        .extract().response().getBody();

        return responseBody.asString().replace("\"", "");
    }

    public PostDTO[] getAllPostsByUser() {
        return getAllPostsByUser(userName);
    }
    public PostDTO[] getAllPostsByUser(String userName) {

        return given()
                .spec(requestSpecification)
            .when()
                .get(EndPoints.POST_BY_USER, userName)
            .then()
                .statusCode(200)
                .log().all()
                .extract().response().as(PostDTO[].class);
    }

    public void deletePostsTillPresent(){
        deletePostsTillPresent(userName, password);
    }

    public void deletePostsTillPresent(String username, String password){
        PostDTO[] listOfPosts = getAllPostsByUser(userName);

        for (int i = 0; i < listOfPosts.length; i++) {
            deletePostById(username, password, listOfPosts[i].get_id());
            logger.info(String.format("Post with id %s and title '%s' was deleted",
                    listOfPosts[i].get_id(), listOfPosts[i].getTitle()));
        }
        Assert.assertEquals("Number of posts ", 0, getAllPostsByUser(userName).length);
    }

    private void deletePostById(String username, String password, String id) {
        JSONObject bodyParams = new JSONObject();
        bodyParams.put("token", getToken(username, password));

        given()
                .spec(requestSpecification)
                .body(bodyParams.toMap())
        .when()
                .delete(EndPoints.DELETE_POST, id)
        .then()
                .statusCode(200)
                .log().all();
    }

    public void createPost(String title, String userName, String passWord){
        String token = getToken(userName.toLowerCase(), passWord);

        HashMap<String, String> requestParams = new HashMap<>();
        requestParams.put("title", title);
        requestParams.put("body", "post body");
        requestParams.put( "select1", "One Person");
        requestParams.put("token", token);

        given()
                .contentType(ContentType.JSON)
                .body(requestParams)
                .log().all()
                .when()
                .post(EndPoints.CREATE_POST)
                .then()
                .statusCode(200);

        logger.info("new post was created");
    }
}
