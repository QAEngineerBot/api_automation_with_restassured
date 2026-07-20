package com.api.utils;

import org.hamcrest.Matchers;

import com.api.constants.Roles;
import static com.api.utils.ConfigManager.getProperty;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecUtils {

    public static RequestSpecification requestSpec() {
        return new RequestSpecBuilder().setBaseUri(getProperty("BASE_URI"))
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .log(LogDetail.BODY)
                .log(LogDetail.HEADERS)
                .log(LogDetail.URI)
                .log(LogDetail.METHOD)
                .build();
    }

    public static RequestSpecification requestSpec(Object payload) {
        return new RequestSpecBuilder().setBaseUri(getProperty("BASE_URI"))
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .setBody(payload)
                .log(LogDetail.BODY)
                .log(LogDetail.HEADERS)
                .log(LogDetail.URI)
                .log(LogDetail.METHOD)
                .build();
    }

    public static RequestSpecification requestSpecWithAuth(Roles role) {
        return new RequestSpecBuilder().setBaseUri(getProperty("BASE_URI"))
                .addHeader("Authorization", AuthTokenProvider.getToken(role))
                .setAccept(ContentType.JSON)
                .log(LogDetail.BODY)
                .log(LogDetail.HEADERS)
                .log(LogDetail.URI)
                .log(LogDetail.METHOD)
                .build();
    }

    public static ResponseSpecification successResponseSpec() {
        return new ResponseSpecBuilder().expectContentType(ContentType.JSON)
                .expectStatusCode(200)
                .log(LogDetail.BODY)
                .expectResponseTime(Matchers.lessThan(1500L))
                .build();
    }

    public static ResponseSpecification unAuthorizedResponseSpec() {
        return new ResponseSpecBuilder()
                .expectStatusCode(401)
                .log(LogDetail.BODY)
                .expectResponseTime(Matchers.lessThan(1500L))
                .build();
    }
}
