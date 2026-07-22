package com.api.tests;

import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static com.api.constants.Roles.FD;
import static com.api.utils.SpecUtil.requestSpecWithAuth;
import static com.api.utils.SpecUtil.successResponseSpec;

import static io.restassured.RestAssured.given;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.restassured.module.jsv.JsonSchemaValidator;
@Epic("User Management")
@Feature("User Details API")
@Listeners(com.listeners.ApiTestListener.class)
public class UserDetailsAPITest {

    @Story("Verify User Details API with valid token")
    @Description("This test verifies the user details API with a valid token and checks the response against the expected schema.")
    @Severity(SeverityLevel.CRITICAL)
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
