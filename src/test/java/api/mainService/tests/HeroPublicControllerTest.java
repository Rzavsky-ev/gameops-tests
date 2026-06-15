package api.mainService.tests;

import api.mainService.dto.heroPublicController.*;
import api.mainService.specs.RequestSpec;
import api.mainService.utils.ApiEndpoints;
import api.mainService.utils.HeroApiHelper;
import api.mainService.utils.HeroBatchBody;
import api.mainService.utils.HeroStatsBody;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.List;

import static api.mainService.utils.ApiEndpoints.HEROES_BASE;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

@Epic("GameOps Platform")
@Feature("Public Heroes API")
@DisplayName("API-тесты публичных эндпоинтов героев")
public class HeroPublicControllerTest {

    // --- GET /heroes/names ---

    @Test
    @DisplayName("GET /heroes/names должен вернуть 200 и список имён героев")
    @Description("Проверяет получение списка имён героев с дефолтным языком")
    @Story("Имена героев")
    @Tag("smoke")
    void getNamesShouldReturn200() {
        Allure.step("Получить список имён героев", () -> {
            List<HeroLookupResponseDto> heroes = HeroApiHelper.fetchHeroNames();

            Allure.step("Проверить, что список не пустой", () ->
                    assertThat(heroes).isNotEmpty()
            );
            Allure.step("Проверить поля первого героя", () -> {
                HeroLookupResponseDto first = heroes.get(0);
                assertThat(first.id()).isNotNull();
                assertThat(first.name()).isNotNull();
                assertThat(first.slug()).isNotNull();
            });
        });
    }

    // --- POST /heroes/batch ---

    @Test
    @DisplayName("POST /heroes/batch должен вернуть героев по id")
    @Description("Проверяет получение списка героев по массиву id")
    @Story("Пакетная загрузка героев")
    @Tag("smoke")
    void getHeroesBatchShouldReturnHeroes() {
        Allure.step("Запросить героев по списку id", () -> {
            List<HeroCardResponseDto> heroes =
                    HeroApiHelper.fetchHeroesBatch(HeroBatchBody.VALID_IDS, 200);

            Allure.step("Проверить, что список не пустой", () ->
                    assertThat(heroes).isNotEmpty()
            );
            Allure.step("Проверить поля первого героя", () -> {
                HeroCardResponseDto first = heroes.get(0);
                assertThat(first.id()).isNotNull();
                assertThat(first.name()).isNotNull();
                assertThat(first.slug()).isNotNull();
            });
        });
    }

    @Test
    @DisplayName("POST /heroes/batch с includeDrafts должен вернуть 200")
    @Description("Проверяет, что параметр includeDrafts=true не ломает запрос")
    @Story("Пакетная загрузка героев")
    @Tag("regression")
    void getHeroesBatchWithDraftsShouldReturn200() {
        Allure.step("Запросить героев с includeDrafts=true", () -> {
            HeroApiHelper.fetchHeroesBatch(HeroBatchBody.SINGLE_ID, 200);
        });
    }

    @Test
    @DisplayName("POST /heroes/batch с пустым телом должен вернуть 400")
    @Description("Проверяет валидацию пустого тела запроса")
    @Story("Пакетная загрузка героев")
    @Tag("regression")
    void getHeroesBatchEmptyBodyShouldReturn400() {
        Allure.step("Отправить запрос без тела", () -> {
            Allure.addAttachment(HeroApiHelper.REQUEST_ATTACHMENT, HeroApiHelper.RESPONSE_TYPE, HeroBatchBody.EMPTY_BODY);

            Response response = given()
                    .spec(RequestSpec.jsonRequestSpec())
                    .body(HeroBatchBody.EMPTY_BODY)
                    .when()
                    .post(ApiEndpoints.HEROES_BATCH)
                    .then()
                    .statusCode(400)
                    .extract()
                    .response();

            Allure.addAttachment(HeroApiHelper.RESPONSE_PREFIX + response.statusCode(),
                    HeroApiHelper.RESPONSE_TYPE, response.asPrettyString());
        });
    }

    @Test
    @DisplayName("POST /heroes/batch без Content-Type должен вернуть 415")
    @Description("Проверяет, что запрос без Content-Type возвращает 415 Unsupported Media Type")
    @Story("Пакетная загрузка героев")
    @Tag("regression")
    void getHeroesBatchWithoutContentTypeShouldReturn415() {
        Allure.step("Отправить запрос без Content-Type", () -> {
            Allure.addAttachment(HeroApiHelper.REQUEST_ATTACHMENT,
                    HeroApiHelper.RESPONSE_TYPE, HeroBatchBody.SINGLE_ID);

            Response response = given()
                    .spec(RequestSpec.baseSpec())
                    .body(HeroBatchBody.SINGLE_ID)
                    .when()
                    .post(ApiEndpoints.HEROES_BATCH)
                    .then()
                    .statusCode(415)
                    .extract()
                    .response();

            Allure.addAttachment(HeroApiHelper.RESPONSE_PREFIX + response.statusCode(),
                    HeroApiHelper.RESPONSE_TYPE, response.asPrettyString());
        });
    }

    @Test
    @DisplayName("POST /heroes/batch с несуществующими id должен вернуть пустой список")
    @Description("Проверяет, что запрос с несуществующими id возвращает пустой массив")
    @Story("Пакетная загрузка героев")
    @Tag("regression")
    void getHeroesBatchNonExistentShouldReturnEmpty() {
        Allure.step("Запросить героев с несуществующими id", () -> {
            List<HeroCardResponseDto> heroes =
                    HeroApiHelper.fetchHeroesBatch(HeroBatchBody.NON_EXISTENT_IDS, 200);

            Allure.step("Проверить, что список пустой", () ->
                    assertThat(heroes).isEmpty()
            );
        });
    }

    @Test
    @DisplayName("POST /heroes/batch с пустым списком id должен вернуть 400")
    @Description("Проверяет валидацию пустого массива heroIds")
    @Story("Пакетная загрузка героев")
    @Tag("regression")
    void getHeroesBatchEmptyIdsShouldReturn400() {
        Allure.step("Отправить запрос с пустым списком heroIds", () -> {
            Allure.addAttachment(HeroApiHelper.REQUEST_ATTACHMENT,
                    HeroApiHelper.RESPONSE_TYPE, HeroBatchBody.EMPTY_IDS);

            Response response = given()
                    .spec(RequestSpec.jsonRequestSpec())
                    .body(HeroBatchBody.EMPTY_IDS)
                    .when()
                    .post(ApiEndpoints.HEROES_BATCH)
                    .then()
                    .statusCode(400)
                    .extract()
                    .response();

            Allure.addAttachment(HeroApiHelper.RESPONSE_PREFIX + response.statusCode(),
                    HeroApiHelper.RESPONSE_TYPE, response.asPrettyString());
        });
    }

    // --- GET /heroes/search ---

    @Test
    @DisplayName("GET /heroes/search должен вернуть 200 и результаты")
    @Description("Проверяет поиск героев по имени из базы")
    @Story("Поиск героев")
    @Tag("smoke")
    void searchShouldReturn200() {
        Allure.step("Поиск героев по запросу", () -> {
            List<HeroLookupResponseDto> names = HeroApiHelper.fetchHeroNames();
            assertThat(names).isNotEmpty();
            String realName = names.get(0).name();

            Response response = given()
                    .spec(RequestSpec.baseSpec())
                    .queryParam("query", realName)
                    .when()
                    .get(ApiEndpoints.HEROES_SEARCH)
                    .then()
                    .statusCode(200)
                    .extract()
                    .response();

            Allure.addAttachment(HeroApiHelper.RESPONSE_PREFIX + response.statusCode(),
                    HeroApiHelper.RESPONSE_TYPE, response.asPrettyString());

            List<HeroLookupResponseDto> heroes = response.jsonPath().getList(".", HeroLookupResponseDto.class);

            Allure.step("Проверить, что результаты есть", () ->
                    assertThat(heroes).isNotEmpty()
            );
            Allure.step("Проверить поля первого результата", () -> {
                HeroLookupResponseDto first = heroes.get(0);
                assertThat(first.id()).isNotNull();
                assertThat(first.name()).isNotNull();
                assertThat(first.slug()).isNotNull();
            });
        });
    }

    @Test
    @DisplayName("GET /heroes/search с лимитом должен вернуть не более указанного количества")
    @Description("Проверяет, что параметр limit ограничивает количество результатов")
    @Story("Поиск героев")
    @Tag("regression")
    void searchWithLimitShouldReturnLimitedResults() {
        int limit = 3;

        Allure.step("Поиск героев с лимитом " + limit, () -> {
            Response response = given()
                    .spec(RequestSpec.baseSpec())
                    .queryParam("query", "a")
                    .queryParam("limit", limit)
                    .when()
                    .get(ApiEndpoints.HEROES_SEARCH)
                    .then()
                    .statusCode(200)
                    .extract()
                    .response();

            Allure.addAttachment(HeroApiHelper.RESPONSE_PREFIX + response.statusCode(),
                    HeroApiHelper.RESPONSE_TYPE, response.asPrettyString());

            List<HeroLookupResponseDto> heroes = response.jsonPath().getList(".", HeroLookupResponseDto.class);

            Allure.step("Проверить, что количество результатов ≤ " + limit, () ->
                    assertThat(heroes).hasSizeLessThanOrEqualTo(limit)
            );
        });
    }

    @Test
    @DisplayName("GET /heroes/search с английским языком должен вернуть 200")
    @Description("Проверяет поиск с параметром language=EN")
    @Story("Поиск героев")
    @Tag("regression")
    void searchWithEnglishShouldReturn200() {
        Allure.step("Поиск героев на английском", () -> {
            Response response = given()
                    .spec(RequestSpec.baseSpec())
                    .queryParam("query", "dragon")
                    .queryParam("language", "EN")
                    .when()
                    .get(ApiEndpoints.HEROES_SEARCH)
                    .then()
                    .statusCode(200)
                    .extract()
                    .response();

            Allure.addAttachment(HeroApiHelper.RESPONSE_PREFIX + response.statusCode(),
                    HeroApiHelper.RESPONSE_TYPE, response.asPrettyString());
        });
    }

    @Test
    @DisplayName("GET /heroes/search без query должен вернуть 400")
    @Description("Проверяет, что отсутствие обязательного параметра query возвращает 400")
    @Story("Поиск героев")
    @Tag("regression")
    void searchWithoutQueryShouldReturn400() {
        Allure.step("Поиск без параметра query", () -> {
            Response response = given()
                    .spec(RequestSpec.baseSpec())
                    .when()
                    .get(ApiEndpoints.HEROES_SEARCH)
                    .then()
                    .statusCode(400)
                    .extract()
                    .response();

            Allure.addAttachment(HeroApiHelper.RESPONSE_PREFIX + response.statusCode(),
                    HeroApiHelper.RESPONSE_TYPE, response.asPrettyString());
        });
    }

    @Test
    @DisplayName("GET /heroes/search с несуществующим словом должен вернуть пустой список")
    @Description("Проверяет, что поиск несуществующего героя возвращает пустой массив")
    @Story("Поиск героев")
    @Tag("regression")
    void searchNonExistentShouldReturnEmpty() {
        Allure.step("Поиск несуществующего героя", () -> {
            Response response = given()
                    .spec(RequestSpec.baseSpec())
                    .queryParam("query", "xyzxyzxyz")
                    .when()
                    .get(ApiEndpoints.HEROES_SEARCH)
                    .then()
                    .statusCode(200)
                    .extract()
                    .response();

            Allure.addAttachment(HeroApiHelper.RESPONSE_PREFIX + response.statusCode(),
                    HeroApiHelper.RESPONSE_TYPE, response.asPrettyString());

            List<HeroLookupResponseDto> heroes = response.jsonPath().getList(".", HeroLookupResponseDto.class);

            Allure.step("Проверить, что список пустой", () ->
                    assertThat(heroes).isEmpty()
            );
        });
    }

    // --- GET /heroes/filters ---

    @Test
    @DisplayName("GET /heroes/filters должен вернуть 200 и фильтры")
    @Description("Проверяет получение всех справочников для фильтрации: элементы, редкости, классы")
    @Story("Фильтры героев")
    @Tag("smoke")
    void getFiltersShouldReturn200() {
        Allure.step("Получить фильтры", () -> {
            Response response = given()
                    .spec(RequestSpec.baseSpec())
                    .when()
                    .get(ApiEndpoints.HEROES_FILTERS)
                    .then()
                    .statusCode(200)
                    .extract()
                    .response();

            Allure.addAttachment(HeroApiHelper.RESPONSE_PREFIX + response.statusCode(),
                    HeroApiHelper.RESPONSE_TYPE, response.asPrettyString());

            HeroCatalogFiltersResponseDto filters = response.as(HeroCatalogFiltersResponseDto.class);

            Allure.step("Проверить, что фильтры не пустые", () -> {
                assertThat(filters.elements()).isNotEmpty();
                assertThat(filters.rarities()).isNotEmpty();
                assertThat(filters.heroClasses()).isNotEmpty();
            });

            Allure.step("Проверить структуру элемента", () -> {
                HeroCatalogFilterOptionResponseDto element = filters.elements().get(0);
                assertThat(element.id()).isNotNull();
                assertThat(element.name()).isNotNull();
                assertThat(element.imageUrl()).isNotNull();
            });

            Allure.step("Проверить структуру редкости", () -> {
                HeroCatalogRarityFilterOptionResponseDto rarity = filters.rarities().get(0);
                assertThat(rarity.id()).isNotNull();
                assertThat(rarity.name()).isNotNull();
                assertThat(rarity.stars()).isNotNull();
            });
        });
    }

    // --- GET /heroes/{slug} ---

    @Test
    @DisplayName("GET /heroes/{slug} должен вернуть 200 и детали героя")
    @Description("Проверяет получение полной информации о герое по slug")
    @Story("Детали героя")
    @Tag("smoke")
    void getDetailsShouldReturn200() {
        Allure.step("Получить slug первого героя", () -> {
            List<HeroLookupResponseDto> names = HeroApiHelper.fetchHeroNames();
            assertThat(names).isNotEmpty();
            String slug = names.get(0).slug();

            Allure.step("Запросить детали героя: " + slug, () -> {
                Response response = given()
                        .spec(RequestSpec.baseSpec())
                        .when()
                        .get(HEROES_BASE + "/" + slug)
                        .then()
                        .statusCode(200)
                        .extract()
                        .response();

                Allure.addAttachment(HeroApiHelper.RESPONSE_PREFIX + response.statusCode(),
                        HeroApiHelper.RESPONSE_TYPE, response.asPrettyString());

                HeroDetailsResponseDto hero = response.as(HeroDetailsResponseDto.class);

                Allure.step("Проверить основные поля", () -> {
                    assertThat(hero.id()).isNotNull();
                    assertThat(hero.name()).isNotNull();
                    assertThat(hero.slug()).isEqualTo(slug);
                    assertThat(hero.element()).isNotNull();
                    assertThat(hero.rarity()).isNotNull();
                    assertThat(hero.rarity().stars()).isGreaterThan(0);
                });
            });
        });
    }

    @Test
    @DisplayName("GET /heroes/{slug} с несуществующим slug должен вернуть 404")
    @Description("Проверяет, что запрос несуществующего героя возвращает 404")
    @Story("Детали героя")
    @Tag("regression")
    void getDetailsNonExistentShouldReturn404() {
        Allure.step("Запросить несуществующего героя", () -> {
            Response response = given()
                    .spec(RequestSpec.baseSpec())
                    .when()
                    .get(HEROES_BASE + "/non-existent-slug-123")
                    .then()
                    .statusCode(404)
                    .extract()
                    .response();

            Allure.addAttachment(HeroApiHelper.RESPONSE_PREFIX + response.statusCode(),
                    HeroApiHelper.RESPONSE_TYPE, response.asPrettyString());
        });
    }

    // --- POST /heroes/{slug}/stats/calculate ---

    @Test
    @DisplayName("POST /heroes/{slug}/stats/calculate должен вернуть 200")
    @Description("Проверяет расчёт статов героя на максимальном уровне")
    @Story("Расчёт статов")
    @Tag("smoke")
    void calculateStatsShouldReturn200() {
        Allure.step("Получить slug первого героя", () -> {
            List<HeroLookupResponseDto> names = HeroApiHelper.fetchHeroNames();
            assertThat(names).isNotEmpty();
            String slug = names.get(0).slug();

            Allure.step("Рассчитать статы", () -> {
                Allure.addAttachment("Request Body", HeroApiHelper.RESPONSE_TYPE, HeroStatsBody.VALID);

                Response response = given()
                        .spec(RequestSpec.jsonRequestSpec())
                        .body(HeroStatsBody.VALID)
                        .when()
                        .post(HEROES_BASE + "/" + slug + "/stats/calculate")
                        .then()
                        .statusCode(200)
                        .extract()
                        .response();

                Allure.addAttachment(HeroApiHelper.RESPONSE_PREFIX + response.statusCode(),
                        HeroApiHelper.RESPONSE_TYPE, response.asPrettyString());

                HeroStatCalculationResponseDto stats = response.as(HeroStatCalculationResponseDto.class);

                Allure.step("Проверить, что статы рассчитаны", () -> {
                    assertThat(stats.finalStats()).isNotNull();
                    assertThat(stats.finalStats().attack()).isNotNull();
                    assertThat(stats.finalStats().armor()).isNotNull();
                    assertThat(stats.finalStats().hp()).isNotNull();
                });
            });
        });
    }

    @Test
    @DisplayName("POST /heroes/{slug}/stats/calculate с неверным stageCode должен вернуть 400")
    @Description("Проверяет валидацию неверного значения stageCode")
    @Story("Расчёт статов")
    @Tag("regression")
    void calculateStatsInvalidStageCodeShouldReturn400() {
        Allure.step("Рассчитать статы с неверным stageCode", () -> {
            Allure.addAttachment(HeroApiHelper.REQUEST_ATTACHMENT, HeroApiHelper.RESPONSE_TYPE, HeroStatsBody.INVALID_STAGE_CODE);

            Response response = given()
                    .spec(RequestSpec.jsonRequestSpec())
                    .body(HeroStatsBody.INVALID_STAGE_CODE)
                    .when()
                    .post(HEROES_BASE + "/test-hero/stats/calculate")
                    .then()
                    .statusCode(400)
                    .extract()
                    .response();

            Allure.addAttachment(HeroApiHelper.RESPONSE_PREFIX + response.statusCode(),
                    HeroApiHelper.RESPONSE_TYPE, response.asPrettyString());
        });
    }

    @Test
    @DisplayName("POST /heroes/{slug}/stats/calculate с несуществующим slug должен вернуть 404")
    @Description("Проверяет, что расчёт статов для несуществующего героя возвращает 404")
    @Story("Расчёт статов")
    @Tag("regression")
    void calculateStatsNonExistentSlugShouldReturn404() {
        Allure.step("Рассчитать статы для несуществующего героя", () -> {
            Allure.addAttachment(HeroApiHelper.REQUEST_ATTACHMENT, HeroApiHelper.RESPONSE_TYPE, HeroStatsBody.VALID);

            Response response = given()
                    .spec(RequestSpec.jsonRequestSpec())
                    .body(HeroStatsBody.VALID)
                    .when()
                    .post(HEROES_BASE + "/non-existent-slug/stats/calculate")
                    .then()
                    .statusCode(404)
                    .extract()
                    .response();

            Allure.addAttachment(HeroApiHelper.RESPONSE_PREFIX + response.statusCode(),
                    HeroApiHelper.RESPONSE_TYPE, response.asPrettyString());
        });
    }

    @Test
    @DisplayName("POST /heroes/{slug}/stats/calculate с пустым телом должен вернуть 400")
    @Description("Проверяет валидацию пустого тела при расчёте статов")
    @Story("Расчёт статов")
    @Tag("regression")
    void calculateStatsEmptyBodyShouldReturn400() {
        Allure.step("Рассчитать статы без тела", () -> {
            Response response = given()
                    .spec(RequestSpec.jsonRequestSpec())
                    .when()
                    .post(HEROES_BASE + "/test-hero/stats/calculate")
                    .then()
                    .statusCode(400)
                    .extract()
                    .response();

            Allure.addAttachment(HeroApiHelper.RESPONSE_PREFIX + response.statusCode(),
                    HeroApiHelper.RESPONSE_TYPE, response.asPrettyString());
        });
    }

    // --- GET /heroes ---

    @Test
    @DisplayName("GET /heroes должен вернуть 200 и страницу героев")
    @Description("Проверяет пагинацию и структуру списка героев")
    @Story("Список героев")
    @Tag("smoke")
    void getHeroesShouldReturn200() {
        Allure.step("Получить список героев", () -> {
            Response response = given()
                    .spec(RequestSpec.baseSpec())
                    .queryParam("page", 0)
                    .queryParam("size", 5)
                    .when()
                    .get(HEROES_BASE)
                    .then()
                    .statusCode(200)
                    .extract()
                    .response();

            Allure.addAttachment(HeroApiHelper.RESPONSE_PREFIX + response.statusCode(),
                    HeroApiHelper.RESPONSE_TYPE, response.asPrettyString());

            HeroPageResponseDto page = response.as(HeroPageResponseDto.class);

            Allure.step("Проверить первого героя", () -> {
                assertThat(page.items()).isNotEmpty();
                HeroCardResponseDto first = page.items().get(0);
                assertThat(first.id()).isNotNull();
                assertThat(first.name()).isNotNull();
                assertThat(first.slug()).isNotNull();
            });
        });
    }

    @Test
    @DisplayName("GET /heroes с фильтрами должен вернуть 200")
    @Description("Проверяет фильтрацию героев по стихиям и редкости")
    @Story("Список героев")
    @Tag("regression")
    void getHeroesWithFiltersShouldReturn200() {
        Allure.step("Получить героев с фильтром по стихии", () -> {
            Response response = given()
                    .spec(RequestSpec.baseSpec())
                    .queryParam("page", 0)
                    .queryParam("size", 5)
                    .queryParam("elementIds", "1,2")
                    .queryParam("rarityIds", "1")
                    .when()
                    .get(HEROES_BASE)
                    .then()
                    .statusCode(200)
                    .extract()
                    .response();

            Allure.addAttachment(HeroApiHelper.RESPONSE_PREFIX + response.statusCode(),
                    HeroApiHelper.RESPONSE_TYPE, response.asPrettyString());
        });
    }

    @Test
    @DisplayName("GET /heroes с поиском должен вернуть 200")
    @Description("Проверяет текстовый поиск по героям")
    @Story("Список героев")
    @Tag("regression")
    void getHeroesWithSearchShouldReturn200() {
        Allure.step("Получить героев по поиску", () -> {
            Response response = given()
                    .spec(RequestSpec.baseSpec())
                    .queryParam("page", 0)
                    .queryParam("size", 5)
                    .queryParam("search", "дракон")
                    .when()
                    .get(HEROES_BASE)
                    .then()
                    .statusCode(200)
                    .extract()
                    .response();

            Allure.addAttachment(HeroApiHelper.RESPONSE_PREFIX + response.statusCode(),
                    HeroApiHelper.RESPONSE_TYPE, response.asPrettyString());
        });
    }

    @Test
    @DisplayName("GET /heroes с отрицательной страницей должен вернуть 200")
    @Description("Проверяет обработку отрицательного значения page")
    @Story("Список героев")
    @Tag("regression")
    void getHeroesNegativePageShouldReturn200() {
        Allure.step("Запросить героев с page=-1", () -> {
            Response response = given()
                    .spec(RequestSpec.baseSpec())
                    .queryParam("page", -1)
                    .queryParam("size", 5)
                    .when()
                    .get(HEROES_BASE)
                    .then()
                    .statusCode(200)
                    .extract()
                    .response();

            Allure.addAttachment(HeroApiHelper.RESPONSE_PREFIX + response.statusCode(),
                    HeroApiHelper.RESPONSE_TYPE, response.asPrettyString());
        });
    }

    // --- GET /heroes/{slug}/variants ---

    @Test
    @DisplayName("GET /heroes/{slug}/variants должен вернуть 200")
    @Description("Проверяет получение вариантов героя: текущий, базовый, костюмы")
    @Story("Варианты героя")
    @Tag("smoke")
    void getVariantsShouldReturn200() {
        Allure.step("Получить slug первого героя", () -> {
            List<HeroLookupResponseDto> names = HeroApiHelper.fetchHeroNames();
            assertThat(names).isNotEmpty();
            String slug = names.get(0).slug();

            Allure.step("Запросить варианты героя", () -> {
                Response response = given()
                        .spec(RequestSpec.baseSpec())
                        .when()
                        .get(HEROES_BASE + "/" + slug + "/variants")
                        .then()
                        .statusCode(200)
                        .extract()
                        .response();

                Allure.addAttachment(HeroApiHelper.RESPONSE_PREFIX + response.statusCode(),
                        HeroApiHelper.RESPONSE_TYPE, response.asPrettyString());

                HeroVariantsResponseDto variants = response.as(HeroVariantsResponseDto.class);

                Allure.step("Проверить структуру", () -> {
                    assertThat(variants.currentHero()).isNotNull();
                    assertThat(variants.currentHero().name()).isNotNull();
                });
            });
        });
    }

    @Test
    @DisplayName("GET /heroes/{slug}/variants с несуществующим slug должен вернуть 404")
    @Description("Проверяет, что запрос вариантов несуществующего героя возвращает 404")
    @Story("Варианты героя")
    @Tag("regression")
    void getVariantsNonExistentShouldReturn404() {
        Allure.step("Запросить варианты несуществующего героя", () -> {
            Response response = given()
                    .spec(RequestSpec.baseSpec())
                    .when()
                    .get(HEROES_BASE + "/non-existent-slug/variants")
                    .then()
                    .statusCode(404)
                    .extract()
                    .response();

            Allure.addAttachment(HeroApiHelper.RESPONSE_PREFIX + response.statusCode(),
                    HeroApiHelper.RESPONSE_TYPE, response.asPrettyString());
        });
    }
}