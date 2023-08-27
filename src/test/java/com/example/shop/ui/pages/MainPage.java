package com.example.shop.ui.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static io.qameta.allure.Allure.step;

public class MainPage {
  SelenideElement logo = $x("//img[@alt='logo']"),
          createShopButton = $x("//a[@href='#create_shop']"),
          allShopsButton = $x("//a[@href='#all_shops']"),
          deleteShopButton = $x("//a[@href='#delete_shop']"),
          createShopTitle = $x("//h2[text()='Create a shop']"),
          deleteShopTitle = $x("//h2[text()='Delete a shop']"),
          allShopsTitle = $x("//h2[text()='Already created shops']"),
          mainTitle = $x("//h1[text()='Welcome to our shop constructor!']"),
          createShopInput = $x("//input[@placeholder='Enter shop name']"),
          publicShopCheckbox = $x("//input[@type='checkbox']"),
          createButton = $x("//button[text()='Create shop']"),
          deleteShopInput = $x("//input[@placeholder='Enter shop id']"),
          deleteButton = $x("//button[text()='Delete shop']"),
          refreshButton = $x("//button[text()='Refresh']"),
          deleteEmptyError = $x("//p[text()='Must be not empty']"),
          createNameError = $x("//p[text()='Store naming convention:']"),
          telegramButton = $x("//a[@href='https://web.telegram.org/']"),
          vkButton = $x("//a[@href='https://m.vk.com/']"),
          shopsTable = $("#shops_div");


  public void clickCreateShopButton() {
    step("Нажать на кнопку Create shop", () -> {
      createShopButton.click();
    });
  }

  public void clickAllShopsButton() {
    step("Нажать на кнопку All shop", () -> {
      allShopsButton.click();
    });
  }

  public void clickDeleteShopButton() {
    step("Нажать на кнопку Delete shop", () -> {
      deleteShopButton.click();
    });
  }

  public void clickPublicShopCheckbox() {
    step("Нажать чек-бокс public", () -> {
      publicShopCheckbox.click();
    });
  }

  public void clickCreateButton() {
    step("Нажать кнопку Create shop", () -> {
      createButton.click();
    });
  }

  public void clickDeleteButton() {
    step("Нажать кнопку Delete shop", () -> {
      deleteButton.click();
    });
  }

  public void clickRefreshButton() {
    step("Нажать на кнопку refresh", () -> {
      refreshButton.click();
    });
  }

  public void clickTelegramButton() {
    step("Нажать на кнопку Telegram", () -> {
      telegramButton.click();
    });
  }

  public void clickVkButton() {
    step("Нажать на кнопку VK", () -> {
      vkButton.click();
    });
  }

  public void shouldCreateShopTitle(String value) {
    step("Проверить переход по ссылке", () -> {
      createShopTitle.shouldHave(text(value));
    });
  }

  public void shouldDeleteShopTitle(String value) {
    step("Проверить переход по ссылке", () -> {
      deleteShopTitle.shouldHave(text(value));
    });
  }

  public void shouldAllShopsTitle(String value) {
    step("Проверить переход по ссылке", () -> {
      allShopsTitle.shouldHave(text(value));
    });
  }

  public void shouldMainTitle(String value) {
    step("Проверить отображение логотипа и главного заголовка", () -> {
      mainTitle.shouldHave(text(value));
    });
  }

  public void typeCreateShopInput(String value) {
    step("Заполнить поле Название магазина", () -> {
    createShopInput.setValue(value);
    });
  }

  public void typeDeleteShopInput(String value) {
    step("Заполнить поле Название магазина", () -> {
      deleteShopInput.setValue(value);
    });
  }

  public void shouldVisibleLogo() {
    step("Проверить отображение логотипа и главного заголовка", () -> {
      logo.shouldBe(visible);
    });
  }

  public void shouldDeleteEmptyError() {
    step("Проверить отображение ошибки", () -> {
      deleteEmptyError.shouldBe(visible);
    });
  }

  public void shouldCreateNameError() {
    step("Проверить отображение всплывающей ошибки", () -> {
      createNameError.shouldBe(visible);
    });
  }

  public void shouldCreateShopButton() {
    createShopButton.shouldBe(visible);
  }

  public void shouldAllShopsButton() {
    allShopsButton.shouldBe(visible);
  }

  public void shouldDeleteShopButton() {
    deleteShopButton.shouldBe(visible);
  }

  public void shouldShop(String value) {
    step("Проверить в таблице добавление магазина", () -> {
      shopsTable.shouldHave(text(value));
    });
  }

  public void shouldMenu() {
    step("Проверить отображение главного меню", () -> {
      shouldCreateShopButton();
      shouldAllShopsButton();
      shouldDeleteShopButton();
    });
  }

}