package api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.junit.Assert;

import static io.restassured.RestAssured.given;


public class ApiHelper {
    Logger logger = Logger.getLogger(getClass());
    public static final String userName = "yuliiak";
    private final String passWord = "1234567qwerty";

    RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    public String getToken() {
        return getToken(userName, passWord);
    }

    public String getToken(String userName, String passWord) {
        JSONObject requestParams = new JSONObject();
        requestParams.put("username", userName);
        requestParams.put("password", passWord);

        ResponseBody responseBody =
                given()
                        .spec(requestSpecification)
                        .body(requestParams.toMap())
                        .when()
                        .post(EndPoints.LOGIN)
                        .then()
                        .statusCode(200)
                        .log().all()
                        .extract().response().body();

        return responseBody.asString().replace("\"","");
    }

    public PostDTO[] getAllPostByUser() {
        return getAllPostsByUser(userName);
    }

    public PostDTO[] getAllPostsByUser(String userName) {
        return given()
                .spec(requestSpecification)
                .when()
                .get(EndPoints.POST_BY_USER,userName)
                .then()
                .statusCode(200)
                .log().all()
                .extract().response().as(PostDTO[].class);
    }

    public void deletePostsAllPresent() {
        deletePostsTillPresent(userName, passWord);

    }

    public void deletePostsTillPresent(String userName, String passWord) {
PostDTO[] listOfPosts = getAllPostsByUser(userName);
        for (int i = 0; i < listOfPosts.length; i++) {
            deletePostById(userName,passWord, listOfPosts[i].get_id());
            logger.info(String.format("Post with id %s and title '%s' was deleted"
                    , listOfPosts[i].get_id(), listOfPosts[i].getTitle()));
        }

        Assert.assertEquals("Number of posts ", 0 , getAllPostsByUser(userName).length);
    }

    private void deletePostById(String userName, String passWord, String id) {
        JSONObject bodyParams = new JSONObject();
        bodyParams.put("token", getToken(userName, passWord));
        given()
                .spec(requestSpecification)
                .body(bodyParams.toMap())
                .when()
                .delete(EndPoints.DELETE_POST, id)
                .then()
                .statusCode(200)
                .log().all();
    }
}
