package com.example.shop;

import com.example.shop.models.ProductPojo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.example.shop.BaseTest.generateSomeProducts;

public class ExTest {

    @BeforeEach
    public void setUp() {
        ProductPojo product = generateSomeProducts();
    }

    @Test
    public void shouldTest() {

    }
}
