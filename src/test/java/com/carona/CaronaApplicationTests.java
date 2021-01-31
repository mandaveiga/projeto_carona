package com.carona;

import com.jayway.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static com.jayway.restassured.RestAssured.reset;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CaronaApplicationTests {

    @LocalServerPort
    protected int port = 0;

    @BeforeEach
    public void before() {
        reset();

        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;
    }
}
