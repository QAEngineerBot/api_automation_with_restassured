package com.api.tests;

import static org.hamcrest.Matchers.equalTo;
import org.testng.annotations.Test;

import com.api.pojo.UserLoginCredentials;
import static com.api.utils.ConfigManager.getProperty;
import static com.api.utils.SpecUtils.*;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class LoginAPITest {

    @Test
    public void loginAPITest() {

        UserLoginCredentials userCred = new UserLoginCredentials("iamfd", "password");

        given().baseUri(getProperty("BASE_URI"))
                .spec(requestSpec(userCred))
                .when().post("login")
                .then()
                .spec(successResponseSpec())
                .log().ifValidationFails()
                .and()
                .body("message", equalTo("Success"))
                .and()
                .body(matchesJsonSchemaInClasspath("response-schema/LoginResponseSchema.json"));
    }

}
