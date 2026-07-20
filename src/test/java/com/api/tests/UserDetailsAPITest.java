package com.api.tests;

import static org.hamcrest.Matchers.equalTo;
import org.testng.annotations.Test;

import static com.api.constants.Roles.FD;
import static com.api.utils.SpecUtils.requestSpecWithAuth;
import static com.api.utils.SpecUtils.successResponseSpec;

import static io.restassured.RestAssured.given;
import io.restassured.module.jsv.JsonSchemaValidator;

public class UserDetailsAPITest {

    @Test
    public void userDetailsAPITest() {

        given()
                .spec(requestSpecWithAuth(FD))
                .when()
                .get("userdetails")
                .then()
                .spec(successResponseSpec())
                .and()
                .body("message", equalTo("Success"))
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/UserDetailsResponseSchema.json"));

    }
}
