package com.example.shop.ui.tests;

import com.codeborne.selenide.Configuration;
import com.example.shop.ui.pages.MainPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;
@DisplayName("UI тесты")
class ShopUiTests {
  @BeforeAll
  static void setUp() {
    Configuration.browser = "Firefox";
    Configuration.headless = true;
  }

  @AfterEach
  void addAttachments() {
    closeWebDriver();
  }

  MainPage mainPage = new MainPage();

  @BeforeEach
  public void setSelenide() {
    open("http://localhost:63342/shop/src/main/java/com/example/shop/ui/main.html?_ijt=n88fnoh7kl99vdqij96g182bvb&_ij_reload=RELOAD_ON_SAVE");

  }

  @Test
  @DisplayName("Проверка отображения логотипа и главного заголовка")
  void shouldLogoAndTitleTest() {
      mainPage.shouldVisibleLogo();
      mainPage.shouldMainTitle("Welcome to our shop constructor!");
  }

  @Test
  @DisplayName("Проверка отображения главного меню")
  void shouldMenuTest() {
      mainPage.shouldMenu();
  }

  @Test
  @DisplayName("Проверка кнопки Создать магазин")
  void shouldCreateShopButtonTest() {
      mainPage.clickCreateShopButton();
      mainPage.shouldCreateShopTitle("Create a shop");
  }

  @Test
  @DisplayName("Проверка кнопки Все магазины")
  void shouldAllShopButtonTest() {
      mainPage.clickAllShopsButton();
      mainPage.shouldAllShopsTitle("Already created shops");
  }

  @Test
  @DisplayName("Проверка кнопки Удалить магазин")
  void shouldDeleteShopButtonTest() {
      mainPage.clickDeleteShopButton();
      mainPage.shouldDeleteShopTitle("Delete a shop");
  }


  @Test
  @DisplayName("Проверка кнопки Создать магазин")
  void shouldValidCreateShopTest() {
      mainPage.clickCreateShopButton();
      mainPage.typeCreateShopInput("ShopName");
      mainPage.clickCreateButton();
      actions().keyDown(Keys.ENTER).perform();
      mainPage.shouldShop("ShopName");
  }

  @Test
  @DisplayName("Проверка отображения ошибки при некорректном названии магазина")
  void shouldErrorShopTest() {
      mainPage.clickCreateShopButton();
      mainPage.typeCreateShopInput("111111111");
      mainPage.clickCreateButton();
      mainPage.shouldCreateNameError();
  }

  @Test
  @DisplayName("Проверка кнопки Удалить магазин")
  void shouldDeleteShopTest() {
      mainPage.clickDeleteShopButton();
      mainPage.typeDeleteShopInput("111111111");
      mainPage.clickDeleteButton();
      actions().keyDown(Keys.ENTER).perform();
  }

  @Test
  @DisplayName("Проверка отображения ошибки при удалении магазина без ID")
  void shouldEmptyDeleteShopTest() {
      mainPage.clickDeleteShopButton();
      mainPage.clickDeleteButton();
      mainPage.shouldDeleteEmptyError();
  }

  @Test
  @DisplayName("Проверка кнопки Создать магазин - публичный")
  void shouldCreatePublicShopTest() {
      mainPage.clickCreateShopButton();
      mainPage.typeCreateShopInput("ShopName");
      mainPage.clickPublicShopCheckbox();
      mainPage.clickCreateButton();
      actions().keyDown(Keys.ENTER).perform();
      mainPage.shouldShop("ShopName");
  }

  @Test
  @DisplayName("Проверка кнопки refresh")
  void shouldRefreshShopTest() {
      mainPage.clickAllShopsButton();
      mainPage.clickRefreshButton();
      mainPage.shouldVisibleLogo();
      mainPage.shouldMainTitle("Welcome to our shop constructor!");
  }

  @Test
  @DisplayName("Проверка кнопки Telegram")
  void shouldTelegramButtonTest() {
      mainPage.clickTelegramButton();
    step("Проверить переход по ссылке", () -> {
      switchTo().window("Telegram");
    });
  }

  @Test
  @DisplayName("Проверка кнопки VK")
  void shouldVKButtonTest() {
        mainPage.clickVkButton();
    step("Проверить переход по ссылке", () -> {
      switchTo().window("ВКонтакте | Добро пожаловать");
    });
  }

}