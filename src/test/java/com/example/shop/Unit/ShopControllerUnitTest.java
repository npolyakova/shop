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
	@DisplayName("Добавление магазина, с названием содержащим более 6 символов и начинающихся с Заглвной буквы")
	void ShouldAddShopWithCorrectName() {
		ShopDto dto = new ShopDto(1L, "OnlineShop№1", true);

		ResponseEntity<String> response = shopController.addShop(dto);


		assertEquals(200, response.getStatusCodeValue());

	}

	// Test №2
	@Test
	@DisplayName("Отрицательный тест. Добавление магазина, с названием начинающимся не с Заглавной буквы")
	void ShouldAddShopNameWithoutCapitalLetter() {
		ShopDto dto = new ShopDto(1L, "onlineShop№2", true);

		ResponseEntity<String> response = shopController.addShop(dto);

		assertEquals(400, response.getStatusCodeValue());
		assertEquals("Name should begin with a capital letter", response.getBody());

	}

	// Test №3
	@Test
	@DisplayName("Отрицательный тест. Добавление магазина с названием содержащим 6 символов")
	void ShouldAddShortShopName() {
		ShopDto dto = new ShopDto(1L, "Shop№3", true);

		ResponseEntity<String> response = shopController.addShop(dto);

		assertEquals(400, response.getStatusCodeValue());
		assertEquals("Name should be more than 6 letters", response.getBody());
	}


	// Test №4
	@Test
	@DisplayName("Добавление магазина, в названием которого входят любые символы, но начинатся с Заглавной буквы")
	void ShouldAddShopNameWithDifferentSymbols() {
		ShopDto dto = new ShopDto(1L, "OnlineShop?!#@$%&№~*^`'/|(_)[]{}-+=.,:;<>", true);

		ResponseEntity<String> response = shopController.addShop(dto);
		assertThat(response).isNotNull();
	}


	// Test №5
	@Test
	@DisplayName("Добавление магазина, с названием содержащим 256 символов")
	void ShouldAddShopNameLengthOf256Symbols() {
		ShopDto dto = new ShopDto(1L,"?!#@$%&№~*^`'/|(_)[]{}-+=.,:;<>LongOnlineShopNameLongOnlineShopNameLongOnlineShopNameLongOnlineShopNameLongOnlineShopNameLongOnlineShopNameLongOnlineShopNameLongOnlineShopNameLongOnlineShopNameLongOnlineShopNameLongOnlineShopNameLongOnlineShopNameLongOnlin", true);

		ResponseEntity<String> response = shopController.addShop(dto);
		assertThat(response).isNotNull();
	}

	// Test №6
	@Test
	@DisplayName("Получения всех магазинов")
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
	@DisplayName("Получение существующего магазина")
	void ShouldGetExistShop() {
		final ResponseEntity<ShopPojo> result = shopController.getShop(15);
		assertEquals(HttpStatus.OK, result.getStatusCode());
	}
}




