package com.api.tests;

import static org.hamcrest.Matchers.blankOrNullString;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import org.testng.annotations.Test;

import com.api.constants.Roles;
import static com.api.utils.SpecUtils.requestSpec;
import static com.api.utils.SpecUtils.requestSpecWithAuth;
import static com.api.utils.SpecUtils.successResponseSpec;
import static com.api.utils.SpecUtils.unAuthorizedResponseSpec;

import static io.restassured.RestAssured.given;
import io.restassured.module.jsv.JsonSchemaValidator;

public class CountAPITest {

    @Test
    public void verifyCountApi() {
        given()
                .spec(requestSpecWithAuth(Roles.FD))
                .when()
                .get("dashboard/count")
                .then()
                .spec(successResponseSpec())
                .log().ifValidationFails()
                .body("message", equalTo("Success"))
                .body("data", notNullValue())
                .body("data.size()", equalTo(3))
                .body("data.count", everyItem(greaterThanOrEqualTo(0)))
                .body("data.label", everyItem(not(blankOrNullString())))
                .body("data.key", containsInAnyOrder("pending_for_delivery", "created_today", "pending_fst_assignment"))
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/CountResponseSchema.json"));
    }

    @Test
    public void verifyCountApiWithMissingAuth() {
        given()
                .spec(requestSpec())
                .when()
                .get("dashboard/count")
                .then()
                .spec(unAuthorizedResponseSpec());
    }
}
