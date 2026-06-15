package api.mainService.utils;

import api.mainService.dto.heroCoachPublicController.HeroCoachForecastResponseDto;
import api.mainService.dto.heroCoachPublicController.HeroCoachPageResponseDto;
import api.mainService.dto.heroPublicController.HeroCardResponseDto;
import api.mainService.dto.heroPublicController.HeroLookupResponseDto;
import api.mainService.specs.RequestSpec;
import exceptions.UtilityClassException;
import io.qameta.allure.Allure;
import io.restassured.response.Response;

import java.util.List;

import static io.restassured.RestAssured.given;

/**
 * Хелпер для вызова API-эндпоинтов героев.
 */
public class HeroApiHelper {

    public static final String REQUEST_ATTACHMENT = "Request Body";
    public static final String RESPONSE_PREFIX = "Response ";
    public static final String RESPONSE_TYPE = "application/json";

    private HeroApiHelper() {
        throw new UtilityClassException(getClass());
    }

    /**
     * Получает список всех героев (id, slug, name).
     *
     * @return список DTO с краткой информацией о героях
     */
    public static List<HeroLookupResponseDto> fetchHeroNames() {
        return given()
                .spec(RequestSpec.baseSpec())
                .when()
                .get(ApiEndpoints.HEROES_NAMES)
                .then()
                .statusCode(200)
                .extract()
                .jsonPath()
                .getList(".", HeroLookupResponseDto.class);
    }

    /**
     * Выполняет пакетную загрузку героев по списку id.
     *
     * @param body       JSON-тело запроса
     * @param statusCode ожидаемый HTTP-статус ответа
     * @return список DTO с карточками героев
     */
    public static List<HeroCardResponseDto> fetchHeroesBatch(String body, int statusCode) {
        Allure.addAttachment(REQUEST_ATTACHMENT, RESPONSE_TYPE, body);

        Response response = given()
                .spec(RequestSpec.jsonRequestSpec())
                .body(body)
                .when()
                .post(ApiEndpoints.HEROES_BATCH)
                .then()
                .statusCode(statusCode)
                .extract()
                .response();

        Allure.addAttachment(RESPONSE_PREFIX + response.statusCode(),
                RESPONSE_TYPE,
                response.asPrettyString());

        return response.jsonPath().getList(".", HeroCardResponseDto.class);
    }

    /**
     * Получает список героев, доступных для тренера.
     *
     * @param page номер страницы
     * @param size размер страницы
     * @return DTO с пагинированным списком героев
     */
    public static HeroCoachPageResponseDto fetchHeroCoachPage(int page, int size) {
        Response response = given()
                .spec(RequestSpec.baseSpec())
                .queryParam("page", page)
                .queryParam("size", size)
                .when()
                .get(ApiEndpoints.HERO_COACH)
                .then()
                .statusCode(200)
                .extract()
                .response();

        Allure.addAttachment(RESPONSE_PREFIX + response.statusCode(),
                RESPONSE_TYPE, response.asPrettyString());

        return response.as(HeroCoachPageResponseDto.class);
    }

    /**
     * Получает прогноз тренера героев с указанием даты предыдущего события.
     *
     * @param targetDate        целевая дата прогноза (обязательно)
     * @param previousEventDate дата предыдущего события (опционально)
     * @return DTO с прогнозом
     */
    public static HeroCoachForecastResponseDto fetchHeroCoachForecast(String targetDate, String previousEventDate) {
        Response response = given()
                .spec(RequestSpec.baseSpec())
                .queryParam("targetDate", targetDate)
                .queryParam("previousEventDate", previousEventDate)
                .when()
                .get(ApiEndpoints.HERO_COACH_FORECAST)
                .then()
                .statusCode(200)
                .extract()
                .response();

        Allure.addAttachment(RESPONSE_PREFIX + response.statusCode(),
                RESPONSE_TYPE, response.asPrettyString());

        return response.as(HeroCoachForecastResponseDto.class);
    }

    /**
     * Получает прогноз тренера героев без даты предыдущего события.
     *
     * @param targetDate целевая дата прогноза
     * @return DTO с прогнозом
     */
    public static HeroCoachForecastResponseDto fetchHeroCoachForecast(String targetDate) {
        return fetchHeroCoachForecast(targetDate, null);
    }
}