package com.carona.controller;


import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.Assertions.assertThat;

public class UserControllerTest extends CaronaApplicationTests {

    @Test
    public void shouldReturnCreated() {
        String body = "{\n" +
                "    \"name\": \"xpto\",\n" +
                "    \"email\": \"jose@gmail.com\"\n" +
                "}";

        Integer statusCode = RestAssured.given().body(body)
                .when()
                .contentType(ContentType.JSON)
                .post("/users")
                .then().extract().statusCode();

        assertThat(statusCode).isEqualTo(HttpStatus.CREATED.value());
    }

    @Test
    public void shouldReturnBadRequest() {
        String body = "{}";

        Integer statusCode = RestAssured.given().body(body)
                .when()
                .contentType(ContentType.JSON)
                .post("/users")
                .then().extract().statusCode();

        assertThat(statusCode).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void givenNameNullThenShouldReturnBadRequest() {
        String body = "{\n" +
                "    \"name\": null,\n" +
                "    \"email\": \"jose@gmail.com\"\n" +
                "}";

        Integer statusCode = RestAssured.given().body(body)
                .when()
                .contentType(ContentType.JSON)
                .post("/users")
                .then().extract().statusCode();

        assertThat(statusCode).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void givenEmailNullThenShouldReturnBadRequest() {
        String body = "{\n" +
                "    \"name\": \"xpto\",\n" +
                "    \"email\": null\n" +
                "}";

        Integer statusCode = RestAssured.given().body(body)
                .when()
                .contentType(ContentType.JSON)
                .post("/users")
                .then().extract().statusCode();

        assertThat(statusCode).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

}