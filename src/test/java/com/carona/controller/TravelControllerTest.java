package com.carona.controller;

import com.carona.CaronaApplicationTests;
import com.carona.dto.*;
import com.carona.entity.*;
import com.carona.service.*;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;
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

    @Autowired
    private TravelService service;

    private Optional<Driver> driver;

    private Optional<Passanger> passanger;

    private Optional<Travel> travel;

    @BeforeEach
    void init(){
        Optional<User> user1 = userService.save(new UserDTO("maicon" , "maicon@teste.com"));
        Optional<User> user2 = userService.save(new UserDTO("caio", "caio@teste.com"));

        this.passanger = passangerService.save(new PassangerDTO(user1.get().getId()));
        List<Long> passangersId = new ArrayList<>();

        this.driver = driverService.save(new DriverDTO(user2.get().getId()));

        this.travel = service.save(new TravelDTO(10L , 3 , driver.get().getId() , passangersId));

    }

    @Test
    public void givenTravelWhenToSaveThenReturnCreated() {
        String body = "{\n" +
                "\t\"value\": 7,\n" +
                "\t\"maxPassangers\": 2,\n" +
                "\t\"driverId\": "+ this.driver.get().getId() +",\n" +
                "\t\"passangers\": [\n" +
                "\t\t"+this.passanger.get().getId()+"\n" +
                "\t]\n" +
                "\n" +
                "}";

        Integer statusCode = RestAssured.given().body(body)
                .when()
                .contentType(ContentType.JSON)
                .post(recurso)
                .then().extract().statusCode();

        assertThat(statusCode).isEqualTo(HttpStatus.CREATED.value());
    }

    @Test
    public void giveTravelWhenToSaveThenReturnTravel(){
        Long driverId = this.driver.get().getId();

        String body = "{\n" +
                "\t\"value\": 7,\n" +
                "\t\"maxPassangers\": 2,\n" +
                "\t\"driverId\": "+ driverId +",\n" +
                "\t\"passangers\": [\n" +
                "\t\t"+this.passanger.get().getId()+"\n" +
                "\t]\n" +
                "\n" +
                "}";

        Integer IdRequest = RestAssured.given().body(body)
                .when()
                .contentType(ContentType.JSON)
                .post(recurso)
                .then()
                .extract()
                .path("driver.id");

        assertThat(IdRequest.longValue()).isEqualTo(driverId);
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

    @Test
    public void givenDriverIdWhenClosedThenReturnOk(){
        recurso = recurso + "/closed/1";

        Integer statusCode = RestAssured.given()
                .when()
                .contentType(ContentType.JSON)
                .get(recurso)
                .then()
                .extract().statusCode();

        assertThat(statusCode).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    public void givenDriverIdWhenClosedThenReturnTravel(){
        Long idRequest = this.travel.get().getId();

        recurso = recurso + "/closed/"+idRequest;

        Integer driverid = RestAssured.given()
                .when()
                .contentType(ContentType.JSON)
                .get(recurso)
                .then()
                .extract()
                .path("driver.id");

        assertThat(idRequest).isEqualTo(driverid.longValue());
    }
}
