package apiTests;

import api.*;
import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class CreatePostByApiTest {

    Logger logger = Logger.getLogger(getClass());
    ApiHelper apiHelper = new ApiHelper();

    @Before
    public void deleteAllPosts() {
        apiHelper.deletePostsTillPresent();
    }

    @Test
    public void createPostByApi(){
        String token = apiHelper.getToken();
//        logger.info(token);

        HashMap<String, String> requestParams = new HashMap<>();
        requestParams.put("title", "Serge's post/api");
        requestParams.put("body", "post body");
        requestParams.put("select1", "One Person");
        requestParams.put("token", token);

        String response =
                given()
                        .contentType(ContentType.JSON)
                        .body(requestParams)
                        .log().all()
                .when()
                        .post(EndPoints.CREATE_POST)
                .then()
                        .statusCode(200)
                        .log().all()
                        .extract().response().getBody().asString();

        Assert.assertEquals("Message ", "\"Congrats.\"", response);

        PostDTO[] actualPostsDTO = apiHelper.getAllPostsByUser();
        Assert.assertEquals("Number of posts ", 1, actualPostsDTO.length);

        PostDTO[] expectPostDTO = {
                PostDTO.builder()
                        .title(requestParams.get("title"))
                        .body(requestParams.get("body"))
                        .select1(requestParams.get("select1"))
                        .author(AuthorDTO.builder().username(ApiHelper.username).build())
                        .isVisitorOwner(false)
                        .build()
        };

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(actualPostsDTO[0])
                        .isEqualToIgnoringGivenFields(expectPostDTO[0], "_id", "createdDate", "author");
        softAssertions.assertThat(actualPostsDTO[0].getAuthor())
                        .isEqualToIgnoringGivenFields(expectPostDTO[0].getAuthor(), "avatar");

        softAssertions.assertAll();
    }
}
