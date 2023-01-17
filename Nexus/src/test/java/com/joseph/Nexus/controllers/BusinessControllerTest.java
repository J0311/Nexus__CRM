package com.joseph.Nexus.controllers;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.assertj.core.api.BDDAssertions.then;

public class BusinessControllerTest {

    // Utilizing rest assured api to verify test method end point
    @Test
    void testBusinessTestControllerEndpoint (){
        String test = "Business Test in progress";
        given().contentType("text").body(test)
                .when().get("http://localhost:8080/api/v1/business")
        .then()
                //.statusCode(200)
               // .body("Business Test in progress", equals(test))
                .log().all();
    }

}
