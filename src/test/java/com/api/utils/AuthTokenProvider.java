package com.api.utils;

import com.api.constants.Roles;
import static com.api.constants.Roles.ENG;
import static com.api.constants.Roles.FD;
import static com.api.constants.Roles.QC;
import static com.api.constants.Roles.SUP;
import com.api.pojo.UserLoginCredentials;

import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;

public class AuthTokenProvider {

    private AuthTokenProvider() {
        // private constructor to restrict object creation
    }

    public static String getToken(Roles role) {

        UserLoginCredentials userCreds = null;

        if (role == FD) {
            userCreds = new UserLoginCredentials("iamfd", "password");
        } else if (role == SUP) {
            userCreds = new UserLoginCredentials("iamsup", "password");
        } else if (role == ENG) {
            userCreds = new UserLoginCredentials("iameng", "password");
        } else if (role == QC) {
            userCreds = new UserLoginCredentials("iamqc", "password");
        }

        String token = given().baseUri(ConfigManager.getProperty("BASE_URI")).contentType(ContentType.JSON)
                .body(userCreds).post("login").then().log().ifValidationFails().statusCode(200).extract().body()
                .jsonPath().getString("data.token");

        return token;

    }

}
