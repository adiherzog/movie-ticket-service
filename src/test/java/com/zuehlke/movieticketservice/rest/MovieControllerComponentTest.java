package com.zuehlke.movieticketservice.rest;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MovieControllerComponentTest {

    @LocalServerPort
    private int port;

    @Before
    public void setUp() throws Exception {
        RestAssured.port = port;
    }

    @Test
    public void getMovies() throws Exception {
        // @formatter:off
        RestAssured
            .when()
                .get("/api/v1/movies")
            .then()
                .statusCode(200)
                .body("[0].id", equalTo(1))
                .body("[0].title", equalTo("Batman Begins"))
                .body("[0].poster", equalTo("https://images-na.ssl-images-amazon.com/images/M/MV5BNTM3OTc0MzM2OV5BMl5BanBnXkFtZTYwNzUwMTI3._V1_SX300.jpg"))
                .body("$.size", equalTo(3));
        // @formatter:on
    }

    @Test
    public void getMovieById() throws Exception {
        // @formatter:off
        String body = RestAssured
            .when()
                .get("/api/v1/movies/3")
            .then()
                .statusCode(200)
                .extract().body().asString();

        assertThat(body, equalTo(readFile("getMovieExpectedResponse.json")));
        // @formatter:on
    }

    private String readFile(String fileName) throws FileNotFoundException {
        String file = this.getClass().getResource(fileName).getFile();
        return new Scanner(new File(file)).useDelimiter("\\Z").next();
    }

}