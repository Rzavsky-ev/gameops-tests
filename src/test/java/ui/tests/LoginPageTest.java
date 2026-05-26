package ui.tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import ui.base.BaseTest;
import ui.pages.LoginPage;
import ui.pages.MainPage;

import static com.codeborne.selenide.Condition.*;

@Epic("GameOps Platform")
@Feature("Страница входа")
@DisplayName("Тесты страницы входа")
@Tag("login")
public class LoginPageTest extends BaseTest {

    @Test
    @DisplayName("Поле Email должно быть видимо")
    @Description("Проверяет отображение поля ввода Email на странице логина")
    @Story("Форма входа")
    @Tag("smoke")
    void loginPageShouldHaveUsernameField() {
        LoginPage loginPage = new MainPage().clickLogin();
        Allure.step("Проверить, что поле Email видимо", () ->
                loginPage.getUsernameInput().shouldBe(visible)
        );
    }

    @Test
    @DisplayName("Поле Password должно быть видимо")
    @Description("Проверяет отображение поля ввода пароля на странице логина")
    @Story("Форма входа")
    @Tag("smoke")
    void loginPageShouldHavePasswordField() {
        LoginPage loginPage = new MainPage().clickLogin();
        Allure.step("Проверить, что поле Password видимо", () ->
                loginPage.getPasswordInput().shouldBe(visible)
        );
    }

    @Test
    @DisplayName("Кнопка Sign In должна быть видима и активна")
    @Description("Проверяет отображение и доступность кнопки входа")
    @Story("Форма входа")
    @Tag("smoke")
    void loginPageShouldHaveSignInButton() {
        LoginPage loginPage = new MainPage().clickLogin();
        Allure.step("Проверить, что кнопка Sign In видима и активна", () ->
                loginPage.getSignInButton().shouldBe(visible).shouldBe(enabled)
        );
    }

    @Test
    @DisplayName("Ссылка 'Forgot Password' должна быть видима")
    @Description("Проверяет отображение ссылки восстановления пароля")
    @Story("Форма входа")
    @Tag("smoke")
    void loginPageShouldHaveForgotPasswordLink() {
        LoginPage loginPage = new MainPage().clickLogin();
        Allure.step("Проверить, что ссылка 'Forgot Password' видима", () ->
                loginPage.getForgotPasswordLink().shouldBe(visible)
        );
    }

    @Test
    @DisplayName("Ссылка 'Register' должна быть видима")
    @Description("Проверяет отображение ссылки регистрации")
    @Story("Форма входа")
    @Tag("smoke")
    void loginPageShouldHaveRegisterLink() {
        LoginPage loginPage = new MainPage().clickLogin();
        Allure.step("Проверить, что ссылка 'Register' видима", () ->
                loginPage.getRegisterLink().shouldBe(visible)
        );
    }

    @Test
    @DisplayName("Пустые поля должны показывать ошибку валидации")
    @Description("Проверяет, что при нажатии Sign In с пустыми полями появляется ошибка")
    @Story("Валидация")
    @Tag("regression")
    void loginShouldShowErrorOnEmptyFields() {
        LoginPage loginPage = new MainPage().clickLogin();
        Allure.step("Нажать Sign In с пустыми полями", () -> loginPage.getSignInButton().click());
        Allure.step("Проверить, что поле Email стало невалидным", () ->
                loginPage.getUsernameInput().shouldHave(attribute("aria-invalid", "true"))
        );
    }

    @Test
    @DisplayName("Некорректный Email должен показывать ошибку валидации")
    @Description("Проверяет, что при вводе некорректного Email появляется ошибка")
    @Story("Валидация")
    @Tag("regression")
    void loginShouldShowErrorOnInvalidEmail() {
        LoginPage loginPage = new MainPage().clickLogin();
        Allure.step("Ввести некорректный Email", () ->
                loginPage.getUsernameInput().setValue("not-an-email")
        );
        Allure.step("Нажать Sign In", () -> loginPage.getSignInButton().click());
        Allure.step("Проверить ошибку валидации Email", () ->
                loginPage.getUsernameInput().shouldHave(attribute("aria-invalid", "true"))
        );
    }

    @Test
    @DisplayName("Вход с неверным паролем должен показывать ошибку")
    @Description("Проверяет сообщение об ошибке при неверном пароле")
    @Story("Авторизация")
    @Tag("regression")
    void loginWithInvalidPassword() {
        LoginPage loginPage = new MainPage().clickLogin();
        Allure.step("Ввести Email", () ->
                loginPage.getUsernameInput().setValue("test@example.com")
        );
        Allure.step("Ввести неверный пароль", () ->
                loginPage.getPasswordInput().setValue("WrongPassword123")
        );
        Allure.step("Нажать Sign In", () -> loginPage.getSignInButton().click());
        Allure.step("Проверить сообщение об ошибке", () ->
                loginPage.getErrorMessage().shouldBe(visible)
                        .shouldHave(text("Invalid username or password"))
        );
    }

    @Test
    @DisplayName("Вход с несуществующим Email должен показывать ошибку")
    @Description("Проверяет сообщение об ошибке при несуществующем пользователе")
    @Story("Авторизация")
    @Tag("regression")
    void loginWithNonExistentUser() {
        LoginPage loginPage = new MainPage().clickLogin();
        Allure.step("Ввести несуществующий Email", () ->
                loginPage.getUsernameInput().setValue("no-such-user@test.com")
        );
        Allure.step("Ввести пароль", () ->
                loginPage.getPasswordInput().setValue("SomePassword123")
        );
        Allure.step("Нажать Sign In", () -> loginPage.getSignInButton().click());
        Allure.step("Проверить сообщение об ошибке", () ->
                loginPage.getErrorMessage().shouldBe(visible)
                        .shouldHave(text("Invalid username or password"))
        );
    }

    @Test
    @DisplayName("Успешный вход с валидными учётными данными")
    @Description("Проверяет вход с правильным email и паролем")
    @Story("Авторизация")
    @Tag("regression")
    void loginWithValidCredentials() {
        LoginPage loginPage = new MainPage().clickLogin();
        Allure.step("Ввести Email", () ->
                loginPage.getUsernameInput().setValue("test@example.com")
        );
        Allure.step("Ввести пароль", () ->
                loginPage.getPasswordInput().setValue("GamePlatformTest")
        );
        Allure.step("Нажать Sign In", () -> loginPage.getSignInButton().click());
        Allure.step("Проверить, что вход выполнен (редирект на главную)", () ->
                new MainPage().getLogo().shouldBe(visible)
        );
    }
}