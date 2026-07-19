package com.api.tests;

import static org.hamcrest.Matchers.blankOrNullString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import org.testng.annotations.Test;

import com.api.constants.Roles;
import static com.api.utils.AuthTokenProvider.getToken;
import com.api.utils.ConfigManager;
import static com.api.utils.ConfigManager.getProperty;

import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;

public class CountAPITest {

    @Test
    public void verifyCountApi() {
        given()
                .baseUri(ConfigManager.getProperty("BASE_URI")).and().accept(ContentType.JSON)
                .and().headers("Authorization", getToken(Roles.FD))
                .and().log().uri().log().headers().log().method()
                .when()
                .get("dashboard/count")
                .then()
                .log().body()
                .log().ifValidationFails()
                .statusCode(200)
                .and()
                .time(lessThan(1500L))
                .body("message", equalTo("Success"))
                .body("data", notNullValue())
                .body("data.size()", equalTo(3))
                .body("data.count", everyItem(greaterThanOrEqualTo(0)))
                .body("data.label", everyItem(not(blankOrNullString())))
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/CountResponseSchema.json"));
    }

    @Test
    public void verifyCountApiWithMissingAuth() {
        given()
                .baseUri(getProperty("BASE_URI"))
                .and()
                .accept(ContentType.JSON)
                .when()
                .get("dashboard/count")
                .then()
                .log().body()
                .and()
                .statusCode(401);
    }
}
