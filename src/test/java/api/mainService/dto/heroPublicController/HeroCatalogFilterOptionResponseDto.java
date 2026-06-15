package api.mainService.dto.heroPublicController;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * DTO для элемента справочника фильтров.
 *
 * @param id       идентификатор
 * @param name     название
 * @param imageUrl URL изображения
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record HeroCatalogFilterOptionResponseDto(Long id, String name, String imageUrl) {
}
