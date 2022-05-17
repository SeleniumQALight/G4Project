package apiTests;

import api.ApiHelper;
import api.AuthorDTO;
import api.Endpoints;
import api.PostDTO;
import io.restassured.http.ContentType;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class createPostByApiTest {

    ApiHelper apiHelper = new ApiHelper();

    @Before
    public void deleteAllPosts(){
        apiHelper.deletePostsTillPresent();
    }

    @Test
    public void createPostByApiTest() {
            String token = apiHelper.getToken();

        HashMap<String, String> requestParams = new HashMap<>();
        requestParams.put("title", "New Post Api");
        requestParams.put("body", "Some test body for the post");
        requestParams.put("select1", "One Person");
        requestParams.put("token", token);

        String response =
                given()
                        .contentType(ContentType.JSON)
                        .body(requestParams)
                        .log().all()
                .when()
                        .post(Endpoints.CREATE_POST)
                .then()
                        .statusCode(200)
                        .log().all()
                        .extract()
                        .response().getBody().asString();
        Assert.assertEquals("Message", "\"Congrats.\"", response);

        PostDTO[] actualPostsDTO = apiHelper.getAllPostsByUser();
        Assert.assertEquals("Number of posts", 1, actualPostsDTO.length);

        PostDTO[] expectedPostDTO = {
                PostDTO.builder()
                        .title(requestParams.get("title"))
                        .body(requestParams.get("body"))
                        .select1(requestParams.get("select1"))
                        .author(AuthorDTO.builder().username(ApiHelper.USER_NAME).build())
                        .isVisitorOwner(false)
                        .build()
        };

        SoftAssertions softAssertions = new SoftAssertions();
            softAssertions
                    .assertThat(actualPostsDTO[0])
                    .isEqualToIgnoringGivenFields(expectedPostDTO[0], "_id", "createdDate", "author");
        softAssertions
                .assertThat(actualPostsDTO[0].getAuthor())
                .isEqualToIgnoringGivenFields(expectedPostDTO[0].getAuthor(), "avatar");
        softAssertions.assertAll();

    }
}
