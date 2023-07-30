package com.example.shop.Ui;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class BaseUiTest {

    public SelenideElement Greetings = $("#greetings > h1");

    public SelenideElement MainIcons = $("html > body > header > img");

    public SelenideElement Button_Href_CREATE_SHOP = $("#links > a:first-of-type");

    public SelenideElement Button_Href_AllShops = $("#links > a:nth-of-type(2)");

    public SelenideElement Button_Href_DeleteShop = $("#links > a:nth-of-type(3)");

    public SelenideElement Button_Refresh = $("#shops_div > button");

    public SelenideElement Button_Createshop = $("#create > div > button");

    public SelenideElement checkbox = $("#public");

    public SelenideElement placeholderEnterShopName = $("#name");

    public SelenideElement Button_DeleteShop = $("#delete > div > button");

    public SelenideElement placeholderEnterShopId = $("#id");

    public SelenideElement Form_CreateShop = $("#create > div > h2");

    public SelenideElement Form_DeleateShop = $("#delete > div > h2");

    public SelenideElement Form_AlreadyCreatedShops = $("#shops_div > h2");

    public SelenideElement Body_Table = $("#response");



}