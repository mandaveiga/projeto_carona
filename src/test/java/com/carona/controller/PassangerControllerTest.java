package com.carona.controller;

import com.carona.CaronaApplicationTests;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.Assertions.assertThat;

public class PassangerControllerTest extends CaronaApplicationTests {

    private String recurso = "/passangers";

    @Test
    public void givePassangerWhenToSaveThenReturnCreated(){
        String body = "{\n" +
                "    \"userId\": 1 \n" +
                "}";

        Integer statusCode = RestAssured.given().body(body)
                .when()
                .contentType(ContentType.JSON)
                .post(recurso)
                .then().extract().statusCode();

        assertThat(statusCode).isEqualTo(HttpStatus.CREATED.value());
    }

    @Test
    public void givePassangerWhenToSaveThenReturnPassanger(){
        int idUserRequest = 1;

        String body = "{\n" +
                "    \"userId\": "+ idUserRequest + " \n" +
                "}";

        Integer idUser = RestAssured.given().body(body)
                .when()
                .contentType(ContentType.JSON)
                .post(recurso)
                .then()
                .extract()
                .path("user.id");

        assertThat(idUser).isEqualTo(idUserRequest);
    }

    @Test
    public void givenEmptyBodyWhenToSaveThenReturnBadRequest() {
        String body = "{}";

        Integer statusCode = RestAssured.given().body(body)
                .when()
                .contentType(ContentType.JSON)
                .post(recurso)
                .then()
                .extract().statusCode();

        assertThat(statusCode).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }
}

