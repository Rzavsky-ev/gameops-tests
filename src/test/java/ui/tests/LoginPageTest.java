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
}