package apiTests;

import api.Endpoints;
import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class ApiTests {

    final String USER_NAME = "romansiforov";
    Logger logger = Logger.getLogger(getClass());

    @Test
    public void getAllPostsByUser() {
        PostDTO[] responseBody =
                given()
                        .contentType(ContentType.JSON)
                        .accept(ContentType.JSON)
                        .log().all()
                        .when()
                        .get(Endpoints.POST_BY_USER, USER_NAME)
                        .then()
                        .statusCode(200)
                        .log().all()
                        .extract()
                        .response()
                        .as(PostDTO[].class);

        logger.info("Number of messages is " + responseBody.length);
        logger.info("Post name is " + responseBody[0].title);
        logger.info("Username " + responseBody[0].author.username);

        for (int i = 0; i < responseBody.length; i++) {
            Assert.assertEquals("Username ", USER_NAME, responseBody[i].getAuthor().getUsername());
        }

        PostDTO[] expectedPostDTO = {
                new PostDTO("test", "test", "All Users", new AuthorDTO("romansiforov"), false)
        };

        Assert.assertEquals(expectedPostDTO.length, responseBody.length);

        SoftAssertions softAssertion = new SoftAssertions();

        for (int i = 0; i < expectedPostDTO.length; i++) {
            softAssertion.assertThat(responseBody[i])
                    .isEqualToIgnoringGivenFields(expectedPostDTO[i], "_id", "createdDate", "author");

            softAssertion.assertThat(responseBody[i].getAuthor())
                    .isEqualToIgnoringGivenFields(expectedPostDTO[i].getAuthor(), "avatar");
        }
        softAssertion.assertAll();
    }
}
