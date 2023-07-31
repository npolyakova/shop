package com.example.shop.Ui;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.actions;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class ShopUiTest {


    BaseUiTest baseUiTest = new BaseUiTest();


    @BeforeEach
    public void setSelenide() throws InterruptedException {
        System.setProperty("chromeoptions.args", "--remote-allow-origins=*");
        open("http://localhost:4000/");
        Thread.sleep(5000);
    }

    //Test №1
    @Test
    @DisplayName("Проверка отображения главной страницы: url, приветствие, иконка в заголовке")
    @Feature("Меню")
    @Story("Главная страница")
    public void ShouldCheckMainPageLocation() {
        step("Проверить главную страницу сайта Онлайн-магазинов, по адрессу", () -> {
            WebDriverRunner.url().equals("http://localhost:4000/");
        });
        step("Приветствие на главной странице и наличие иконки в заголовке", () -> {
            baseUiTest.Greetings.shouldHave(Condition.text("Welcome to our shop constructor!"));
            baseUiTest.MainIcons.shouldBe(Condition.visible);
        });

    }

    //Test №2
    @Test
    @DisplayName("Проверка наличия основных кнопок-ссылок в верхней части главной страницы")
    @Feature("Меню")
    @Story("Главная страница")
    public void ShouldCheckMainButtonsInTopsOfThePage() {
        step("Проверить наличие кнопок: Create shop, All shops, Delete shop", () -> {
            baseUiTest.Button_Href_CREATE_SHOP.shouldBe(Condition.visible);
            baseUiTest.Button_Href_AllShops.shouldBe(Condition.visible);
            baseUiTest.Button_Href_DeleteShop.shouldBe(Condition.visible);
        });
    }

    //Test №3
    @Test
    @DisplayName("Проверка работоспособности основных кнопок-ссылок в верхней части главной страницы")
    @Feature("Меню")
    @Story("Главная страница")
    public void ShouldCheckWorkMainButtonsInTopsOfThePage() {
        step("Проверить кликабельность кнопок: Create shop, All shops, Delete shop", () -> {
            baseUiTest.Button_Href_CREATE_SHOP.click();
            baseUiTest.Form_CreateShop.shouldBe(Condition.visible);

            baseUiTest.Button_Href_AllShops.click();
            baseUiTest.Form_AlreadyCreatedShops.shouldBe(Condition.visible);

            baseUiTest.Button_Href_DeleteShop.click();
            baseUiTest.Form_DeleateShop.shouldBe(Condition.visible);
        });
    }

    //Test №4
    @Test
    @DisplayName("Проверка кнопки Create shop")
    @Feature("Меню")
    @Story("Главная страница")
    public void ShouldCheckButtonCreateShop() {
        step("Ввести в поле наименование магазина - название магазина, поставить отметку публичный, нажать кнопку " +
                "Create shop", () -> {
            baseUiTest.placeholderEnterShopName.setValue("Create New Online shop№1");
            baseUiTest.checkbox.click();
            baseUiTest.Button_Createshop.click();
            actions().keyDown(Keys.ENTER).click();
        });

        step("Подтвердить действие на странице, проверить добавление магазина в список магазинов", () -> {
            actions().keyDown(Keys.ENTER).click();
            baseUiTest.Body_Table.shouldHave(Condition.text("Create New Online shop№1"));
        });

    }

    //Test №5
    @Test
    @DisplayName("Проверка кнопки All shops")
    @Feature("Меню")
    @Story("Главная страница")
    public void ShouldCheckButtonAllShop() {
        step("Проверить переход по кнопке-ссылке All shops, к списку магазинов", () -> {
            baseUiTest.Button_Href_AllShops.click();
            baseUiTest.Form_AlreadyCreatedShops.shouldBe(Condition.visible);
        });
    }

    //Test №6
    @Test
    @DisplayName("Проверка кнопки Delete shop")
    @Feature("Меню")
    @Story("Главная страница")
    public void ShouldCheckButtonDeleteShop() {
        step("Ввести в поле Enter shop id - id магазина, нажать кнопку Delete shop", () -> {
            baseUiTest.placeholderEnterShopId.setValue("8752");
            baseUiTest.Button_DeleteShop.click();
        });

        step("Подтвердить действие на странице", () -> {
            actions().keyDown(Keys.ENTER).click();
        });
    }

    //Test №7
    @Test
    @DisplayName("Проверка кнопки refresh")
    @Feature("Меню")
    @Story("Главная страница")
    public void ShouldCheckRefreshButton() {
        step("Проверить рабоспособность кнопки refresh, проверить что на главной странице отображаетя адрес," +
                "приветствие, логотип", () -> {
            baseUiTest.Button_Refresh.click();
            WebDriverRunner.url().equals("http://localhost:4000/");
            baseUiTest.Greetings.shouldHave(Condition.text("Welcome to our shop constructor!"));
            baseUiTest.MainIcons.shouldBe(Condition.visible);

        });
    }

    //Test №8
    @Test
    @DisplayName("Проверка кнопки Telegram")
    @Feature("Меню")
    @Story("Главная страница")
    public void ShouldCheckTelegramButton() {
        step("Нажать на кнопку Telegram", () -> {
            baseUiTest.Button_Telegram.click();
        });
        step("Проверить переход на страницу Telegram", () -> {
            WebDriverRunner.url().equals("https://web.telegram.org/a/");
        });
    }

    //Test №9
    @Test
    @DisplayName("Проверка кнопки VK. Тест падает.")
    @Feature("Меню")
    @Story("Главная страница")
    public void ShouldCheckVKButton() {
        step("Нажать на кнопку VK", () -> {
            baseUiTest.Button_VK.click();
        });
        step("Проверить переход на страницу VK", () -> {
            WebDriverRunner.url().equals("https://m.vk.com/");
        });
    }
}