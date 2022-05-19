package apiTests;

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

public class ApiTests {
    final String USER_NAME = "autoapi";
    Logger logger = Logger.getLogger(getClass());
    @Test
    public void getAllPostsByUser() {
        PostDTO[] responseBody = given()
                .contentType(ContentType.JSON)
                .filter(new AllureRestAssured())
                .log().all()
                .when()
                .get(EndPoints.POST_BY_USER, USER_NAME)
                .then()
                .statusCode(200)
                .log().all()
                .extract()
                .response().as(PostDTO[].class); //тип даннbх

        logger.info("Number of posts " + responseBody.length);
        logger.info("Post title " + responseBody[0].getTitle());
        logger.info("Username "+ responseBody[0].getAuthor().getUsername());

        for (int i = 0; i <responseBody.length ; i++) {
            Assert.assertEquals("Username ", USER_NAME, responseBody[i].getAuthor().getUsername());
        }

        PostDTO[] expectedPostDTO = {
             //   new PostDTO("test","post body", "One Person", new AuthorDTO("autoapi"), false),
             //   new PostDTO("test2","post body2", "One Person", new AuthorDTO("autoapi"), false)

                PostDTO.builder().title("test").body("post body").select1("One Person")
                        .author(AuthorDTO.builder().username("autoapi").build()).isVisitorOwner(false)
                        .build(),
                PostDTO.builder().title("test2").body("post body2").select1("One Person")
                        .author(AuthorDTO.builder().username("autoapi").build()).isVisitorOwner(false)
                        .build()
        };

        Assert.assertEquals(expectedPostDTO.length, responseBody.length);

        SoftAssertions softAssertions = new SoftAssertions();
        for (int i = 0; i < expectedPostDTO.length; i++) {
            softAssertions.assertThat(responseBody[i])
                    .isEqualToIgnoringGivenFields(expectedPostDTO[i],"_id","createdDate","author");
            softAssertions.assertThat(responseBody[i].getAuthor())
                    .isEqualToIgnoringGivenFields(expectedPostDTO[i].getAuthor(),"avatar");
            
        }

        softAssertions.assertAll();
    }
    @Test
    public void getAllByUserNegative(){
        String actualResponce =
                given()
                        .contentType(ContentType.JSON)
                        .log().all()
                        .when()
                        .get(EndPoints.POST_BY_USER,"notValidUser")
                        .then()
                        .statusCode(200)
                        .log().all()
                        .extract().response().getBody().asString();

        Assert.assertEquals("Message in responce",
                "\"Sorry, invalid user requested.undefined\"",actualResponce );
        Assert.assertEquals("Message in responce",
                "Sorry, invalid user requested.undefined",
                actualResponce.replace("\"", "") );
    }

    @Test
    public void getAllPostsByUserPath(){
        Response responce  =
                given()
                  .contentType(ContentType.JSON)
                .log().all()
                .when()
                .get(EndPoints.POST_BY_USER, USER_NAME)
                .then()
                .statusCode(200)
                .log().all()
                        .extract().response();

        List<String> actualTitleList = responce.jsonPath().getList("title",String.class);
        List<Map> acualAuthorList = responce.jsonPath().getList("author",Map.class);

        SoftAssertions softAssertions = new SoftAssertions();

        for (int i = 0; i < actualTitleList.size(); i++) {
            softAssertions.assertThat(actualTitleList.get(i)).as("Item number " + i)
                    .contains("test");
        }

        for (int i = 0; i < acualAuthorList.size(); i++) {
            softAssertions.assertThat(acualAuthorList.get(i).get("username"))
                    .as("Item number "+ i).isEqualTo(USER_NAME);

        }


        softAssertions.assertAll();

    }

    @Test
    public void getAllPostsByUsersSchema(){
        given()
                .contentType(ContentType.JSON)
                .log().all()
                .when()
                .get(EndPoints.POST_BY_USER, USER_NAME)
                .then()
                .statusCode(200)
                .log().all()
                .assertThat().body(matchesJsonSchemaInClasspath("respons.json"));

    }


}
