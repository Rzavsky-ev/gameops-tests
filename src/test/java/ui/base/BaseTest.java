package ui.base;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.Allure;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.io.ByteArrayInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import static com.codeborne.selenide.Selenide.*;


/**
 * Базовый класс для всех UI-тестов.
 */
public abstract class BaseTest {

    /**
     * Настраивает окружение один раз перед всеми тестами.
     * <p>
     * Устанавливает тип браузера, таймауты и проверяет наличие параметра удалённого запуска.
     * </p>
     */
    @BeforeAll
    public static void setUpAll() {
       // Configuration.browser = "firefox";
        Configuration.browser = "chrome";
        Configuration.baseUrl = "https://gameops-platform.dev";
        Configuration.browserSize = "1920x1200";
        Configuration.timeout = 15000;
        Configuration.pageLoadTimeout = 60000;

        String remote = System.getProperty("selenide.remote");
        if (remote != null) {
            Configuration.remote = remote;
        }
    }

    /**
     * Открывает главную страницу и устанавливает русский язык перед каждым тестом.
     * <p>
     * Выполняет JavaScript для записи значения {@code i18nextLng = "ru"} в {@code localStorage}
     * сразу после загрузки страницы, чтобы SPA подхватило русскую локализацию.
     * </p>
     */
    @BeforeEach
    void openBrowser() {
        open("/");
        executeJavaScript("localStorage.setItem('i18nextLng', 'ru');");
    }

    /**
     * Закрывает браузер после каждого теста.
     */
    @AfterEach
    protected void tearDown() {
        String path = screenshot("final");
        if (path != null) {
            try {
                String filePath = path.startsWith("file:") ? path.substring(5) : path;
                byte[] bytes = Files.readAllBytes(Paths.get(filePath));
                Allure.addAttachment("Скриншот после теста", "image/png",
                        new ByteArrayInputStream(bytes), "png");
            } catch (Exception e) {
                System.err.println("Не удалось прикрепить скриншот: " + e.getMessage());
            }
        }
        closeWebDriver();
    }
}