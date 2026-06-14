package api.mainService.utils;

import exceptions.UtilityClassException;

/**
 * Шаблоны JSON-тел для запросов к {@code POST /api/v1/public/heroes/batch}.
 */
public class HeroBatchBody {

    /**
     * Существующие id героев
     */
    public static final String VALID_IDS = """
            {
                "heroIds": [1, 2, 3]
            }
            """;

    /**
     * Один id героя
     */
    public static final String SINGLE_ID = """
            {
                "heroIds": [1]
            }
            """;

    /**
     * Несуществующие id
     */
    public static final String NON_EXISTENT_IDS = """
            {
                "heroIds": [99999, 88888]
            }
            """;

    /**
     * Пустой массив id
     */
    public static final String EMPTY_IDS = """
            {
                "heroIds": []
            }
            """;

    /**
     * Пустое тело
     */
    public static final String EMPTY_BODY = """
            {
            }
            """;

    private HeroBatchBody() {
        throw new UtilityClassException(getClass());
    }
}