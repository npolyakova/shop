package com.example.shop;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static io.restassured.RestAssured.when;

@SpringBootTest
class ShopApplicationTests {

	@Test
	void contextLoads() {
		when().get("http://localhost:4000").then().statusCode(200);
	}

}
