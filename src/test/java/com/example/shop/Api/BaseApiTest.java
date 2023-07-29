package com.example.shop.Api;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.util.stream.DoubleStream;

public class BaseApiTest {


    @BeforeAll
    public static void setBaseUri() {
        RestAssured.baseURI = "http://localhost:4000/";
    }

        Faker faker = new Faker();
        String shopName = faker.name().username();
        Long shopId = faker.random().nextLong();
        Boolean shopPublic = faker.bool().bool();

    public static RequestSpecification requestSpecification;
    public ResponseSpecification responseSpecification;

    @BeforeEach
    public void setup() {
    RequestSpecification requestSpecification = new RequestSpecBuilder().build();
    }



}
