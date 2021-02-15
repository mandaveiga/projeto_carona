package com.carona.controller;

import com.carona.CaronaApplicationTests;
import com.carona.entity.User;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.ResponseBodyExtractionOptions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.Assertions.assertThat;

public class UserControllerTest extends CaronaApplicationTests {

    private String recurso = "/users";

    @Test
    public void giveUserWhenToSaveThenReturnCreated() {
        String body = "{\n" +
                "    \"name\": \"any\",\n" +
                "    \"email\": \"any@teste.com\"\n" +
                "}";

        Integer statusCode = RestAssured.given().body(body)
                .when()
                .contentType(ContentType.JSON)
                .post(recurso)
                .then().extract().statusCode();

        assertThat(statusCode).isEqualTo(HttpStatus.CREATED.value());
    }

    @Test
    public void whenToSaveThenReturnUser(){
        String body = "{\n" +
                "    \"name\": \"jose\",\n" +
                "    \"email\": \"jose@gmail.com\"\n" +
                "}";

        String emailResponse = RestAssured.given().body(body)
                .when()
                .contentType(ContentType.JSON)
                .post(recurso)
                .then()
                .extract()
                .path("email").toString();

        assertThat(emailResponse).isEqualTo("jose@gmail.com");
    }

    @Test
    public void givenEmptyBodyWhenToSaveThenReturnBadRequest() {
        String body = "{}";

        Integer statusCode = RestAssured.given().body(body)
                .when()
                .contentType(ContentType.JSON)
                .post(recurso)
                .then().extract().statusCode();

        assertThat(statusCode).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void giveNameNullWhenToSaveThenReturnBadRequest() {
        String body = "{\n" +
                "    \"name\": null,\n" +
                "    \"email\": \"jose@gmail.com\"\n" +
                "}";

        Integer statusCode = RestAssured.given().body(body)
                .when()
                .contentType(ContentType.JSON)
                .post(recurso)
                .then().extract().statusCode();

        assertThat(statusCode).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void givenEmailNullWhenToSaveThenReturnBadRequest() {
        String body = "{\n" +
                "    \"name\": \"jose\",\n" +
                "    \"email\": null\n" +
                "}";

        Integer statusCode = RestAssured.given().body(body)
                .when()
                .contentType(ContentType.JSON)
                .post(recurso)
                .then().extract().statusCode();

        assertThat(statusCode).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

}