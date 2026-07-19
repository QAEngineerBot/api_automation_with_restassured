package com.api.tests;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.notNullValue;
import org.testng.annotations.Test;

import com.api.constants.Roles;
import com.api.utils.AuthTokenProvider;
import com.api.utils.ConfigManager;
import static com.api.utils.ConfigManager.getProperty;

import static io.restassured.RestAssured.given;

public class MasterAPITest {

    @Test
    public void verifyMasterApi() {
        given()
                .baseUri(ConfigManager.getProperty("BASE_URI"))
                .and()
                .header("Authorization", AuthTokenProvider.getToken(Roles.FD))
                .and()
                .contentType("")
                .log().headers()
                .log().method()
                .when()
                .post("master")
                .then()
                .log().body()
                .log().ifValidationFails()
                .statusCode(200)
                .time(lessThan(1000L))
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
                .baseUri(getProperty("BASE_URI"))
                .contentType("")
                .headers("Authorization", "")
                .when()
                .post("master")
                .then()
                .log().body()
                .statusCode(401);
    }
}
