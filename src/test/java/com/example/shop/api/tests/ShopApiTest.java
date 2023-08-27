package com.example.shop.api.tests;

import com.example.shop.api.TestBaseApi;
import com.example.shop.api.models.AddShopRequest;
import com.example.shop.api.models.AllShopResponse;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import static com.example.shop.api.data.DataGenerator.addShop;
import static com.example.shop.api.spec.Specifications.*;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("API тесты")
class ShopApiTest extends TestBaseApi {
  @BeforeAll
  public static void setUp() {
    installRequestSpecification(requestSpec(SHOP_URL));
  }

  @Test
  @DisplayName("Получение списка всех магазинов")
  void getAllShopsTest() {
    installResponseSpecification(responseSpecOK200());

    List<AllShopResponse> allShopResponse = given()
            .when()
            .get(SHOPS_ALL_PATH)
            .then()
            .extract().body().jsonPath().getList("", AllShopResponse.class);
    allShopResponse.forEach(x -> AssertionsForClassTypes.assertThat(x.getShopId()).isNotNull());
    allShopResponse.forEach(x -> AssertionsForClassTypes.assertThat(x.getShopName()).isNotNull());
    allShopResponse.forEach(x -> AssertionsForClassTypes.assertThat(x.getShopPublic()).isNotNull());
  }

  @Test
  @DisplayName("Получение одного магазина")
  void getShopTest() {
    installResponseSpecification(responseSpecOK200());

    AllShopResponse allShopResponse = given()

            .when()
            .get(SHOPS_PATH + 102)
            .then()
            .extract().body().as(AllShopResponse.class);
    assertThat(allShopResponse.getShopId()).isEqualTo(102);

  }

  @Test
  @DisplayName("Создание одного магазина")
  void addShopTest() {
    installResponseSpecification(responseSpecOK200WithoutJson());

    AddShopRequest data = addShop();
    given()
            .body(data)
            .when()
            .post(SHOPS_ADD_PATH)
            .then();
  }

  @Test
  @DisplayName("Удаление одного магазина")
  void deleteShopTest() {
    installResponseSpecification(responseSpecOK204());

    given()
            .when()
            .delete(SHOPS_DELETE_PATH + 1)
            .then();
  }

}