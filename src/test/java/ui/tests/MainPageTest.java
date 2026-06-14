package ui.tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import ui.base.BaseTest;
import ui.pages.LoginPage;
import ui.pages.MainPage;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.*;

@Epic("GameOps Platform")
@Feature("Главная страница")
@DisplayName("Тесты главной страницы")
public class MainPageTest extends BaseTest {

    // --- Header ---

    @Test
    @DisplayName("Логотип должен быть видим")
    @Description("Проверяет, что логотип GameOps отображается в хедере")
    @Story("Хедер")
    @Tag("smoke")
    void isLogoVisible() {
        Allure.step("Проверить, что логотип видим", () ->
                new MainPage().getLogo().shouldBe(visible)
        );
    }

    @Test
    @DisplayName("Бургер-меню должно быть видимо")
    @Description("Проверяет видимость иконки бургер-меню")
    @Story("Бургер-меню")
    @Tag("smoke")
    void burgerMenuShouldBeVisible() {
        Allure.step("Проверить, что бургер-меню видимо", () ->
                new MainPage().getBurgerMenu().shouldBe(visible)
        );
    }

    @Test
    @DisplayName("Бургер-меню должно быть активно")
    @Description("Проверяет, что бургер-меню доступно для клика")
    @Story("Бургер-меню")
    @Tag("smoke")
    void burgerMenuShouldBeEnabled() {
        Allure.step("Проверить, что бургер-меню активно", () ->
                new MainPage().getBurgerMenu().shouldBe(enabled)
        );
    }

    @Test
    @DisplayName("Бургер-меню должно открываться")
    @Description("Проверяет, что при клике на бургер появляются пункты меню")
    @Story("Бургер-меню")
    @Tag("regression")
    void burgerMenuShouldOpen() {
        MainPage page = new MainPage();
        Allure.step("Открыть бургер-меню", () -> page.getBurgerMenu().click());
        Allure.step("Проверить, что пункт 'Главная' видим", () ->
                page.getBurgerHome().shouldBe(visible));
        Allure.step("Проверить, что пункт 'Герои' видим", () ->
                page.getBurgerHeroes().shouldBe(visible));
    }

    @Test
    @DisplayName("Пункт 'Главная' должен вести на главную")
    @Description("Проверяет навигацию через бургер-меню на главную страницу")
    @Story("Бургер-меню")
    @Tag("regression")
    void burgerHomeShouldNavigate() {
        MainPage mainPage = new MainPage();
        Allure.step("Открыть бургер-меню", () -> mainPage.getBurgerMenu().click());
        Allure.step("Нажать 'Главная'", () -> mainPage.getBurgerHome().click());
        Allure.step("Проверить заголовок главной", () -> {
            mainPage.getEmpiresImg().shouldBe(visible);
            mainPage.getAmpersandImg().shouldBe(visible);
            mainPage.getPuzzlesImg().shouldBe(visible);
        });
    }

    // --- About ---

    @Test
    @DisplayName("Кнопка 'О проекте' должна быть видима")
    @Description("Проверяет видимость кнопки 'О проекте' в хедере")
    @Story("Хедер")
    @Tag("smoke")
    void aboutButtonShouldBeVisible() {
        Allure.step("Проверить, что кнопка 'О проекте' видима", () ->
                new MainPage().getButtonAbout().shouldBe(visible)
        );
    }

    @Test
    @DisplayName("Кнопка 'О проекте' должна быть активна")
    @Description("Проверяет, что кнопка 'О проекте' доступна для клика")
    @Story("Хедер")
    @Tag("smoke")
    void aboutButtonShouldBeEnabled() {
        Allure.step("Проверить, что кнопка 'О проекте' активна", () ->
                new MainPage().getButtonAbout().shouldBe(enabled)
        );
    }

    @Test
    @DisplayName("Кнопка 'О проекте' должна открывать модальное окно")
    @Description("Проверяет открытие и закрытие модального окна с информацией о проекте")
    @Story("О проекте")
    @Tag("regression")
    void aboutButtonShouldOpenInfo() {
        MainPage page = new MainPage();
        Allure.step("Нажать кнопку 'О проекте'", () -> page.getButtonAbout().click());
        Allure.step("Проверить, что модальное окно открылось", () ->
                page.getAboutModal().shouldBe(visible));
        Allure.step("Проверить заголовок модального окна", () ->
                page.getAboutModalTitle().shouldHave(text("О проекте")));
        Allure.step("Закрыть модальное окно", () -> page.getAboutModalClose().click());
        Allure.step("Проверить, что модальное окно закрылось", () ->
                page.getAboutModal().shouldNotBe(visible));
    }

    // --- Theme ---

    @Test
    @DisplayName("Кнопка 'Тема' должна быть видима")
    @Description("Проверяет видимость кнопки переключения темы")
    @Story("Тема")
    @Tag("smoke")
    void themeButtonShouldBeVisible() {
        Allure.step("Проверить, что кнопка 'Тема' видима", () ->
                new MainPage().getButtonTheme().shouldBe(visible)
        );
    }

    @Test
    @DisplayName("Кнопка 'Тема' должна быть активна")
    @Description("Проверяет, что кнопка переключения темы доступна для клика")
    @Story("Тема")
    @Tag("smoke")
    void themeButtonShouldBeEnabled() {
        Allure.step("Проверить, что кнопка 'Тема' активна", () ->
                new MainPage().getButtonTheme().shouldBe(enabled)
        );
    }

    @Test
    @DisplayName("Переключение темы должно работать")
    @Story("Тема")
    @Tag("regression")
    void themeButtonShouldToggleTheme() {
        MainPage page = new MainPage();
        Allure.step("Нажать кнопку 'Тема' для открытия меню", () ->
                page.getButtonTheme().click());

        if (page.isDarkThemeActive()) {
            Allure.step("Переключить на светлую тему", () -> page.getThemeLight().click());
            Allure.step("Проверить, что иконка сменилась на солнце", () ->
                    page.getThemeLight().shouldBe(visible)
            );
        } else {
            Allure.step("Переключить на тёмную тему", () -> page.getThemeDark().click());
            Allure.step("Проверить, что иконка сменилась на луну", () ->
                    page.getThemeDark().shouldBe(visible)
            );
        }
    }

    // --- Language ---

    @Test
    @DisplayName("Кнопка 'Язык' должна быть видима")
    @Description("Проверяет видимость кнопки переключения языка")
    @Story("Язык")
    @Tag("smoke")
    void languageButtonShouldBeVisible() {
        Allure.step("Проверить, что кнопка 'Язык' видима", () ->
                new MainPage().getButtonLanguage().shouldBe(visible)
        );
    }

    @Test
    @DisplayName("Кнопка 'Язык' должна быть активна")
    @Description("Проверяет, что кнопка переключения языка доступна для клика")
    @Story("Язык")
    @Tag("smoke")
    void languageButtonShouldBeEnabled() {
        Allure.step("Проверить, что кнопка 'Язык' активна", () ->
                new MainPage().getButtonLanguage().shouldBe(enabled)
        );
    }

    @Test
    @DisplayName("Меню языка должно открываться и переключать язык")
    @Description("Проверяет открытие меню выбора языка и переключение между русским и английским")
    @Story("Язык")
    @Tag("regression")
    void languageMenuShouldOpenAndSwitchLanguage() {
        MainPage page = new MainPage();
        Allure.step("Нажать кнопку 'Язык'", () -> page.getButtonLanguage().click());
        Allure.step("Проверить пункты меню", () -> {
            page.getLangRussian().shouldBe(visible);
            page.getLangEnglish().shouldBe(visible);
        });

        if (page.isRussianActive()) {
            Allure.step("Переключить на английский", () -> page.getLangEnglish().click());
            Allure.step("Проверить, что кнопка теперь 'EN'", () ->
                    page.getButtonLanguage().shouldHave(text("EN")));
        } else {
            Allure.step("Переключить на русский", () -> page.getLangRussian().click());
            Allure.step("Проверить, что кнопка теперь 'RU'", () ->
                    page.getButtonLanguage().shouldHave(text("RU")));
        }
    }

    // --- Login ---

    @Test
    @DisplayName("Кнопка 'Войти' должна быть видима")
    @Description("Проверяет видимость кнопки входа в хедере")
    @Story("Авторизация")
    @Tag("smoke")
    void loginButtonShouldBeVisible() {
        Allure.step("Проверить, что кнопка 'Войти' видима", () ->
                new MainPage().getButtonLogin().shouldBe(visible)
        );
    }

    @Test
    @DisplayName("Кнопка 'Войти' должна быть активна")
    @Description("Проверяет, что кнопка входа доступна для клика")
    @Story("Авторизация")
    @Tag("smoke")
    void loginButtonShouldBeEnabled() {
        Allure.step("Проверить, что кнопка 'Войти' активна", () ->
                new MainPage().getButtonLogin().shouldBe(enabled)
        );
    }

    @Test
    @DisplayName("Кнопка 'Войти' должна вести на страницу логина")
    @Description("Проверяет переход на Keycloak страницу входа")
    @Story("Авторизация")
    @Tag("regression")
    void loginButtonShouldNavigateToLogin() {
        MainPage page = new MainPage();
        Allure.step("Нажать кнопку 'Войти'", () -> page.getButtonLogin().click());
        Allure.step("Проверить, что открылась страница входа", () -> {
            LoginPage loginPage = new LoginPage();
            loginPage.getPageTitle().shouldBe(visible);
            loginPage.getPageTitle().shouldHave(text("Sign in to your account"));
        });
    }

    // --- Hero ---

    @Test
    @DisplayName("Главный заголовок должен быть видим")
    @Description("Проверяет отображение заголовка 'EMPIRES & PUZZLES'")
    @Story("Hero-секция")
    @Tag("smoke")
    void headingShouldBeVisible() {
        Allure.step("Проверить заголовок", () -> {
            MainPage mainPage = new MainPage();
            mainPage.getEmpiresImg().shouldBe(visible);
            mainPage.getAmpersandImg().shouldBe(visible);
            mainPage.getPuzzlesImg().shouldBe(visible);
        });
    }

    @Test
    @DisplayName("Подзаголовок должен быть видим")
    @Description("Проверяет отображение подзаголовка 'Фан-сообщество игры'")
    @Story("Hero-секция")
    @Tag("smoke")
    void subheadingShouldBeVisible() {
        Allure.step("Проверить подзаголовок", () ->
                new MainPage().getSubheading().shouldHave(text("Фан-сообщество игры"))
        );
    }

    // --- Quick links ---

    @Test
    @DisplayName("Быстрые ссылки должны быть видны")
    @Description("Проверяет видимость всех быстрых ссылок: Герои, События, Сундуки, Альянсы, Совместные закупки")
    @Story("Навигация")
    @Tag("smoke")
    void quickLinksShouldBeVisible() {
        MainPage page = new MainPage();
        Allure.step("Проверить быстрые ссылки", () -> {
            page.getHeroesLink().shouldBe(visible);
            page.getEventsLink().shouldBe(visible);
            page.getChestsLink().shouldBe(visible);
            page.getAllianceLink().shouldBe(visible);
        });
    }

    // --- Publications ---

    @Test
    @DisplayName("Заголовок 'Публикации' должен быть видим")
    @Description("Проверяет отображение заголовка раздела публикаций")
    @Story("Публикации")
    @Tag("smoke")
    void publicationsTitleShouldBeVisible() {
        Allure.step("Проверить заголовок 'Публикации'", () ->
                new MainPage().getPublicationsTitle().shouldHave(text("Публикации"))
        );
    }

    @Test
    @DisplayName("Фильтры публикаций должны быть видны")
    @Description("Проверяет видимость всех фильтров: Новости, События, Расписание, Гайды, Промокоды")
    @Story("Публикации")
    @Tag("smoke")
    void filtersShouldBeVisible() {
        MainPage page = new MainPage();
        Allure.step("Проверить фильтры", () -> {
            page.getFilterNews().shouldBe(visible);
            page.getFilterEvents().shouldBe(visible);
            page.getFilterSchedule().shouldBe(visible);
            page.getFilterGuides().shouldBe(visible);
            page.getFilterPromocodes().shouldBe(visible);
        });
    }

    @Test
    @DisplayName("Должна быть хотя бы одна статья")
    @Description("Проверяет, что список публикаций не пуст")
    @Story("Публикации")
    @Tag("smoke")
    void articlesShouldBePresent() {
        Allure.step("Проверить, что есть хотя бы одна статья", () ->
                new MainPage().getArticles().shouldHave(sizeGreaterThan(0))
        );
    }
}