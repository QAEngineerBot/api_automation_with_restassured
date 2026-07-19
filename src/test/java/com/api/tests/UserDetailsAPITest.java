package com.api.tests;

import static org.hamcrest.Matchers.equalTo;
import org.testng.annotations.Test;

import static com.api.constants.Roles.FD;
import static com.api.utils.AuthTokenProvider.getToken;
import static com.api.utils.ConfigManager.getProperty;

import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.module.jsv.JsonSchemaValidator;

public class UserDetailsAPITest {

    @Test
    public void userDetailsAPITest() {

        Header header = new Header("Authorization", getToken(FD));

        given()
                .baseUri(getProperty("BASE_URI"))
                .and()
                .accept(ContentType.JSON)
                .and()
                .header(header)
                .log().uri()
                .log().method()
                .when()
                .get("userdetails")
                .then()
                .log().body()
                .log().ifValidationFails()
                .statusCode(200)
                .and()
                .body("message", equalTo("Success"))
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/UserDetailsResponseSchema.json"));

    }
}
