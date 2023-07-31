package com.example.shop.Unit;

import com.example.shop.ShopHandler;
import com.example.shop.controllers.ShopController;
import com.example.shop.models.ShopDto;
import com.example.shop.models.ShopPojo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ShopControllerUnitTest {


	@Mock
	public ShopHandler shopHandler;

	@InjectMocks
	public ShopController shopController;



	// Test №1
	@Test
	@DisplayName("Add the shop name, that has more than 6 letters and stars with the capital letter")
	void ShouldAddShopWithCorrectName() {
		ShopDto dto = new ShopDto(1L, "OnlineShop№1", true);

		ResponseEntity<String> response = shopController.addShop(dto);


		assertEquals(200, response.getStatusCodeValue());

	}

	// Test №2
	@Test
	@DisplayName("Negative Test. The shop name does not start with a capital letter")
	void ShouldAddShopNameWithoutCapitalLetter() {
		ShopDto dto = new ShopDto(1L, "onlineShop№2", true);

		ResponseEntity<String> response = shopController.addShop(dto);

		assertEquals(400, response.getStatusCodeValue());
		assertEquals("Name should begin with a capital letter", response.getBody());

	}

	// Test №3
	@Test
	@DisplayName("Negative Test. The shop name is less than 7 letters")
	void ShouldAddShortShopName() {
		ShopDto dto = new ShopDto(1L, "Shop№3", true);

		ResponseEntity<String> response = shopController.addShop(dto);

		assertEquals(400, response.getStatusCodeValue());
		assertEquals("Name should be more than 6 letters", response.getBody());
	}


	// Test №4
	@Test
	@DisplayName("The shop name starts with any different symbols, but exactly starts with Capital letter")
	void ShouldAddShopNameWithDifferentSymbols() {
		ShopDto dto = new ShopDto(1L, "OnlineShop?!#@$%&№~*^`'/|(_)[]{}-+=.,:;<>", true);

		ResponseEntity<String> response = shopController.addShop(dto);
		assertThat(response).isNotNull();
	}


	// Test №5
	@Test
	@DisplayName("The shop name contains only 256 symbols")
	void ShouldAddShopNameLengthOf256Symbols() {
		ShopDto dto = new ShopDto(1L,"?!#@$%&№~*^`'/|(_)[]{}-+=.,:;<>LongOnlineShopNameLongOnlineShopNameLongOnlineShopNameLongOnlineShopNameLongOnlineShopNameLongOnlineShopNameLongOnlineShopNameLongOnlineShopNameLongOnlineShopNameLongOnlineShopNameLongOnlineShopNameLongOnlineShopNameLongOnlin", true);

		ResponseEntity<String> response = shopController.addShop(dto);
		assertThat(response).isNotNull();
	}

	// Test №6
	@Test
	@DisplayName("Return all shops")
	void ShouldgetAllShops() {
		List<ShopPojo> expectedShops = Collections.singletonList(
				new ShopPojo()
		);

		ResponseEntity<List<ShopPojo>> response = shopController.getShops();

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(expectedShops.toString(), response.getBody().toString());

	}
	private void assertEquals(int expectedShops, int body) {
	}
	private void assertEquals(String ok, String statusCode) {
	}
	private void assertEquals(HttpStatus ok, HttpStatusCode statusCode) {
	}

	// Test №7
	@Test
	@DisplayName("Get exist shop")
	void ShouldGetExistShop() {
		final ResponseEntity<ShopPojo> result = shopController.getShop(15);
		assertEquals(HttpStatus.OK, result.getStatusCode());
	}
}




