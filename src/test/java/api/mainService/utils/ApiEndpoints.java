package api.mainService.utils;

import exceptions.UtilityClassException;

/**
 * Константы эндпоинтов API.
 */
public class ApiEndpoints {

    /**
     * Базовый путь к эндпоинтам героев
     */
    public static final String HEROES_BASE = "/api/v1/public/heroes";

    /**
     * GET — список имён героев
     */
    public static final String HEROES_NAMES = HEROES_BASE + "/names";

    /**
     * POST — пакетная загрузка героев по id
     */
    public static final String HEROES_BATCH = HEROES_BASE + "/batch";

    /**
     * GET — поиск героев
     */
    public static final String HEROES_SEARCH = HEROES_BASE + "/search";

    /**
     * GET — справочники для фильтрации
     */
    public static final String HEROES_FILTERS = HEROES_BASE + "/filters";

    /**
     * GET — список героев для тренера
     */
    public static final String HERO_COACH = "/api/v1/public/hero-coach";

    /**
     * GET — прогноз тренера героев
     */
    public static final String HERO_COACH_FORECAST = HERO_COACH + "/forecast";

    private ApiEndpoints() {
        throw new UtilityClassException(getClass());
    }
}