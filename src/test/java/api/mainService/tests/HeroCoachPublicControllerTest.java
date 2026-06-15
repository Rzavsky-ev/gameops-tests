package api.mainService.tests;

import api.mainService.dto.heroCoachPublicController.HeroCoachForecastResponseDto;
import api.mainService.dto.heroCoachPublicController.HeroCoachHeroResponseDto;
import api.mainService.dto.heroCoachPublicController.HeroCoachPageResponseDto;
import api.mainService.specs.RequestSpec;
import api.mainService.utils.ApiEndpoints;
import api.mainService.utils.HeroApiHelper;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;


@Epic("GameOps Platform")
@Feature("Hero Coach API")
@DisplayName("API-тесты публичных эндпоинтов тренера героев")
public class HeroCoachPublicControllerTest {

    @Test
    @DisplayName("GET /hero-coach должен вернуть 200 и список героев")
    @Description("Проверяет получение списка героев для тренера с пагинацией")
    @Story("Тренер героев")
    @Tag("smoke")
    void getHeroCoachShouldReturn200() {
        Allure.step("Получить список героев для тренера", () -> {
            HeroCoachPageResponseDto page = HeroApiHelper.fetchHeroCoachPage(0, 5);

            Allure.step("Проверить пагинацию", () -> {
                assertThat(page.items()).isNotEmpty();
                assertThat(page.totalElements()).isPositive();
            });

            Allure.step("Проверить первого героя", () -> {
                HeroCoachHeroResponseDto first = page.items().get(0);
                assertThat(first.id()).isNotNull();
                assertThat(first.name()).isNotNull();
                assertThat(first.heroCoachDate()).isNotNull();
            });
        });
    }

    @Test
    @DisplayName("GET /hero-coach с отрицательной страницей должен вернуть 200")
    @Description("Проверяет обработку отрицательного значения page")
    @Story("Тренер героев")
    @Tag("regression")
    void getHeroCoachNegativePageShouldReturn200() {
        Allure.step("Запросить героев с page=-1", () -> {
            HeroApiHelper.fetchHeroCoachPage(-1, 5);
        });
    }

    @Test
    @DisplayName("GET /hero-coach с отрицательным размером должен вернуть 200")
    @Description("Проверяет обработку отрицательного значения size")
    @Story("Тренер героев")
    @Tag("regression")
    void getHeroCoachNegativeSizeShouldReturn200() {
        Allure.step("Запросить героев с size=-5", () -> {
            HeroApiHelper.fetchHeroCoachPage(0, -5);
        });
    }

    @Test
    @DisplayName("GET /hero-coach с размером 0 должен вернуть 200")
    @Description("Проверяет обработку нулевого размера страницы")
    @Story("Тренер героев")
    @Tag("regression")
    void getHeroCoachSizeZeroShouldReturn200() {
        Allure.step("Запросить героев с size = 0", () -> {
            HeroApiHelper.fetchHeroCoachPage(0, 0);
        });
    }

    @Test
    @DisplayName("GET /hero-coach/forecast должен вернуть 200")
    @Description("Проверяет получение прогноза тренера героев на заданную дату")
    @Story("Тренер героев — прогноз")
    @Tag("smoke")
    void getHeroCoachForecastShouldReturn200() {
        Allure.step("Получить прогноз на будущую дату", () -> {
            String targetDate = "2027-01-01";
            HeroCoachForecastResponseDto forecast =
                    HeroApiHelper.fetchHeroCoachForecast(targetDate);

            Allure.step("Проверить поля прогноза", () -> {
                assertThat(forecast.targetDate()).isNotNull();
                assertThat(forecast.newlyAvailableHeroes()).isNotNull();
            });
        });
    }

    @Test
    @DisplayName("GET /hero-coach/forecast без targetDate должен вернуть 400")
    @Description("Проверяет, что обязательный параметр targetDate вызывает ошибку")
    @Story("Тренер героев — прогноз")
    @Tag("regression")
    void getHeroCoachForecastWithoutTargetDateShouldReturn400() {
        Allure.step("Запросить прогноз без даты", () -> {
            Response response = given()
                    .spec(RequestSpec.baseSpec())
                    .when()
                    .get(ApiEndpoints.HERO_COACH_FORECAST)
                    .then()
                    .statusCode(400)
                    .extract()
                    .response();

            Allure.addAttachment(HeroApiHelper.RESPONSE_PREFIX + response.statusCode(),
                    HeroApiHelper.RESPONSE_TYPE, response.asPrettyString());
        });
    }

    @Test
    @DisplayName("GET /hero-coach/forecast с previousEventDate должен вернуть 200")
    @Description("Проверяет прогноз с указанием даты предыдущего события")
    @Story("Тренер героев — прогноз")
    @Tag("regression")
    void getHeroCoachForecastWithPreviousDateShouldReturn200() {
        Allure.step("Получить прогноз с previousEventDate", () -> {
            String targetDate = "2027-01-01";
            String previousEventDate = "2026-12-25";
            HeroApiHelper.fetchHeroCoachForecast(targetDate, previousEventDate);
        });
    }
}
