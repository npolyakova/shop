package com.example.shop.api.spec;

import com.example.shop.api.TestBaseApi;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.http.HttpStatus;
import static io.restassured.http.ContentType.JSON;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.hamcrest.Matchers.lessThanOrEqualTo;

public class Specifications extends TestBaseApi {
  public static RequestSpecification requestSpec(String url) {
    return new RequestSpecBuilder()
            .setBaseUri(url)
            .setContentType(JSON)
            .build();
  }

  public static ResponseSpecification responseSpecOK200() {
    return new ResponseSpecBuilder()
            .expectContentType(JSON)
            .expectStatusCode(HttpStatus.SC_OK)
            .expectResponseTime(lessThanOrEqualTo(3L), SECONDS)
            .build();
  }

  public static ResponseSpecification responseSpecOK200WithoutJson() {
    return new ResponseSpecBuilder()
            .expectStatusCode(HttpStatus.SC_OK)
            .expectResponseTime(lessThanOrEqualTo(3L), SECONDS)
            .build();
  }

  public static ResponseSpecification responseSpecOK204() {
    return new ResponseSpecBuilder()
            .expectStatusCode(HttpStatus.SC_NO_CONTENT)
            .expectResponseTime(lessThanOrEqualTo(3L), SECONDS)
            .build();
  }

  public static void installRequestSpecification(RequestSpecification requestSpec) {
    RestAssured.requestSpecification = requestSpec;
  }

  public static void installResponseSpecification(ResponseSpecification responseSpec) {
    RestAssured.responseSpecification = responseSpec;
  }

}