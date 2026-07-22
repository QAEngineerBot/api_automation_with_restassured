package com.api.tests;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.api.constants.Roles;
import com.api.pojo.CreateJobApiRequest;
import com.api.pojo.Customer;
import com.api.pojo.CustomerAddress;
import com.api.pojo.CustomerProduct;
import com.api.pojo.Problems;
import com.api.utils.SpecUtil;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

@Epic("Job Management")
@Feature("Create Job API")
@Listeners(com.listeners.ApiTestListener.class)
public class CreateJobAPITest {

    Customer customer = new Customer("John", "Doe", "7637483748", "", "john123@gmail.com", "");
    CustomerAddress customerAddress = new CustomerAddress("1", "AG", "MG", "PG", "pune", "411052", "India",
            "Maharashtra");
    CustomerProduct customerProduct = new CustomerProduct("2025-09-23T18:30:00.000Z", "12349846994846",
            "12349846994846", "12349846994846", "2025-09-23T18:30:00.000Z", 3, 3);
    Problems problems = new Problems(1, "Battery issue");
    List<Problems> problemsList = new ArrayList<Problems>();
    CreateJobApiRequest createJobApiRequest = new CreateJobApiRequest(0, 2, 1, 2, customer, customerAddress,
            customerProduct, problemsList);

    @Story("Verify Create Job API with valid token")
    @Description("This test verifies the Create Job API with a valid token and checks the response against the expected schema.")
    @Severity(SeverityLevel.BLOCKER)
    @Test
    public void verifyCreateJobApi() {

        problemsList.add(problems);

        given().spec(SpecUtil.requestSpec(Roles.FD, createJobApiRequest))
                .when()
                .post("job/create")
                .then()
                .spec(SpecUtil.successResponseSpec());
    }
}
