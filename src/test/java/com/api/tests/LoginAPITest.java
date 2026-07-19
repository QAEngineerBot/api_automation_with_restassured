package com.api.tests;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;

import org.testng.annotations.Test;

import com.api.pojo.UserLoginCredentials;
import static com.api.utils.ConfigManager.*;

import io.restassured.http.ContentType;

public class LoginAPITest {

    @Test
    public void loginAPITest() throws IOException {

        UserLoginCredentials userCred = new UserLoginCredentials("iamfd", "password");

        given().baseUri(getProperty("BASE_URI"))
                .and().contentType(ContentType.JSON)
                .and()
                .accept(ContentType.JSON)
                .and().body(userCred)
                .and()
                .log().uri()
                .log().headers()
                .log().method()
                .log().body()
                .when().post("login")
                .then().log().body()
                .and()
                .log().ifValidationFails()
                .and()
                .statusCode(200)
                .and()
                .body("message", equalTo("Success"))
                .and()
                .body(matchesJsonSchemaInClasspath("response-schema/LoginResponseSchema.json"));
    }

}
