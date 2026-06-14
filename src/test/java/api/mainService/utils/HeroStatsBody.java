package api.mainService.utils;

import exceptions.UtilityClassException;

/**
 * Шаблоны JSON-тел для запросов к {@code POST /api/v1/public/heroes/{slug}/stats/calculate}.
 */
public class HeroStatsBody {

    /**
     * Валидный запрос с stageCode = ASCENSION_4_80
     */
    public static final String VALID = """
            {
                "stageCode": "ASCENSION_4_80"
            }
            """;

    /**
     * Невалидный запрос с несуществующим stageCode
     */
    public static final String INVALID_STAGE_CODE = """
            {
                "stageCode": "INVALID_CODE"
            }
            """;

    private HeroStatsBody() {
        throw new UtilityClassException(getClass());
    }
}