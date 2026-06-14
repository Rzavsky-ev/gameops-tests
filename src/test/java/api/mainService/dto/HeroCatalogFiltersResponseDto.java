package api.mainService.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * DTO для ответа {@code GET /heroes/filters}.
 *
 * @param elements     список стихий
 * @param rarities     список редкостей (со звёздами)
 * @param heroClasses  список классов героев
 * @param families     список семей
 * @param manaSpeeds   список скоростей маны
 * @param alphaTalents список альфа-талантов
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record HeroCatalogFiltersResponseDto(
        List<HeroCatalogFilterOptionResponseDto> elements,
        List<HeroCatalogRarityFilterOptionResponseDto> rarities,
        List<HeroCatalogFilterOptionResponseDto> heroClasses,
        List<HeroCatalogFilterOptionResponseDto> families,
        List<HeroCatalogFilterOptionResponseDto> manaSpeeds,
        List<HeroCatalogFilterOptionResponseDto> alphaTalents
) {
}