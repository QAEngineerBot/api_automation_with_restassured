package com.api.tests;

import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.api.pojo.LoginApiRequest;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

import static com.api.utils.ConfigManager.getProperty;
import static com.api.utils.SpecUtil.*;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

@Epic("User Management")
@Feature("Login API")
@Listeners(com.listeners.ApiTestListener.class)
public class LoginAPITest {

    @Story("Verify Login API with valid credentials")
    @Description("This test verifies the login API with valid credentials and checks the response against the expected schema.")
    @Severity(SeverityLevel.BLOCKER)
    @Test
    public void loginAPITest() {

        LoginApiRequest userCred = new LoginApiRequest("iamfd", "password");

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
