package com.api.utils;

import com.api.constants.Roles;
import com.api.request.model.LoginApiRequest;

import static com.api.constants.Roles.ENG;
import static com.api.constants.Roles.FD;
import static com.api.constants.Roles.QC;
import static com.api.constants.Roles.SUP;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;

public class AuthTokenProvider {

    private AuthTokenProvider() {
        // private constructor to restrict object creation
    }

    public static String getToken(Roles role) {

        LoginApiRequest userCreds = null;

        if (role != null) {
            switch (role) {
                case FD ->
                    userCreds = new LoginApiRequest("iamfd", "password");

                case SUP ->
                    userCreds = new LoginApiRequest("iamsup", "password");

                case ENG ->
                    userCreds = new LoginApiRequest("iameng", "password");

                case QC ->
                    userCreds = new LoginApiRequest("iamqc", "password");
                default ->
                    userCreds = new LoginApiRequest("iamfd", "password");
            }
        }

        return given().baseUri(ConfigManager.getProperty("BASE_URI")).contentType(ContentType.JSON)
                .body(userCreds).post("login").then().log().ifValidationFails().statusCode(200).extract().body()
                .jsonPath().getString("data.token");

    }

}
