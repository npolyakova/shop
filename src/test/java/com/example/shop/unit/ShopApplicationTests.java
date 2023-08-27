package com.example.shop.unit;

import com.example.shop.controllers.ShopController;
import com.example.shop.models.ShopDto;
import com.example.shop.models.ShopPojo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import java.util.List;
import java.util.Objects;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest()
@DisplayName("UNIT тесты")
class ShopApplicationTests {
  @Autowired
  ShopController operationsHandler;
  // Тестовые данные для unit тестов
  static ShopDto shopWith7Name = new ShopDto(111111L, "Nameddd", false);
  static ShopDto shopWith6Name = new ShopDto(111111L, "Namedd", false);
  static ShopDto shopWithLowerCaseName = new ShopDto(111111L, "nameddd", false);
  static ShopDto shopWithAllSymbolsName = new ShopDto(111111L, "Name#45&ddd", false);
  static ShopDto shopWith256Name = new ShopDto(111111L, "NameWith256NameWith256NameWith256NameWith256NameWith256NameWith256NameWith256NameWith256NameWith256NameWith256NameWith256NameWith256NameWith256NameWith256NameWith256NameWith256NameWith256NameWith256NameWith256NameWith256NameWith256NameWith256NameWith256Nam", false);

  @Test
  @DisplayName("Создание магазина с именем - 7 символов")
  void shouldAddShopWith7Name() {
    final ResponseEntity<String> result = operationsHandler.addShop(shopWith7Name);
    assertThat(result).isNotNull();
  }

  @Test
  @DisplayName("Создание магазина с именем - 6 символов")
  void shouldAddShopWith6Name() {
    final ResponseEntity<String> result = operationsHandler.addShop(shopWith6Name);
    assertEquals("Name should be more than 6 letters", result.getBody());
  }

  @Test
  @DisplayName("Создание магазина с именем - нижний регистр")
  void shouldAddShopWithLowerCaseName() {
    final ResponseEntity<String> result = operationsHandler.addShop(shopWithLowerCaseName);
    assertEquals("Name should begin with a capital letter", result.getBody());
  }

  @Test
  @DisplayName("Создание магазина с именем - разные спец символы")
  void shouldShopWithAllSymbolsName() {
    final ResponseEntity<String> result = operationsHandler.addShop(shopWithAllSymbolsName);
    assertThat(result).isNotNull();
  }

  @Test
  @DisplayName("Создание магазина с именем - 256 символов")
  void shouldAddShopWith256Name() {
    final ResponseEntity<String> result = operationsHandler.addShop(shopWith256Name);
    assertThat(result).isNotNull();
  }


  @Test
  @DisplayName("Удаление несуществующего магазина")
  void deleteShop() {
    final ResponseEntity<String> result = operationsHandler.deleteShop(10000L);
    assertThat(result).isNotNull();
  }


  @Test
  @DisplayName("Получение всех магазинов")
  void shouldGetAllShops() {
    final List<ShopPojo> result = operationsHandler.getShops().getBody();
    System.out.println(result);
    assertThat(result).isNotNull();
  }

  @Test
  @DisplayName("Получение существующего магазина")
  void shouldGetOneShop() {
    final ResponseEntity<ShopPojo> result = operationsHandler.getShop(102);
    assertThat(Objects.requireNonNull(result.getBody()).getShopId()).isEqualTo(102);
    assertThat(Objects.requireNonNull(result.getBody()).getShopName()).isEqualTo("peterochka");
  }

  @Test
  @DisplayName("Получение несуществующего магазина")
  void shouldGetNotCreateShop() {
    final ResponseEntity<ShopPojo> result = operationsHandler.getShop(100000000);
    assertThat(result).isNotNull();
  }

}