package api.mainService.utils;

import api.mainService.dto.HeroCardResponseDto;
import api.mainService.dto.HeroLookupResponseDto;
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

    private static final String REQUEST_ATTACHMENT = "Request Body";
    private static final String RESPONSE_PREFIX = "Response ";
    private static final String RESPONSE_TYPE = "application/json";

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
}