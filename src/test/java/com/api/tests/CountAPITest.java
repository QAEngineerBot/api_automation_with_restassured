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
import static com.api.utils.SpecUtil.requestSpec;
import static com.api.utils.SpecUtil.requestSpecWithAuth;
import static com.api.utils.SpecUtil.successResponseSpec;
import static com.api.utils.SpecUtil.unAuthorizedResponseSpec;

import static io.restassured.RestAssured.given;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.restassured.module.jsv.JsonSchemaValidator;

@Epic("Job Management")
@Feature("Count API")
public class CountAPITest {

    @Story("Verify Count API with valid token")
    @Description("This test verifies the Count API with a valid token and checks the response against the expected schema.")
    @Severity(SeverityLevel.NORMAL)
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

    @Story("Verify Count API without token")
    @Description("This test verifies the Count API without token and checks the response code against the expected")
    @Severity(SeverityLevel.NORMAL)
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
