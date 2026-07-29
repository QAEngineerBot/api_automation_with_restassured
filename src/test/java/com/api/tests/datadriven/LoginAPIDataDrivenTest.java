package com.api.tests.datadriven;

import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.api.request.model.LoginApiRequest;
import com.data.beans.LoginRequestBean;

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
public class LoginAPIDataDrivenTest {

	@Story("Verify Login API with valid credentials")
	@Description("This test verifies the login API with valid credentials and checks the response against the expected schema.")
	@Severity(SeverityLevel.BLOCKER)
	@Test(groups = { "api", "smoke",
			"regression" }, dataProviderClass = com.dataproviders.DataProviderUtils.class, dataProvider = "LoginDataProvider")
	public void loginAPITest(LoginRequestBean loginRequestBean) {
		given().spec(requestSpec(loginRequestBean)).when().post("login").then().spec(successResponseSpec()).log()
				.ifValidationFails().and().body("message", equalTo("Success")).and()
				.body(matchesJsonSchemaInClasspath("response-schema/LoginResponseSchema.json"));
	}

}
