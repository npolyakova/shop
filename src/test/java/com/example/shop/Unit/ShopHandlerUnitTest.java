package com.example.shop.Unit;

import com.example.shop.ShopHandler;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ShopHandlerUnitTest {

    // Test №1
    @Test
    @DisplayName("Проверка количества букв названия магазина, который равен или больше количества магазинов ")
    void ShouldCheckLengthShopNameComparedToCount() {
        String name = "OnlineShop";
        int count = 8;

        boolean result = ShopHandler.checkLength(name, count);

        assertTrue(result);
    }

    // Test №2
    @Test
    @DisplayName("Отрицательный тест. Проверка количества букв названия магазина, который меньше количества магазинов")
    void ShouldCheckLengthShopNameLessThenCount() {
        String name = "OnlineShop";
        int count = 11;

        boolean result = ShopHandler.checkLength(name, count);

        assertFalse(result);
    }

}