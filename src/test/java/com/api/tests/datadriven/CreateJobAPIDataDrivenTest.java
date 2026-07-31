package com.api.tests.datadriven;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.api.constants.Roles;
import com.api.request.model.CreateJobApiRequest;
import com.api.utils.CreateJobAPIMapper;
import com.api.utils.SpecUtil;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("Job Management")
@Feature("Create Job API")
@Listeners(com.listeners.ApiTestListener.class)
public class CreateJobAPIDataDrivenTest {

	@Story("Verify Create Job API with valid token")
	@Description("This test verifies the Create Job API with a valid token and checks the response against the expected schema.")
	@Severity(SeverityLevel.BLOCKER)
	@Test(groups = { "api", "smoke",
			"regression" }, dataProviderClass = com.dataproviders.DataProviderUtils.class, dataProvider = "CreateJobFakerDataProvider")
	public void verifyCreateJobApi(CreateJobApiRequest createJobApiRequest) {
		given().spec(SpecUtil.requestSpec(Roles.FD, createJobApiRequest)).when().post("job/create").then()
				.spec(SpecUtil.successResponseSpec());
	}
}
