package ru.netology;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static ru.netology.DataGenerator.AddUser.*;

public class AutoLoginTest {
    @BeforeEach
    void setup() {
        open("http://localhost:9999");
    }

    @Test
    void ShouldValidAll() {
        Registration testUser = addUserValid();
        $("[data-test-id=login] input").setValue(testUser.getLogin());
        $("[data-test-id=password] input").setValue(testUser.getPassword());
        $(".button__content").click();
        $(".App_appContainer__3jRx1 h2").shouldBe(visible).shouldHave(text("Личный кабинет"));
    }

    @Test
    void AllInputBlocked() {
        Registration testUser = addUserBlocked();
        $("[data-test-id=login] input").setValue(testUser.getLogin());
        $("[data-test-id=password] input").setValue(testUser.getPassword());
        $(".button__content").click();
        $("[data-test-id=error-notification]").shouldBe(visible).shouldHave(text("Пользователь заблокирован"));
    }

    @Test
    void ShouldLoginInvalid() {
        Registration testUser = addUserLoginInvalid();
        $("[data-test-id=login] input").setValue(testUser.getLogin());
        $("[data-test-id=password] input").setValue(testUser.getPassword());
        $(".button__content").click();
        $("[data-test-id=error-notification]").shouldBe(visible).shouldHave(text("Неверно указан логин или пароль"));
    }

    @Test
    void ShouldPasswordInvalid() {
        Registration testUser = addUserPasswordInvalid();
        $("[data-test-id=login] input").setValue(testUser.getLogin());
        $("[data-test-id=password] input").setValue(testUser.getPassword());
        $(".button__content").click();
        $("[data-test-id=error-notification]").shouldBe(visible).shouldHave(text("Неверно указан логин или пароль"));
    }

    @Test
    void UserNotRegistered() {
        Registration testUser = addUserNotRegistered();
        $("[data-test-id=login] input").setValue(testUser.getLogin());
        $("[data-test-id=password] input").setValue(testUser.getPassword());
        $(".button__content").click();
        $("[data-test-id=error-notification]").shouldBe(visible).shouldHave(text("Неверно указан логин или пароль"));
    }
}