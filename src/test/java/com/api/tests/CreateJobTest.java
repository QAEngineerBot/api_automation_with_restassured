package com.api.tests;

import org.testng.annotations.Test;

import com.api.constants.Roles;
import com.api.pojo.CreateJobApiRequest;
import com.api.pojo.Customer;
import com.api.pojo.CustomerAddress;
import com.api.pojo.CustomerProduct;
import com.api.pojo.Problems;
import com.api.utils.SpecUtils;

import static io.restassured.RestAssured.*;

public class CreateJobTest {

    Customer customer= new Customer("John","Doe","7637483748","","john123@gmail.com","");
    CustomerAddress customerAddress= new CustomerAddress("123 Main St","Apt 4B","New York street","GST Building","NYK","100001","USA","MAHARASHTRA");
    CustomerProduct customerProduct= new CustomerProduct("2025-09-23T18:30:00.000Z","73635363738712","73635363738712","73635363738712","2025-09-23T18:30:00.000Z",3,3);
    Problems problems[]= {new Problems(1,"Problem 1")};
    CreateJobApiRequest createJobApiRequest= new CreateJobApiRequest(1,1,1,1,customer,customerAddress,customerProduct,problems);

    @Test
    public void verifyCreateJobApi() {
        given().spec(SpecUtils.requestSpec(Roles.SUP,createJobApiRequest))
                .when()
                .post("job/create")
                .then()
                .spec(SpecUtils.successResponseSpec());
    }
}
