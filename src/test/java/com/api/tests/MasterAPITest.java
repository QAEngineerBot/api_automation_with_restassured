package com.api.tests;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.notNullValue;
import org.testng.annotations.Test;

import static com.api.constants.Roles.FD;
import static com.api.utils.SpecUtils.requestSpec;
import static com.api.utils.SpecUtils.requestSpecWithAuth;
import static com.api.utils.SpecUtils.successResponseSpec;
import static com.api.utils.SpecUtils.unAuthorizedResponseSpec;

import static io.restassured.RestAssured.given;

public class MasterAPITest {

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
