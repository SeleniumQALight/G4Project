package apiTest;

import api.AuthorDTO;
import api.EndPoints;
import api.PostDTO;
import io.qameta.allure.restassured.AllureRestAssured;
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

public class ApiTest {
    final String USER_NAME = "autoapi";
    Logger logger = Logger.getLogger(getClass());

    @Test
    public void getAllPostsByUser() {
        PostDTO[] responseBody = given()
                .contentType(ContentType.JSON).filter(new AllureRestAssured()).log().all()
                .when()
                .get(EndPoints.POST_BY_USER, USER_NAME)
                .then()
                .statusCode(200).log().all()
                .extract().response().as(PostDTO[].class);
        logger.info("Number of posts: " + responseBody.length);
        logger.info("Title is " + responseBody[0].getTitle());
        logger.info("Username: " + responseBody[0].getAuthor().getUsername());

        for (int i = 0; i < responseBody.length; i++) {
            Assert.assertEquals("Username", USER_NAME, responseBody[i].getAuthor().getUsername());
        }

        PostDTO[] expectedPostDTO = {
//                new PostDTO("test", "test body", "All Users", new AuthorDTO("autoapi"), false),
//                new PostDTO("test2", "test body2", "All Users", new AuthorDTO("autoapi"), false)
                PostDTO.builder().title("test").body("test body").select1("All Users")
                        .author(AuthorDTO.builder().username("autoapi").build()).isVisitorOwner(false)
                        .build(),
                PostDTO.builder().title("test2").body("test body2").select1("All Users")
                        .author(AuthorDTO.builder().username("autoapi").build()).isVisitorOwner(false)
                        .build()
        };

        Assert.assertEquals(expectedPostDTO.length, responseBody.length);

        SoftAssertions softAssertions = new SoftAssertions();

        for (int i = 0; i < expectedPostDTO.length; i++) {
            softAssertions.assertThat(responseBody[i]).isEqualToIgnoringGivenFields(expectedPostDTO[i], "_id", "createdDate", "author");
            softAssertions.assertThat(responseBody[i].getAuthor()).isEqualToIgnoringGivenFields(expectedPostDTO[i].getAuthor(), "avatar");


        }

        softAssertions.assertAll();
    }

    @Test
    public void getAllPostsByUserNegative() {
        String actualResponse = given()
                .contentType(ContentType.JSON).log().all()
                .when().get(EndPoints.POST_BY_USER, "notValideUser")
                .then().statusCode(200).log().all()
                .extract().response().getBody().asString();
        Assert.assertEquals("Message in response", "\"Sorry, invalid user requested.undefined\"", actualResponse);
    }

    @Test
    public void getAllPostsByUserPath() {
        Response response = given()
                .contentType(ContentType.JSON).log().all()
                .when().get(EndPoints.POST_BY_USER, USER_NAME)
                .then().statusCode(200).log().all()
                .extract().response();
        List<String> actualTitleList = response.jsonPath().getList("title", String.class);
        List<Map> actualAuthorList = response.jsonPath().getList("author", Map.class);

        SoftAssertions softAssertions = new SoftAssertions();

        for (int i = 0; i < actualTitleList.size(); i++) {
            softAssertions.assertThat(actualTitleList.get(i)).as("Item number" + i).contains("test");
        }
        for (int i = 0; i < actualAuthorList.size(); i++) {
            softAssertions.assertThat(actualAuthorList.get(i).get("username")).as("Item number " + i).isEqualTo(USER_NAME);
        }

        softAssertions.assertAll();
    }

    @Test
    public void getAllPostsByUserSchema(){
        given()
                .contentType(ContentType.JSON).log().all()
                .when().get(EndPoints.POST_BY_USER, USER_NAME)
                .then().statusCode(200).log().all().assertThat().body(matchesJsonSchemaInClasspath("respons.json"));
    }
}
