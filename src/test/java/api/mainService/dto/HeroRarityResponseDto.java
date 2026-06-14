package api.mainService.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * DTO для информации о редкости героя.
 *
 * @param id       идентификатор редкости
 * @param stars    количество звёзд
 * @param imageUrl URL изображения
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record HeroRarityResponseDto(Long id, int stars, String imageUrl) {
}