package com.example.moviebay;

import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class UserControllerTest {

    @Test
    public void getAllUserMovies_whenCorrectRequest_thenStatusCode200() {

        baseURI = "http://localhost:8080";

        given()
                .get("/2/movies")
                .then()
                .statusCode(200);
    }

    @Test
    public void getAllUserMovies_whenUserIdPathVariableIsNotInDb_thenStatusCode500() {

        baseURI = "http://localhost:8080";

        given()
                .get("/10000/movies")
                .then()
                .statusCode(500);
    }

    @Test
    public void getAllUserMovies_whenUserIdPathVariableTypeIsNotCorrect_thenStatusCode400() {

        baseURI = "http://localhost:8080";

        given()
                .get("/anyString/movies")
                .then()
                .statusCode(400);
    }

    @Test
    public void getAllUserMovies_whenCorrectJsonBody_thenCorrect() {

        baseURI = "http://localhost:8080";

        given()
                .get("/2/movies")
                .then()
                .body("movieId", hasItem(2))
                .body("tmdbId", hasItem(663174))
                .body("title", hasItem("V1 Murder Case"))
                .body("overview", hasItem("An intelligent forensic officer starts investigating a murder, " +
                        "but he faces a slew of challenges as he suffers from nyctophobia. " +
                        "Will he be able to solve the case?"))
                .body("release_date", hasItem("2019-12-27"));
    }

    @Test
    public void getAllUserMovies_validateResponseTimeIsAcceptable_thenCorrect() {

        baseURI = "http://localhost:8080";

        given()
                .get("/2/movies")
                .then()
                .time(lessThan(5000L));
    }

    @Test
    public void addMovieFromTmdbAPI_whenCorrectRequest_thenStatusCode200() {

        baseURI = "http://localhost:8080";

        given()
                .post("/4/add-movie/550")
                .then()
                .statusCode(200);
    }

    @Test
    public void addMovieFromTmdbAPI_whenUserIdPathVariableIsNotInDb_thenStatusCode500() {

        baseURI = "http://localhost:8080";

        given()
                .post("/10000/add-movie/550")
                .then()
                .statusCode(500);
    }


    @Test
    public void addMovieFromTmdbAPI_whenUserIdPathVariableTypeIsNotCorrect_thenStatusCode400() {

        baseURI = "http://localhost:8080";

        given()
                .post("/anyString/add-movie/550")
                .then()
                .statusCode(400);
    }

    @Test
    public void addMovieFromTmdbAPI_whenMovieIdNotExistsInTmdb_thenStatusCode500() {

        baseURI = "http://localhost:8080";

        given()
                .post("/4/add-movie/10000000")
                .then()
                .statusCode(500);
    }

    @Test
    public void addMovieFromTmdbAPI_whenMovieIdPathVariableTypeIsNotCorrect_thenStatusCode400() {

        baseURI = "http://localhost:8080";

        given()
                .post("/4/add-movie/anyString")
                .then()
                .statusCode(400);
    }

    @Test
    public void addMovieFromTmdbAPI_validateResponseTimeIsAcceptable_thenCorrect() {

        baseURI = "http://localhost:8080";

        given()
                .post("/4/add-movie/550")
                .then()
                .time(lessThan(5000L));
    }

    @Test
    public void deleteMovieFromUser_whenCorrectRequest_thenStatusCode200() {

        baseURI = "http://localhost:8080";

        given()
                .delete("/4/delete-movie/550")
                .then()
                .statusCode(200);
    }

    @Test
    public void deleteMovieFromUser_whenUserIdPathVariableTypeIsNotCorrect_thenStatusCode400() {

        baseURI = "http://localhost:8080";

        given()
                .delete("/anyString/delete-movie/550")
                .then()
                .statusCode(400);
    }

    @Test
    public void deleteMovieFromUser_whenUserIdPathVariableIsNotInDb_thenStatusCode500() {

        baseURI = "http://localhost:8080";

        given()
                .delete("/10000/delete-movie/550")
                .then()
                .statusCode(500);
    }

    @Test
    public void deleteMovieFromUser_whenMovieIdPathVariableTypeIsNotCorrect_thenStatusCode400() {

        baseURI = "http://localhost:8080";

        given()
                .delete("/4/delete-movie/anyString")
                .then()
                .statusCode(400);
    }

    @Test
    public void deleteMovieFromUser_validateResponseTimeIsAcceptable_thenCorrect() {

        baseURI = "http://localhost:8080";

        given()
                .post("/4/add-movie/550")
                .then()
                .time(lessThan(5000L));
    }
}
