package api.mainService.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * DTO для ответа с вариантами героя.
 *
 * @param currentHero текущий герой (полная информация)
 * @param baseHero    базовый герой
 * @param costumes    список костюмов
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record HeroVariantsResponseDto(
        HeroDetailsResponseDto currentHero,
        HeroVariantSummaryResponseDto baseHero,
        List<HeroVariantSummaryResponseDto> costumes
) {
}