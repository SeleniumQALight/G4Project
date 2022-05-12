package apiTests;

import api.AuthorDTO;
import api.EndPoints;
import api.PostDTO;
import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class CreatePostByAPI {


Logger loger = Logger.getLogger(getClass());
    APIHelper apiHelper  = new APIHelper();
@Before
public void deleteAllPosts(){
    apiHelper.deletePostsTillPresent();

}

    @Test
    public void createPostByAPI(){
        String token  = apiHelper.getToken();
       // loger.info(token);

        HashMap<String,String> requestParams = new HashMap<>();
        requestParams.put("title", "New post from API");
        requestParams.put("body", "post body");
        requestParams.put("select1","One Person");
        requestParams.put("token",token);

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

        Assert.assertEquals("Message ", "\"Congrats.\"", response );

        //
        PostDTO[] actualPostsDTO = apiHelper.getAllPostsByUser();

        Assert.assertEquals("number of posts ",1,actualPostsDTO.length);

        PostDTO[] expectedPostDTO =  {
                PostDTO.builder()
                        .title(requestParams.get("title"))
                        .body(requestParams.get("body"))
                        .select1(requestParams.get("select1"))
                        .author(AuthorDTO.builder()
                                .username(APIHelper.userName)
                                .build())
                        .isVisitorOwner(false)
                        .build()
        };
        SoftAssertions  softAssertions = new SoftAssertions();
        softAssertions.assertThat(actualPostsDTO[0])
                        .isEqualToIgnoringGivenFields(expectedPostDTO[0],"_id",
                                "createdDate","author");
        softAssertions.assertThat(actualPostsDTO[0].getAuthor())
                        .isEqualToIgnoringGivenFields(expectedPostDTO[0].getAuthor(),
                                "avatar");

        softAssertions.assertAll();

    }
}
