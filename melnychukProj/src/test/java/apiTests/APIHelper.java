package apiTests;

import api.EndPoints;
import api.PostDTO;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.junit.Assert;

import static io.restassured.RestAssured.given;

public class APIHelper {
    Logger logger= Logger.getLogger(getClass());

   public  static  final String  userName = "olhatest";
  private   final String password = "qwerty123456";


    RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    public String getToken(){
        return getToken(userName,password);
    }


    public String getToken(String userName, String password) {
        JSONObject requestParams = new JSONObject();
        requestParams.put("username", userName);
        requestParams.put("password",password);

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

        return responseBody.asString().replace("\"","");
    }
 public  PostDTO[] getAllPostsByUser(){
  return       getAllPostsByUser(userName);
 }

    public PostDTO[] getAllPostsByUser(String userName) {

        return  given()
                .spec(requestSpecification)
                .when()
                .get(EndPoints.POST_BY_USER,userName)
                .then()
                .statusCode(200)
                .log().all()
                .extract().response().as(PostDTO[].class);
    }

    public void deletePostsTillPresent() {
        deletePostsTillPresent(userName,password);

    }
    public void deletePostsTillPresent(String  userName, String password){
        PostDTO[] listsOfPosts = getAllPostsByUser(userName);

        for (int i = 0; i < listsOfPosts.length; i++) {
            deletePostById(userName,password,listsOfPosts[i].get_id());
            logger.info(String.format(
                    "Post with id %s and title '%s' was deleted "
            , listsOfPosts[i].get_id(),listsOfPosts[i].getTitle()));

        }
        Assert.assertEquals("Number of posts ", 0,getAllPostsByUser(userName).length);

    }

    private void deletePostById(String userName, String password, String id) {
        JSONObject bodyParams =  new JSONObject();
        bodyParams.put("token", getToken(userName,password));

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
