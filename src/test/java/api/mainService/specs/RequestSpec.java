package api.mainService.specs;

import exceptions.UtilityClassException;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

/**
 * Фабрика спецификаций запросов для API-тестов.
 */
public class RequestSpec {

    private static final String BASE_URL = "https://api.gameops-platform.dev";

    private RequestSpec() {
        throw new UtilityClassException(getClass());
    }

    /**
     * Создаёт базовую спецификацию для GET-запросов.
     *
     * @return спецификация запроса
     */
    public static RequestSpecification baseSpec() {
        return new RequestSpecBuilder()
                .setBaseUri(BASE_URL)
                .setAccept("application/json")
                .addQueryParam("language", "RU")
                .build();
    }

    /**
     * Создаёт спецификацию для запросов с JSON-телом.
     *
     * @return спецификация запроса с Content-Type: JSON
     */
    public static RequestSpecification jsonRequestSpec() {
        return new RequestSpecBuilder()
                .addRequestSpecification(baseSpec())
                .setContentType("application/json")
                .build();
    }
}