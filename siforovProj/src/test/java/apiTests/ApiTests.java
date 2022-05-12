package apiTests;

import api.Endpoints;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

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
//                new PostDTO("test", "test", "All Users", new AuthorDTO("romansiforov"), false)
                PostDTO.builder().title("test").body("test").select1("All Users")
                        .author(AuthorDTO.builder().username("romansiforov").build())
                        .isVisitorOwner(false)
                        .build()
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

    @Test
    public void getAllPostsByUserNegative() {
        String actualResponse =
                given()
                        .log().all()
                .when()
                        .get(Endpoints.POST_BY_USER, "not valid user")
                .then()
                        .statusCode(200)
                        .extract().response().getBody().asString();
        Assert.assertEquals("Message in response", "Sorry, invalid user requested.undefined",
                actualResponse.replace("\"", ""));
    }

    @Test
    public void getAllPostsByUserJPath() {
        Response response =
                given()
                        .contentType(ContentType.JSON)
                        .log().all().
                when()
                        .get(Endpoints.POST_BY_USER, USER_NAME).
                then()
                        .statusCode(200)
                        .log().all()
                        .extract().response();
        List<String> actualTitleList = response.jsonPath().getList("title", String.class);
        List<Map> actualAuthorList = response.jsonPath().getList("author", Map.class);

        SoftAssertions softAssertion = new SoftAssertions();

        for (int i = 0; i < actualTitleList.size(); i++) {
            softAssertion
                    .assertThat(actualTitleList.get(i))
                    .as("Item " + i)
                    .contains("test");
        }

        for (int i = 0; i < actualAuthorList.size(); i++) {
            softAssertion
                    .assertThat(actualAuthorList.get(i).get("username"))
                    .as("Item" + i)
                    .isEqualTo(USER_NAME);
        }
        softAssertion.assertAll();

    }

    @Test
    public void getAAllPostsByUserSchema(){
        given()
                .contentType(ContentType.JSON)
                .log().all().
        when()
                .get(Endpoints.POST_BY_USER, USER_NAME).
        then()
                .statusCode(200)
                .log().all()
                .assertThat().body(matchesJsonSchemaInClasspath("respons.json"));
    }
}
