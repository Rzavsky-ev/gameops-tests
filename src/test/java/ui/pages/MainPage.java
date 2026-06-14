package ui.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Allure;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

/**
 * Page Object главной страницы GameOps Platform.
 */
public class MainPage {

    // --- Header ---
    private final SelenideElement logo = $("a[aria-label='GameOps home']");
    private final SelenideElement burgerMenu = $("button svg.lucide-menu").parent();
    private final SelenideElement buttonAbout = $("button svg.lucide-info").parent();
    private final SelenideElement buttonTheme = $("button[title='Тема']");
    private final SelenideElement buttonLanguage = $("button svg.lucide-globe").parent();
    private final SelenideElement buttonLogin = $("button svg.lucide-user").parent();

    // --- Burger menu items ---
    private final SelenideElement burgerHome = $("a[href='/']");
    private final SelenideElement burgerHeroes = $("a[href='/heroes']");

    // --- About modal ---
    private final SelenideElement aboutModal = $("div.fixed.inset-0.z-\\[70\\]");
    private final SelenideElement aboutModalTitle = aboutModal.$("h2");
    private final SelenideElement aboutModalClose = $("button[aria-label='Закрыть']");

    // --- Theme dropdown ---
    private final SelenideElement themeLight = $("button svg.lucide-sun").parent();
    private final SelenideElement themeDark = $("button svg.lucide-moon").parent();

    // --- Language dropdown ---
    private final SelenideElement langRussian = $(byText("Русский")).closest("button");
    private final SelenideElement langEnglish = $(byText("Английский")).closest("button");

    // --- Hero ---
    private final SelenideElement empiresImg = $("img[alt='Empires']");
    private final SelenideElement ampersandImg = $("img[alt='&']");
    private final SelenideElement puzzlesImg = $("img[alt='Puzzles']");
    private final SelenideElement subheading = $("p.text-lg");

    // --- Quick links ---
    private final SelenideElement heroesLink = $("a[href='/heroes']");
    private final SelenideElement eventsLink = $("a[href='/events']");
    private final SelenideElement chestsLink = $("a[href='/chests']");
    private final SelenideElement allianceLink = $("a[href='/alliance']");

    // --- Publications ---
    private final SelenideElement publicationsTitle = $("h2");
    private final ElementsCollection articles = $$("article");

    // Filters — порядок фиксирован: Новости, События, Расписание, Гайды, Промокоды
    private final SelenideElement filterNews = $$("section button").get(0);
    private final SelenideElement filterEvents = $$("section button").get(1);
    private final SelenideElement filterSchedule = $$("section button").get(2);
    private final SelenideElement filterGuides = $$("section button").get(3);
    private final SelenideElement filterPromocodes = $$("section button").get(4);

    // --- Header getters ---

    public SelenideElement getLogo() {
        return logo;
    }

    public SelenideElement getBurgerMenu() {
        return burgerMenu;
    }

    public SelenideElement getButtonAbout() {
        return buttonAbout;
    }

    public SelenideElement getButtonTheme() {
        return buttonTheme;
    }

    public SelenideElement getButtonLanguage() {
        return buttonLanguage;
    }

    public SelenideElement getButtonLogin() {
        return buttonLogin;
    }

    // --- Burger menu getters ---

    public SelenideElement getBurgerHome() {
        return burgerHome;
    }

    public SelenideElement getBurgerHeroes() {
        return burgerHeroes;
    }

    // --- About modal getters ---

    public SelenideElement getAboutModal() {
        return aboutModal;
    }

    public SelenideElement getAboutModalTitle() {
        return aboutModalTitle;
    }

    public SelenideElement getAboutModalClose() {
        return aboutModalClose;
    }

    // --- Theme dropdown getters ---

    public SelenideElement getThemeLight() {
        return themeLight;
    }

    public SelenideElement getThemeDark() {
        return themeDark;
    }

    /**
     * @return true, если активна тёмная тема (есть галочка на кнопке «Темная»)
     */
    public boolean isDarkThemeActive() {
        return themeDark.exists();
    }

    // --- Language dropdown getters ---

    public SelenideElement getLangRussian() {
        return langRussian;
    }

    public SelenideElement getLangEnglish() {
        return langEnglish;
    }

    /**
     * @return true, если активен русский язык (есть галочка на кнопке «Русский»)
     */
    public boolean isRussianActive() {
        return langRussian.$("svg.lucide-check").exists();
    }

    // --- Actions ---

    /**
     * Кликает по кнопке «Войти» и возвращает страницу логина.
     *
     * @return {@link LoginPage}
     */
    public LoginPage clickLogin() {
        Allure.step("Нажать кнопку 'Войти'", () -> buttonLogin.click());
        return new LoginPage();
    }
    // --- Hero getters ---

    public SelenideElement getEmpiresImg() {
        return empiresImg;
    }

    public SelenideElement getAmpersandImg() {
        return ampersandImg;
    }

    public SelenideElement getPuzzlesImg() {
        return puzzlesImg;
    }

    public SelenideElement getSubheading() {
        return subheading;
    }

    // --- Quick links getters ---

    public SelenideElement getHeroesLink() {
        return heroesLink;
    }

    public SelenideElement getEventsLink() {
        return eventsLink;
    }

    public SelenideElement getChestsLink() {
        return chestsLink;
    }

    public SelenideElement getAllianceLink() {
        return allianceLink;
    }

    // --- Publications getters ---

    public SelenideElement getPublicationsTitle() {
        return publicationsTitle;
    }

    public SelenideElement getFilterNews() {
        return filterNews;
    }

    public SelenideElement getFilterEvents() {
        return filterEvents;
    }

    public SelenideElement getFilterSchedule() {
        return filterSchedule;
    }

    public SelenideElement getFilterGuides() {
        return filterGuides;
    }

    public SelenideElement getFilterPromocodes() {
        return filterPromocodes;
    }

    public ElementsCollection getArticles() {
        return articles;
    }
}