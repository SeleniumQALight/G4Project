package apiTests;

import api.AuthorDTO;
import api.EndPoints;
import api.PostDTO;
import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class ApiTests {
    final String USER_NAME = "autoapi";
    Logger logger = Logger.getLogger(getClass());

    @Test
    public void getAllPostsByUser() {
       PostDTO[] responseBody = given()
                .contentType(ContentType.JSON)
                .log().all()
                .when()
                .get(EndPoints.POST_BY_USER, USER_NAME)
                .then()
                .statusCode(200)
                .log().all()
                .extract()
                .response().as(PostDTO[].class);
       logger.info("Number of posts " + responseBody.length);
       logger.info("Post title " + responseBody[0].getTitle());
       logger.info("Username " + responseBody[0].getAuthor().getUsername());

        for (int i = 0; i < responseBody.length; i++) {
            Assert.assertEquals("Username ", USER_NAME, responseBody[i].getAuthor().getUsername());
        }

        PostDTO[] expectedPostDTO = {
                new PostDTO("test", "test body", "All Users", new AuthorDTO("autoapi"), false),
                new PostDTO("test2", "test body2", "All Users", new AuthorDTO("autoapi"), false)
        };

        Assert.assertEquals(expectedPostDTO.length, responseBody.length);

        SoftAssertions softAssertions = new SoftAssertions();

        for (int i = 0; i < expectedPostDTO.length; i++) {
            softAssertions.assertThat(responseBody[i])
                    .isEqualToIgnoringGivenFields(expectedPostDTO[i], "_id", "createdDate", "author");
            softAssertions.assertThat(responseBody[i].getAuthor())
                    .isEqualToIgnoringGivenFields(expectedPostDTO[i].getAuthor(), "avatar");

        }

        softAssertions.assertAll();
    }
}
