package api.mainService.dto.heroPublicController;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * DTO для элемента редкости в фильтрах.
 *
 * @param id       идентификатор редкости
 * @param name     название редкости
 * @param imageUrl URL изображения
 * @param stars    количество звёзд
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record HeroCatalogRarityFilterOptionResponseDto(Long id, String name, String imageUrl, Integer stars) {
}
