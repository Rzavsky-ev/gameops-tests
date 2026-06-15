package api.mainService.dto.heroPublicController;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * DTO для ответа с расчётом статов героя.
 *
 * @param stageCode            стадия развития
 * @param costumeHeroId        идентификатор костюма
 * @param costumeIndex         индекс костюма
 * @param emblemPathType       путь эмблем
 * @param includeMasterEmblems включать ли мастер-эмблемы
 * @param minStats             минимальные характеристики
 * @param baseStats            базовые характеристики
 * @param stageStats           стадийные характеристики
 * @param costumeBonus         бонусы костюма
 * @param emblemBonus          бонусы эмблем
 * @param masterEmblemBonus    бонусы мастер-эмблем
 * @param finalStats           итоговые характеристики
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record HeroStatCalculationResponseDto(
        String stageCode,
        Long costumeHeroId,
        Integer costumeIndex,
        String emblemPathType,
        Boolean includeMasterEmblems,
        HeroStatBlockResponseDto minStats,
        HeroStatBlockResponseDto baseStats,
        HeroStatBlockResponseDto stageStats,
        HeroStatBlockResponseDto costumeBonus,
        HeroStatBlockResponseDto emblemBonus,
        HeroStatBlockResponseDto masterEmblemBonus,
        HeroStatBlockResponseDto finalStats
) {
}