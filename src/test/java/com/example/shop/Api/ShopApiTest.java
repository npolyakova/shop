package com.example.shop.Api;

import com.example.shop.models.ShopDto;
import io.qameta.allure.Feature;
import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.assertj.core.api.Assertions;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ShopApiTest extends BaseApiTest{



    //Test №1
    @Test
    @Feature("Онлайн Магазин")
    @DisplayName("Cоздание магазина")
    public void ShouldAddShop() {


        RequestSpecification request = RestAssured.given();

        final ShopDto shopDto = new ShopDto(shopId, shopName, shopPublic);
        JSONObject object = new JSONObject();
        object.put(String.valueOf(shopId), shopDto.getShopId());
        object.put(shopName, shopDto.getShopName());
        object.put(String.valueOf(shopPublic), shopDto.isShopPublic());

        request.body(object.toString());
        request.header("content-type", "application/json");

        System.out.println(object);

        given()
                .body(shopDto)
                .when()
                .post("http://localhost:4000/shops/add")
                .then();

    }

    //Test №2
    @Test
    @Feature("Онлайн Магазин")
    @DisplayName("Получение всех магазинов")
    public void ShouldGetAllShops() {
        ResponseSpecification responseSpec = new ResponseSpecBuilder().expectStatusCode(200).build();
        Response response1 = given().get("http://localhost:4000/shops/all");
        Assertions.assertThat(response1)
                .extracting(
                        Response::getStatusCode,
                        Response::getStatusLine
                )
                .contains(
                        200,
                        "HTTP/1.1 200 "
                );
    }

    //Test №3
     @Test
    @Feature("Онлайн Магазин")
    @DisplayName("Получение магазина по Id")
   public void ShouldGetShopNameById() {
        RequestSpecification getRequest = given();
        RequestSpecification request = RestAssured.given();

        final ShopDto shopDto = new ShopDto(shopId, shopName, shopPublic);

        JSONObject object = new JSONObject();
        object.put(String.valueOf(shopId), shopDto.getShopId());
        object.put(shopName, shopDto.getShopName());
        object.put(String.valueOf(shopPublic), shopDto.isShopPublic());
        request.body(object.toString());
        request.header("content-type", "application/json");

        final JSONArray shopDtojson =new JSONArray(List.of(object));
        getRequest.body(shopDtojson.toString());

        System.out.println(getRequest.log().body());

        Response response = getRequest.get("http://localhost:4000/shops/8452" );// "http://localhost:4000/shops/8752"

        response
                .then()
                .statusCode(200)
                .assertThat()
                .body("shopId", equalTo(8452));
    }


    //Test №4
    @Test
    @Feature("Онлайн Магазин")
    @DisplayName("Удаление магазина")
    public void ShouldDeleteOneShop() {
        ResponseSpecification responseSpec = new ResponseSpecBuilder().expectStatusCode(200).build();
        Response response1 = given().delete("http://localhost:4000/shops/8752");
        Assertions.assertThat(response1)
                .extracting(
                        Response::getStatusCode,
                        Response::getStatusLine
                );

    }

}
