package com.api.tests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import com.api.pojo.UserLoginCredentials;

import io.restassured.http.ContentType;
import static io.restassured.module.jsv.JsonSchemaValidator.*;

public class LoginAPITest {
	

	@Test
	public void loginAPITest() {
		
		UserLoginCredentials userCred = new UserLoginCredentials("iamfd","password");

		
		given().baseUri("http://64.227.160.186:9000/v1")
		       .and().contentType(ContentType.JSON)
		       .and()
			   .accept(ContentType.JSON)
			   .and().body(userCred)
			   .and()
			   .log().uri()
			   .log().headers()
			   .log().method()
			   .log().body()
		.when().post("login")
		.then().log().body()
		       .and()
		       .log().ifValidationFails()
		       .and()
		       .statusCode(200)
		       .and()
			   .body("message", equalTo("Success"))
			   .and()
			   .body(matchesJsonSchemaInClasspath("response-schema/LoginResponseSchema.json"));
	}
	

}
