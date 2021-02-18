package com.carona.controller;

import com.carona.CaronaApplicationTests;
import com.carona.dto.DriverDTO;
import com.carona.dto.PassangerDTO;
import com.carona.dto.UserDTO;
import com.carona.entity.Driver;
import com.carona.entity.Passanger;
import com.carona.entity.User;
import com.carona.service.DriverService;
import com.carona.service.PassangerService;
import com.carona.service.UserService;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class TravelControllerTest extends CaronaApplicationTests {

    private String recurso = "/travels";

    @Autowired
    private PassangerService passangerService;

    @Autowired
    private DriverService driverService;

    @Autowired
    private UserService userService;


    @Test
    public void givenTravelWhenToSaveThenReturnCreated() {
        Optional<User> user1 = userService.save(new UserDTO("maicon" , "maicon@teste.com"));
        Optional<User> user2 = userService.save(new UserDTO("caio", "caio@teste.com"));

        Optional<Passanger> passanger = passangerService.save(new PassangerDTO(user1.get().getId()));
        Optional<Driver> driver = driverService.save(new DriverDTO(user2.get().getId()));

        String body = "{\n" +
                "\t\"value\": 7,\n" +
                "\t\"open:\": false,\n" +
                "\t\"maxPassangers\": 2,\n" +
                "\t\"driverId\": "+ driver.get().getId() +",\n" +
                "\t\"passangers\": [\n" +
                "\t\t"+passanger.get().getId()+"\n" +
                "\t]\n" +
                "\n" +
                "}";

        Integer statusCode = RestAssured.given().body(body)
                .when()
                .contentType(ContentType.JSON)
                .post(recurso)
                .then().extract().statusCode();

        assertThat(statusCode).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    public void giveTravelWhenToSaveThenReturnTravel(){
        Optional<User> user1 = userService.save(new UserDTO("sara" , "sara@teste.com"));
        Optional<User> user2 = userService.save(new UserDTO("lara", "lara@teste.com"));

        Optional<Passanger> passanger = passangerService.save(new PassangerDTO(user1.get().getId()));
        Optional<Driver> driver = driverService.save(new DriverDTO(user2.get().getId()));

        String body = "{\n" +
                "\t\"value\": 7,\n" +
                "\t\"open:\": false,\n" +
                "\t\"maxPassangers\": 2,\n" +
                "\t\"driverId\": "+ driver.get().getId() +",\n" +
                "\t\"passangers\": [\n" +
                "\t\t"+passanger.get().getId()+"\n" +
                "\t]\n" +
                "\n" +
                "}";

        Long driveIdRequest = RestAssured.given().body(body)
                .when()
                .contentType(ContentType.JSON)
                .post(recurso)
                .then()
                .extract()
                .path("driver.id");

        assertThat(driveIdRequest).isEqualTo(driver.get().getId());
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

    @Test
    public void givenEmptyDriverWhenToSaveThenReturnBadRequest() {
        String body = "{\n" +
                "\t\"value\": 7,\n" +
                "\t\"open:\": false,\n" +
                "\t\"maxPassangers\": 2,\n" +
                "\t\"passangers\": [\n" +
                "\t\t1\n" +
                "\t]\n" +
                "\n" +
                "}";

        Integer statusCode = RestAssured.given().body(body)
                .when()
                .contentType(ContentType.JSON)
                .post(recurso)
                .then()
                .extract().statusCode();

        assertThat(statusCode).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void givenEmptyValueWhenToSaveThenReturnBadRequest() {
        String body = "{\n" +
                "\t\"open:\": false,\n" +
                "\t\"maxPassangers\": 2,\n" +
                "\t\"driverId\": 1,\n" +
                "\t\"passangers\": [\n" +
                "\t\t1\n" +
                "\t]\n" +
                "\n" +
                "}";

        Integer statusCode = RestAssured.given().body(body)
                .when()
                .contentType(ContentType.JSON)
                .post(recurso)
                .then()
                .extract().statusCode();

        assertThat(statusCode).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

}
