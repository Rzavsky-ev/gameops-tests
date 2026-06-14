package api.mainService.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * DTO для краткой информации о варианте героя.*
 *
 * @param id           идентификатор
 * @param slug         URL-имя
 * @param name         имя
 * @param costumeIndex индекс костюма
 * @param imageUrl     URL основного изображения
 * @param previewUrl   URL превью-изображения
 * @param elementName  название стихии
 * @param rarityName   название редкости
 * @param rarityStars  количество звёзд редкости
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record HeroVariantSummaryResponseDto(
        Long id,
        String slug,
        String name,
        Integer costumeIndex,
        String imageUrl,
        String previewUrl,
        String elementName,
        String rarityName,
        Integer rarityStars
) {
}