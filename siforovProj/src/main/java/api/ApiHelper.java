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

    public static final String USER_NAME = "romansiforov";
    private String password = "111111111111";
    Logger logger = Logger.getLogger(getClass());

    RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    public String getToken(String userName, String password) {
        JSONObject requestParams = new JSONObject();
        requestParams.put("username", userName);
        requestParams.put("password", password);

        ResponseBody responseBody =
                given()
                        .spec(requestSpecification)
                        .body(requestParams.toMap())
                .when()
                        .post(Endpoints.LOGIN)
                .then()
                        .statusCode(200)
                        .log().all()
                        .extract().response().getBody();
        return responseBody.asString().replace("\"","");
    }

    public String getToken() {
        return getToken(USER_NAME, password);
    }

    public PostDTO[] getAllPostsByUser() {
        return getAllPostsByUser(USER_NAME);
    }

    public PostDTO[] getAllPostsByUser(String userName) {
        return given()
                .spec(requestSpecification)
               .when()
                .get(Endpoints.POST_BY_USER, USER_NAME)
               .then()
                .statusCode(200)
                .log().all()
                .extract().response().as(PostDTO[].class);
    }

    public void deletePostsTillPresent() {
        deletePostsTillPresent(USER_NAME, password);
    }

    private void deletePostsTillPresent(String userName, String password) {
        PostDTO[] listOfPosts = getAllPostsByUser(userName);

        for (int i = 0; i < listOfPosts.length; i++) {
            deletePostById(userName, password, listOfPosts[i].get_id());
            logger.info(String.format("Post with id %s and with title %s was deleted",
                    listOfPosts[i].get_id(), listOfPosts[i].getTitle()));
        }
        Assert.assertEquals("Number of pposts", 0  , getAllPostsByUser(userName).length);
    }

    private void deletePostById(String userName, String password, String id) {
        JSONObject bodyParams = new JSONObject();
        bodyParams.put("token", getToken(userName, password));

        given()
                .spec(requestSpecification)
                .body(bodyParams.toMap())
        .when()
                .delete(Endpoints.DELETE_POST, id)
        .then()
                .statusCode(200)
                .log().all();
    }
}
