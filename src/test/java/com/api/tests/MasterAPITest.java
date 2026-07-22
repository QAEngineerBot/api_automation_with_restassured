package com.api.tests;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.notNullValue;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

import static com.api.constants.Roles.FD;
import static com.api.utils.SpecUtil.requestSpec;
import static com.api.utils.SpecUtil.requestSpecWithAuth;
import static com.api.utils.SpecUtil.successResponseSpec;
import static com.api.utils.SpecUtil.unAuthorizedResponseSpec;

import static io.restassured.RestAssured.given;

@Epic("Job Management")
@Feature("Master API")
@Listeners(com.listeners.ApiTestListener.class)
public class MasterAPITest {

    @Story("Verify Master API with valid token")
    @Description("This test verifies the Master API with a valid token and checks the response against the expected schema.")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void verifyMasterApi() {
        given()
                .spec(requestSpecWithAuth(FD))
                .contentType("")
                .when()
                .post("master")
                .then()
                .spec(successResponseSpec())
                .body("$", hasKey("message"))
                .body("message", equalTo("Success"))
                .body("$", hasKey("data"))
                .body("data", notNullValue())
                .body("data", hasKey("mst_model"))
                .body("data.mst_oem.id", everyItem(notNullValue()))
                .body("data", hasKey("mst_oem"))
                .body("data.mst_oem.size()", greaterThan(0))
                .body("data.mst_model.size()", greaterThan(0));
    }

    @Story("Verify Master API without token")
    @Description("This test verifies the Master API without token and checks the response code against the expected.")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void verifyMasterApiWithInvalidToken() {
        given()
                .spec(requestSpec())
                .when()
                .post("master")
                .then()
                .spec(unAuthorizedResponseSpec());
    }
}
